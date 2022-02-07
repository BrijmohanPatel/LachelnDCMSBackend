package com.dcms.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.SystemPropertyUtils;

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
	    	tplans.setTotal_balance_amount(responsebean.getData().getTreatmentPlanData().getTotalAmount());
	    	tplans.setTotal_paid_amount("0");
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
		    	Long totalbalance = (Long.parseLong(responsebean.getData().getTreatmentPlanData().getTotalAmount())- Long.parseLong(trtplan.getTotal_paid_amount()));
		    	trtplan.setTotal_balance_amount(totalbalance.toString());
		    	session.update(trtplan);
		    	
		    	List<PlansData> plansData = responsebean.getData().getTreatmentPlanData().getPlans();
		    	
		    	plansData.forEach(plans ->{
		    		Plans plan = new Plans();
		    		System.out.println("***************"+plans);
		    		if(plans.getTpu_id() != "" && plans.getTpu_id() != null) {
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
		    	
		    	Query query = session.createQuery("from treatments where tp_id =:tp_id_param order by treatment_added_date asc");
		        query.setParameter("tp_id_param", trtplan.getTp_id());
		        treatments = query.list();
		        System.out.println("treatmentList is fetched :"+treatments);
		        
		        if(treatments.size() > 0) {
		        	treatments = updateTreamentValuesManager(treatments, trtplan.getTotal_amount());
		        	for(Treatments trt : treatments) {
			    		System.out.println("Inside for each loop"+gson.toJson(trt));
			    		session.update(trt);
			    	}
		        }
		        
		        
		    	
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
	
	
	public List<Treatments> updateTreamentValuesManager(List<Treatments> oldList , String totalAmount){
		List<Treatments> newList = oldList;
		Long totalPaid = 0L;
		Long totalBalance = 0L;
		
		for(int i=0;i<oldList.size();i++) {
			totalPaid = Long.parseLong(oldList.get(i).getPaid_amount());
					
			totalBalance = Long.parseLong(totalAmount) - totalPaid;
			newList.get(i).setBalance_amount(totalBalance.toString());	
		}
		return newList;
	}

	@SuppressWarnings("unused")
	@Override
	public ResponseBean deleteTreatmentPlan(String tpuID) throws SQLException, Exception {
		
		ResponseBean responsebean = new ResponseBean();
		System.out.println("TreatmentPlanDaoImpl.deleteTreatmentPlan");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
		try {
			
			Plans plan = session.get(Plans.class, tpuID);
			TreatmentPlans treatmentPlan = session.get(TreatmentPlans.class, plan.getTp_id());
			if(plan != null) {
				String hql_u = "FROM treatment_plan_units where tp_ID =: tp_ID";
				Query createQuery_u = session.createQuery(hql_u);
				createQuery_u.setParameter("tp_ID", plan.getTp_id());
				List tp_units = createQuery_u.list();
				if(tp_units.size() == 1) {
					session.delete(treatmentPlan);
				}else {
					String TotalAmount = treatmentPlan.getTotal_amount();
					long TotalAmountLongValue = Long.parseLong(TotalAmount);
					
					String unitAmount = plan.getEstimatedAmount();
					long unitAmountLongValue = Long.parseLong(unitAmount);
					
					TotalAmountLongValue = TotalAmountLongValue - unitAmountLongValue;
					
					treatmentPlan.setTotal_amount(String.valueOf(TotalAmountLongValue));
					session.update(treatmentPlan);

				}
				
				session.delete(plan);
				System.out.println("deleted plan is "+plan);
				responsebean.setMessage("Treatment plan unit deteled");
		        responsebean.setStatus(CommonConstants.SUCCESS);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
		        
			}else {
				responsebean.setMessage("Treatment plan unit not present in DB.");
		        responsebean.setStatus(CommonConstants.SUCCESS);
		        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
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
