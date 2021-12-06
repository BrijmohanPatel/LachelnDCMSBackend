package com.dcms.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.dcms.bean.PlansData;
import com.dcms.bean.ResponseBean;
import com.dcms.constants.CommonConstants;
import com.dcms.modal.Patients;
import com.dcms.modal.Plans;
import com.dcms.modal.TreatmentPlans;
import com.dcms.modal.Treatments;
import com.dcms.utilities.HibernateUtil;
import com.google.gson.Gson;

@Component
public class PatientsDaoImpl implements PatientsDao {
	
	
	 Gson gson = new Gson();

	  List<Patients> patientsList = null;
	  List<TreatmentPlans> treatmentPlanList = null;
	  List<Plans> plans = null;
	  List<Treatments> treatments = null;
	
	 @Override
	  public ResponseBean addPatient(ResponseBean responsebean) {
	    System.out.println("PatientsDaoImpl.addPatient()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    Patients patient = new Patients();
	    
	    try {
	    	patient.setReg_date(responsebean.getData().getPatientdata().getReg_date());
	        patient.setPatient_name(responsebean.getData().getPatientdata().getPatient_name());
	        patient.setMobile_number(responsebean.getData().getPatientdata().getMobile_number());
	        patient.setAddress(responsebean.getData().getPatientdata().getAddress());
	        patient.setGender(responsebean.getData().getPatientdata().getGender());
	        patient.setAge(responsebean.getData().getPatientdata().getAge());        
	        patient.setOccupation(responsebean.getData().getPatientdata().getOccupation());
	        patient.setMedical_history(responsebean.getData().getPatientdata().getMedical_history());
	        patient.setChief_complaint(responsebean.getData().getPatientdata().getChief_complaint());
	        String pID = (String)session.save(patient);
	        if(!pID.isEmpty()) {
	         /*   String searchQuery = "from patients WHERE patient_id =:patient_id_param";
	    		Query query = session.createQuery(searchQuery);
	    		query.setParameter("patient_id_param", pID);
	    		patientsList = query.list();*/
	            tx.commit();
	            responsebean.setMessage("Patient added  successfully");
	            responsebean.setStatus(CommonConstants.SUCCESS);
	            responsebean.getData().getPatientdata().setPatient_id(pID);
	            responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
	        }else {
	        	responsebean.setMessage("Failed to add patient");
	            responsebean.setStatus(CommonConstants.FAILED);
	            responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	        }
	    } catch(Exception e) {
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }  
	    
	    return responsebean;
	  }
	 
	 @SuppressWarnings("null")
		@Override
		public ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception {
			System.out.println("PatientsDaoImpl.searchPatient()");		
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		    try{
		    	
		    	String searchAction = responsebean.getData().getSearchPatients().getSearch_by_option();
				String searchQuery = null;
				
				if(searchAction.equals("caseNo")) {
					searchQuery = "from patients WHERE patient_id =:patient_id_param";
					Query query = session.createQuery(searchQuery);
					query.setParameter("patient_id_param", responsebean.getData().getSearchPatients().getSearch_by_optionvalue());
					patientsList = query.list();
					
				}else if(searchAction.equals("patientName")) {
					searchQuery = "from patients WHERE patient_name LIKE concat('%',:patient_name_param,'%')";
					Query query = session.createQuery(searchQuery);
					query.setParameter("patient_name_param", responsebean.getData().getSearchPatients().getSearch_by_optionvalue());
					patientsList = query.list();
					
				}else if(searchAction.equals("mobileNumber")) {
					searchQuery = "from patients WHERE mobile_number =:mobile_number_param";
					Query query = session.createQuery(searchQuery);
					query.setParameter("mobile_number_param", Long.parseLong(responsebean.getData().getSearchPatients().getSearch_by_optionvalue()));
					patientsList = query.list();
					
				}else if(searchAction.equals("Date")) {
					searchQuery = "from patients WHERE reg_date BETWEEN :startDate and :endDate ORDER BY reg_date ASC";
					Query query = session.createQuery(searchQuery);
					query.setParameter("startDate", responsebean.getData().getSearchPatients().getSearchByDate().getStart_date());
					query.setParameter("endDate", responsebean.getData().getSearchPatients().getSearchByDate().getEnd_date());
					patientsList = query.list();
				}
				
				 tx.commit();
				 if(patientsList.size() <= 0) {
					 responsebean.setMessage("No Patient Found!!!");
				 }else{
					 responsebean.setMessage("Patient Found Successfully");
				 }
			     
				 responsebean.setStatus(CommonConstants.SUCCESS);
			     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
			     responsebean.getData().setPatientsList(patientsList);
			     
		    }catch(Exception e) {
		    	if (tx != null) tx.rollback();
		        e.printStackTrace();
		        responsebean.setMessage("Failed to search patient!");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		    } finally {
		        session.close();
		    }
		    		
			return responsebean;
		}

		@SuppressWarnings("unchecked")
		@Override
		public ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception {
			System.out.println("PatientsDaoImpl.selectPatient()");
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		    try{
		    	
		    	String searchAction = responsebean.getData().getSearchPatients().getSearch_by_option();
				Query query  = null;
				String caseNo = responsebean.getData().getSearchPatients().getSearch_by_optionvalue();
				if(searchAction.equals("caseNo")) {
					query = session.createQuery("from treatment_plans WHERE case_no =:case_no");
					query.setParameter("case_no",caseNo);
					treatmentPlanList = query.list();
					System.out.println("treatmentPlanList"+gson.toJson(treatmentPlanList));
					
					for(TreatmentPlans treatmentPlans : treatmentPlanList) {
						query = session.createQuery("from treatment_plan_units WHERE tp_id =:tp_id");
						query.setParameter("tp_id",treatmentPlans.getTp_id());
						plans = query.list();
						System.out.println("value of pd is"+gson.toJson(plans));
						treatmentPlans.setPlans(plans);
					}
				}
				 tx.commit();
				 responsebean.setMessage("Successfully fetched TreatmentPlans!");
				 responsebean.setStatus(CommonConstants.SUCCESS);
			     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
			     responsebean.getData().setTreatmentPlanList(treatmentPlanList);
			     
		    }catch(Exception e) {
		    	if (tx != null) tx.rollback();
		        e.printStackTrace();
		        responsebean.setMessage("Failed to select patient!");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		    } finally {
		        session.close();
		    }
			return responsebean;
		}

		@Override
		public ResponseBean getAllPatients() throws SQLException, Exception {
			System.out.println("PatientsDaoImpl.getAllPatients()");
			ResponseBean responsebean = new ResponseBean();
			
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		 
		    try{
		    	Query query = session.createQuery("from patients order by reg_Date desc");
		    	System.out.println("check 3");
		    	 patientsList = query.list();
		    	 tx.commit();
		    	 
				 responsebean.setMessage("Successfully fetched Patients!");
				
				 responsebean.setStatus(CommonConstants.SUCCESS);
				 
			     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
			    
			     responsebean.getData().setPatientsList(patientsList);
			       
		    }catch(Exception e) {
		    	System.out.println(e);
		    	if (tx != null) tx.rollback();
		        e.printStackTrace();
		        responsebean.setMessage("Failed to get patient!");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		        
		    }finally {
		    	System.out.println("check 10");
		    	session.close();
		    }
			return responsebean;
		}

		@Override
		public ResponseBean updatePatient(ResponseBean responsebean) throws SQLException, Exception {
			System.out.println("PatientsDaoImpl.updatePatient");
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		    try {
		    	Patients patients = session.load(Patients.class, responsebean.getData().getPatientdata().getPatient_id());
		    	if(patients != null) {
		    		
		    		if(responsebean.getData().getPatientdata().getPatient_name() != null) {
		    			patients.setPatient_name(responsebean.getData().getPatientdata().getPatient_name());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getAge() > 0) {
		    			patients.setAge(responsebean.getData().getPatientdata().getAge());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getAddress() != null) {
		    			patients.setAddress(responsebean.getData().getPatientdata().getAddress());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getChief_complaint() != null) {
		    			patients.setChief_complaint(responsebean.getData().getPatientdata().getChief_complaint());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getGender() != null) {
		    			patients.setGender(responsebean.getData().getPatientdata().getGender());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getMedical_history() != null) {
		    			patients.setMedical_history(responsebean.getData().getPatientdata().getMedical_history());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getMobile_number() != null) {
		    			patients.setMobile_number(responsebean.getData().getPatientdata().getMobile_number());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getOccupation() != null) {
		    			patients.setOccupation(responsebean.getData().getPatientdata().getOccupation());
		    		}
		    		
		    		if(responsebean.getData().getPatientdata().getReg_date() != null) {
		    			patients.setReg_date(responsebean.getData().getPatientdata().getReg_date());
		    		}
		    		
			    	session.update(patients);
			    	responsebean.setStatus(CommonConstants.SUCCESS);
			        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
			    	responsebean.setMessage("Patients updated successfully");
		    	}else {
		    		responsebean.setStatus(CommonConstants.FAILED);
		    		responsebean.setMessage("Patients not updated.");
		    		responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		    	}
		    	tx.commit();    	
		    }catch(Exception e) {
		    	System.out.println(e.getMessage());
		    	if (tx != null) tx.rollback();
		        	e.printStackTrace();
		        responsebean.setMessage("Failed to update Patients");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		    } finally {
		        session.close();
		    }
		    
			return responsebean;
		}

		@Override
		public ResponseBean deletePatient(ResponseBean responsebean) throws SQLException, Exception {
			
			System.out.println("PatientsDaoImpl.deletePatient");
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
			try {
				String pID = responsebean.getData().getPatientdata().getPatient_id();
					Patients dPatient = session.get(Patients.class, pID);
					if(dPatient != null) {
						session.delete(dPatient);
						System.out.println("Patient "+pID+" deleted");
						responsebean.setMessage("Patient deteled.");
				        responsebean.setStatus(CommonConstants.SUCCESS);
				        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
				    }else {
				    	responsebean.setMessage("Patient not deteled.");
				        responsebean.setStatus(CommonConstants.FAILED);
				        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
				    }
				
				tx.commit();
				
			}catch(Exception e) {
		    	System.out.println(e.getMessage());
		    	if (tx != null) tx.rollback();
		        e.printStackTrace();
		        responsebean.setMessage("Failed to delete Patient");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
		    } finally {
		        session.close();
		    }
			return responsebean;
		}
}
