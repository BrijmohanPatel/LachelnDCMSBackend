package com.dcms.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TreatmentPlanDaoImpl implements TreatmentPlanDao {
	
	Gson gson = new Gson();

	  List<Patients> patientsList = null;
	  List<TreatmentPlans> treatmentPlanList = null;
	  List<Plans> plans = null;
	  List<Treatments> treatments = null;
	      
	
	@Override
	public ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
	  	System.out.println("TreatmentPlanDaoImpl.addTreatmentPlan()");
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
		  System.out.println("TreatmentPlanDaoImpl.formatedTodaysDate");
		  	Date date = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat(format);
		    String todayDate = formatter.format(date);
		    return todayDate;
	 }

	@Override
	public ResponseBean updateTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentPlanDaoImpl.updateTreatmentPlan");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	
	    	TreatmentPlans trtplan = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentPlanData().getTp_id());
	    	
	    	
	    	if(trtplan != null) {

		    	trtplan.setTotal_amount(responsebean.getData().getTreatmentPlanData().getTotalAmount());
		    	session.update(trtplan);
		    	
		    	List<PlansData> plansData = responsebean.getData().getTreatmentPlanData().getPlans();
		    	
		    	plansData.forEach(plans ->{
		    		Plans plan = new Plans();
		    		
		    		if(plans.getTpu_id() != null && plans.getTpu_id() != 0) {
		    			plan = session.get(Plans.class, plans.getTpu_id());
		    			
		    		}else {
		    			plan.setTp_id(responsebean.getData().getTreatmentPlanData().getTp_id());
		    		}
		    		
		    		plan.setTreatment(plans.getTreatment());
		    		plan.setEstimatedAmount(plans.getEstimatedAmount());
		    		plan.setUpperLeftTooth(plans.getUpperLeftTooth());
		    		plan.setUpperRightTooth(plans.getUpperRightTooth());
		    		plan.setLowerLeftTooth(plans.getLowerLeftTooth());
		    		plan.setLowerRightTooth(plans.getLowerRightTooth());
		    		session.saveOrUpdate(plan);
		    		
		    	});
		    	
		    	responsebean.setStatus(CommonConstants.SUCCESS);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
		    	responsebean.setMessage("Treatment plan updated");
		    	
	    	}else {
	    		responsebean.setStatus(CommonConstants.FAILED);
	    		responsebean.setMessage("Treatment plan not present.");
	    		responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    	}
	    	
	    	tx.commit();
	    	    	
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to update treatment Plan");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	        
	    } finally {
	        session.close();
	    }
	    
		return responsebean;
	}

	@Override
	public ResponseBean deleteTreatmentPlan(String tpID) throws SQLException, Exception {
		
		ResponseBean responsebean = new ResponseBean();
		System.out.println("TreatmentPlanDaoImpl.deleteTreatmentPlan");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
		try {
			String hql = "FROM treatment_plan_units where tp_id =: tp_ID";
			Query createQuery = session.createQuery(hql);
			createQuery.setParameter("tp_ID", tpID);
			List tp_unit = createQuery.list();
			if(tp_unit.size() > 0) {
				responsebean.setMessage("Cannot delete treatment plan. First delete plans.");
		        responsebean.setStatus(CommonConstants.FAILED);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
			}else {
				TreatmentPlans treatmentPlan = session.get(TreatmentPlans.class, tpID);
				if(treatmentPlan != null) {
					session.delete(treatmentPlan);
					System.out.println("treatment plan "+tpID+" deleted");
					responsebean.setMessage("Treatment plan deteled.");
			        responsebean.setStatus(CommonConstants.SUCCESS);
			        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
				}
			}
			
			tx.commit();
			
		}catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to delete treatment Plan");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		
		
		return responsebean;
	}


}
