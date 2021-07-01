package com.dcms.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.dcms.bean. * ;
import com.dcms.modal.*;
import com.dcms.utilities.*;
import com.dcms.constants.*;

import com.google.gson.Gson;

@Component
public class DentalDaoImpl implements DentalDao {
 
  Gson gson = new Gson();

  List<Patients> patientsList = null;
  List<TreatmentPlans> treatmentPlanList = null;
  List<Plans> plans = null;
  List<Treatments> treatments = null;
    
  @Override
  public ResponseBean addPatient(ResponseBean responsebean) {
    System.out.println("DentalDaoImpl.addPatient()");
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
  
  @Override
	public ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
	  	System.out.println("DentalDaoImpl.addTreatmentPlan()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	    	TreatmentPlans tplans = new TreatmentPlans();
	    	tplans.setCase_no(responsebean.getData().getTreatmentPlanData().getCaseNo());
	    	tplans.setTotal_amount(responsebean.getData().getTreatmentPlanData().getTotalAmount());
	    	tplans.setReg_date(formatedTodaysDate("yyyy-MM-dd"));
	    	tplans.setStatus("ACTIVE");
	    	session.save(tplans);
	    	
	    	for(PlansData plandata: responsebean.getData().getTreatmentPlanData().getPlans()) {
	    		Plans plans = new Plans();
		    	plans.setTp_id(tplans.getTp_id());
		    	plans.setEstimatedAmount(plandata.getEstimatedAmount());
		    	plans.setTreatment(plandata.getTreatment());
		    	plans.setUpperLeftTooth(plandata.getUpperLeftTooth());
		    	plans.setUpperRightTooth(plandata.getUpperRightTooth());
		    	plans.setLowerLeftTooth(plandata.getLowerLeftTooth());
		    	plans.setLowerRightTooth(plandata.getLowerRightTooth());
		    	session.save(plans);
	    	}
	    	
	        tx.commit();
	        responsebean.setMessage("TreatmentPlan added  successfully");
	        responsebean.setStatus(CommonConstants.SUCCESS);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
	    	
	    }catch(Exception e) {
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to add treatment Plan");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }  
		return responsebean;
	}
  
	  public String formatedTodaysDate(String format) {
		  	Date date = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat(format);
		    String todayDate = formatter.format(date);
		    return todayDate;
	  }

	@SuppressWarnings("null")
	@Override
	public ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception {
		
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
	        responsebean.setMessage("Failed to search patient!");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		return responsebean;
	}

	@Override
	public ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalDaoImpl.addTreatment()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	    	Treatments treatment = new Treatments();
	    	treatment.setPatient_id(responsebean.getData().getTreatmentData().getPatient_id());
	    	treatment.setTp_id(responsebean.getData().getTreatmentData().getTp_id());
	    	treatment.setWork_done(responsebean.getData().getTreatmentData().getWork_done());
	    	treatment.setTreatment_date(responsebean.getData().getTreatmentData().getTreatment_date());
	    	treatment.setPaid_amount(responsebean.getData().getTreatmentData().getPaid_amount());
	    	treatment.setBalance_amount(responsebean.getData().getTreatmentData().getBalance_amount());
	    	treatment.setPayment_on_date(responsebean.getData().getTreatmentData().getPayment_on_date());
	    	treatment.setUpperLeftTooth(responsebean.getData().getTreatmentData().getToothData().getUpperLeftTooth());
	    	treatment.setUpperRightTooth(responsebean.getData().getTreatmentData().getToothData().getUpperRightTooth());
	    	treatment.setLowerLeftTooth(responsebean.getData().getTreatmentData().getToothData().getLowerLeftTooth());
	    	treatment.setLowerRightTooth(responsebean.getData().getTreatmentData().getToothData().getLowerRightTooth());
	    	session.save(treatment);
	        
	        
	        if(responsebean.getData().getTreatmentPlanData() != null && !responsebean.getData().getTreatmentPlanData().getStatus().isEmpty() && responsebean.getData().getTreatmentPlanData().getStatus().equals("CLOSED")) {
	        	TreatmentPlans tp = new TreatmentPlans();
	        	tp = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentData().getTp_id());
	        	tp.setStatus(responsebean.getData().getTreatmentPlanData().getStatus());
	        	session.update(tp);
	        }
	        
	        tx.commit();
	        
	        responsebean.setMessage("Treatment added  successfully");
	        responsebean.setStatus(CommonConstants.SUCCESS);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
	    }catch(Exception e) {
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to add treatment");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }  
		return responsebean;
	}

	@Override
	public ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalDaoImpl.addTreatment()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	        Query query = session.createQuery("from treatments where tp_id =:tp_id_param order by last_modified DESC");
	        query.setParameter("tp_id_param", responsebean.getData().getTreatmentData().getTp_id());
	        treatments = query.list(); 
	        responsebean.setMessage("Treatment fetched  successfully");
	        responsebean.getData().setTreatmentsList(treatments);
	        responsebean.setStatus(CommonConstants.SUCCESS);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
	    }catch(Exception e) {
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to fetch treatment");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }  
		return responsebean;
	}

}