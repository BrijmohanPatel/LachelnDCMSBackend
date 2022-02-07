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
import com.dcms.modal.TreatmentMaster;
import com.dcms.modal.WorkdoneMaster;
import com.dcms.utilities.HibernateUtil;

@Component
public class AdminWorkdoneMasterDaoImpl implements AdminWorkdoneMasterDao {

	List<WorkdoneMaster> workdoneMasterList = null;
	
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	
	@Override
	public ResponseBean saveWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterDaoImpl.saveWorkdoneMaster()");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	WorkdoneMaster wrk_master = new WorkdoneMaster();
	    	wrk_master.setWork_name(responseBean.getData().getWorkdoneMasterData().getWorkdone_name());
	    	wrk_master.setWork_added_date(sdf3.format(timestamp));
	    	wrk_master.setWork_updated_date(sdf3.format(timestamp));
	    	
	    	 String wrk_master_id = (String)session.save(wrk_master);
	    	 if(wrk_master_id != null) {
	    		 responseBean.setMessage("Work added  successfully");
	    		 responseBean.setStatus(CommonConstants.SUCCESS);
	    		 responseBean.getData().getWorkdoneMasterData().setWd_id( wrk_master_id);
	    		 responseBean.setStatusCode(CommonConstants.STATUSCODE_200); 
	    	 }else {
	    		 responseBean.setMessage("Failed to add Work");
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
	public ResponseBean getWorkdoneMaster() throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterDaoImpl.getWorkdoneMaster()");
		ResponseBean responsebean = new ResponseBean();
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try{
	    	 Query query = session.createQuery("from workdone_master order by work_name desc");
	    	 workdoneMasterList = query.list();
	    	 tx.commit();
			 responsebean.setMessage("Successfully fetched Work Master!");
			 responsebean.setStatus(CommonConstants.SUCCESS);
		     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
		     responsebean.getData().setWorkdoneMasterList(workdoneMasterList);
		       
	    }catch(Exception e) {
	    	System.out.println(e);
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responsebean.setMessage("Failed to get work Master!");
	        responsebean.setStatus(CommonConstants.FAILED);
	        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    }finally {
	    	session.close();
	    }
		return responsebean;
	}

	@Override
	public ResponseBean updateWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterDaoImpl.updateWorkdoneMaster()");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	WorkdoneMaster workdoneMaster = session.load(WorkdoneMaster.class, responseBean.getData().getWorkdoneMasterData().getWd_id());
	    	if(workdoneMaster != null) {
	    		if(responseBean.getData().getWorkdoneMasterData().getWorkdone_name() != null) {
	    			workdoneMaster.setWork_name(responseBean.getData().getWorkdoneMasterData().getWorkdone_name());
	    		}
	    		workdoneMaster.setWork_updated_date(sdf3.format(timestamp));
		    	session.update(workdoneMaster);
		    	responseBean.setStatus(CommonConstants.SUCCESS);
		        responseBean.setStatusCode(CommonConstants.STATUSCODE_200);
		    	responseBean.setMessage("Work Master updated successfully");
	    	}else {
	    		responseBean.setStatus(CommonConstants.FAILED);
	    		responseBean.setMessage("Work Master not updated.");
	    		responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    	}
	    	tx.commit();    	
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        	e.printStackTrace();
	        responseBean.setMessage("Failed to update Work Master");
	        responseBean.setStatus(CommonConstants.FAILED);
	        responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		return responseBean;
	}

	@Override
	public ResponseBean deleteWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterDaoImpl.deleteWorkdoneMaster");
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
		try {
			String wd_id = responseBean.getData().getWorkdoneMasterData().getWd_id();
			WorkdoneMaster wrk_master = session.get(WorkdoneMaster.class, wd_id);
				if(wrk_master != null) {
					session.delete(wrk_master);
					System.out.println("Work Master ID "+wrk_master+" deleted");
					responseBean.setMessage("Work deteled.");
					responseBean.setStatus(CommonConstants.SUCCESS);
					responseBean.setStatusCode(CommonConstants.STATUSCODE_200);
			    }else {
			    	responseBean.setMessage("Work not deteled.");
			    	responseBean.setStatus(CommonConstants.FAILED);
			    	responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
			    }
			
			tx.commit();
			
		}catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	if (tx != null) tx.rollback();
	        e.printStackTrace();
	        responseBean.setMessage("Failed to delete Work");
	        responseBean.setStatus(CommonConstants.FAILED);
	        responseBean.setStatusCode(CommonConstants.STATUSCODE_9000);
	    } finally {
	        session.close();
	    }
		return responseBean;
	}

}
