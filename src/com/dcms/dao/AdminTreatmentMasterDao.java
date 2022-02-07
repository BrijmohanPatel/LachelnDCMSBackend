package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;

@Component
public interface AdminTreatmentMasterDao {
	
	ResponseBean saveTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean getTreatmentMaster() throws SQLException, Exception;

	ResponseBean updateTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean deleteTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception;

}
