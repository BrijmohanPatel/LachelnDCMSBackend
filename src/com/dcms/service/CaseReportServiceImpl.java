package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.CaseReportDao;


@Service
public class CaseReportServiceImpl implements CaseReportService{

	@Autowired
	CaseReportDao caseReportDao;

	@Override
	public ResponseBean getReport(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("CaseReportServiceImpl.getReport");
		return caseReportDao.getReport(responseBean);
	}

	@Override
	public ResponseBean downloadReport(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("CaseReportServiceImpl.downloadReport");
		return caseReportDao.downloadReport(responseBean);
	}

}
