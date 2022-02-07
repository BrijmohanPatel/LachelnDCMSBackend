package com.dcms.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;
import com.dcms.constants.CommonConstants;
import com.dcms.modal.Patients;
import com.dcms.modal.TreatmentMaster;
import com.dcms.modal.Treatments;
import com.dcms.utilities.HibernateUtil;

@Component
public class AdminTreatmentMasterDaoImpl implements AdminTreatmentMasterDao{

	List<TreatmentMaster> treatmentMasterList = null;
	
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    

	
	@Override
	public ResponseBean saveTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
	    System.out.println("AdminTreatmentMasterDaoImpl.saveTreatmentMaster()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	TreatmentMaster trt_master = new TreatmentMaster();
	    	trt_master.setTreatment_name(responseBean.getData().getTreatmentMasterData().getTreatment_name());
	    	trt_master.setTreatment_added_date(sdf3.format(timestamp));
	    	trt_master.setTreatment_updated_date(sdf3.format(timestamp));
	    	
	    	 String trt_master_id = (String)session.save(trt_master);
	    	 if(trt_master_id != null) {
	    		
	    		 responseBean.setMessage("Treatment added  successfully");
	    		 responseBean.setStatus(CommonConstants.SUCCESS);
	    		 responseBean.getData().getTreatmentMasterData().setTm_id(trt_master_id);
	    		 responseBean.setStatusCode(CommonConstants.STATUSCODE_200); 
	    	 }else {
	    		 responseBean.setMessage("Failed to add Treatment");
	    		 responseBean.setStatus(CommonConstants.FAILED);
	    		 responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    	 }
	    	 tx.commit();
	    } catch(Exception e) {
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }  
	    
	    return responseBean;
	}

	@Override
	public ResponseBean getTreatmentMaster() throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterDaoImpl.getTreatmentMaster()");
		ResponseBean responsebean = new ResponseBean();
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	    	 Query query = session.createQuery("from treatment_master order by treatment_name desc");
	    	 treatmentMasterList = query.list();
	    	 tx.commit();
	    	 
			 responsebean.setMessage("Successfully fetched Treatment Master!");
			 responsebean.setStatus(CommonConstants.SUCCESS);
		     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
		     responsebean.getData().setTreatmentMasterList(treatmentMasterList);
		       
	    }catch(Exception e) {
	    	System.out.println(e);
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to get Treatment Master!");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	        
	    }finally {
	    	session.close();
	    }
		return responsebean;
	}

	@Override
	public ResponseBean updateTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterDaoImpl.updateTreatmentMaster()");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	TreatmentMaster treatmentMaster = session.load(TreatmentMaster.class, responseBean.getData().getTreatmentMasterData().getTm_id());
	    	if(treatmentMaster != null) {
	    		
	    		if(responseBean.getData().getTreatmentMasterData().getTreatment_name() != null) {
	    			treatmentMaster.setTreatment_name(responseBean.getData().getTreatmentMasterData().getTreatment_name());
	    		}
	    		treatmentMaster.setTreatment_updated_date(sdf3.format(timestamp));
		    	session.update(treatmentMaster);
		    	responseBean.setStatus(CommonConstants.SUCCESS);
		        responseBean.setStatusCode(CommonConstants.STATUSCODE_200);
		    	responseBean.setMessage("Treatment Master updated successfully");
	    	}else {
	    		responseBean.setStatus(CommonConstants.FAILED);
	    		responseBean.setMessage("Treatment Master not updated.");
	    		responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    	}
	    	tx.commit();    	
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        	e.printStackTrace();
	        responseBean.setMessage("Failed to update Treatment Master");
	        responseBean.setStatus(CommonConstants.FAILED);
	        responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		return responseBean;
	}

	@Override
	public ResponseBean deleteTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterDaoImpl.deleteTreatmentMaster");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
		try {
			String tm_id = responseBean.getData().getTreatmentMasterData().getTm_id();
				TreatmentMaster tmt_master = session.get(TreatmentMaster.class, tm_id);
				if(tmt_master != null) {
					session.delete(tmt_master);
					System.out.println("Treatment Master ID "+tm_id+" deleted");
					responseBean.setMessage("Treatment deteled.");
					responseBean.setStatus(CommonConstants.SUCCESS);
					responseBean.setStatusCode(CommonConstants.STATUSCODE_200);
			    }else {
			    	responseBean.setMessage("Treatment not deteled.");
			    	responseBean.setStatus(CommonConstants.FAILED);
			    	responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
			    }
			
			tx.commit();
			
		}catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responseBean.setMessage("Failed to delete Treatment");
	        responseBean.setStatus(CommonConstants.FAILED);
	        responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		return responseBean;
	}
	

}
