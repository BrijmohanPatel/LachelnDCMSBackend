package com.dcms.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;

@Service
public interface CaseReportService {

	ResponseBean getReport(ResponseBean responsebean) throws SQLException, Exception;

	ResponseBean downloadReport(ResponseBean responsebean) throws SQLException, Exception;

}
