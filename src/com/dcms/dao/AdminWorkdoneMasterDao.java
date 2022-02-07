package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;

@Component
public interface AdminWorkdoneMasterDao {
	
	ResponseBean saveWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean getWorkdoneMaster() throws SQLException, Exception;

	ResponseBean updateWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean deleteWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception;

}
