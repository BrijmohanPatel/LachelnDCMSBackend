package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;

@Service
public interface AdminWorkdoneMasterService {
	
	ResponseBean saveWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean getWorkdoneMaster() throws SQLException, Exception;

	ResponseBean updateWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean deleteWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;
	

}
