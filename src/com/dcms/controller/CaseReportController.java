package com.dcms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.CaseReportService;

@Controller
public class CaseReportController {
	
	@Autowired
	CaseReportService caseReportService;
	
	@RequestMapping(value = "/getCaseReport", method = RequestMethod.POST)
	public @ResponseBody ResponseBean getReport(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("CaseReportController.getReport() ::  " + responsebean);
			return caseReportService.getReport(responsebean);
	}
	
	@RequestMapping(value = "/downloadReport", method = RequestMethod.POST)
	public @ResponseBody ResponseBean downloadReport(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("CaseReportController.downloadReport() ::  " + responsebean);
			return caseReportService.downloadReport(responsebean);
	}
	
}
