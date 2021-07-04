package com.dcms.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;
import com.dcms.constants.CommonConstants;
import com.dcms.modal.Patients;
import com.dcms.modal.Plans;
import com.dcms.modal.TreatmentPlans;
import com.dcms.modal.Treatments;
import com.dcms.utilities.HibernateUtil;
import com.google.gson.Gson;

@Component
public class TreatmentDaoImpl implements TreatmentDao{
	
	Gson gson = new Gson();

	  List<Patients> patientsList = null;
	  List<TreatmentPlans> treatmentPlanList = null;
	  List<Plans> plans = null;
	  List<Treatments> treatments = null;
	      
	  
		

		@Override
		public ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception {
			System.out.println("TreatmentDaoImpl.addTreatment()");
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
			System.out.println("TreatmentDaoImpl.getTreatments()");
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
