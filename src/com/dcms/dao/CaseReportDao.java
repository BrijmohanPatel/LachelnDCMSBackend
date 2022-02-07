package com.dcms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;

@Component
public interface CaseReportDao {

	ResponseBean getReport(ResponseBean responseBean) throws SQLException, Exception;

	ResponseBean downloadReport(ResponseBean responseBean) throws SQLException, Exception;

}
