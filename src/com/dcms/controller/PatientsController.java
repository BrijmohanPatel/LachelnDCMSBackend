package com.dcms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.PatientsService;

@Controller
public class PatientsController {
	
	@Autowired
	PatientsService patientsService;
	
	@Value("${dateFormat}")
	String dateFormat;
	
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("PatientsController.addPatient() ::  " + responsebean);
			return patientsService.addPatient(responsebean);
	}
	
	@RequestMapping(value = "/searchPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean searchPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("PatientsController.searchPatient() ::  " + responsebean);
			return patientsService.searchPatient(responsebean);
	}
	
	@RequestMapping(value = "/selectPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean selectPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("PatientsController.selectPatient() ::  " + responsebean);
			System.out.println("dateFormat is"+dateFormat);
			return patientsService.selectPatient(responsebean);
	}
	
	@RequestMapping(value = "/getAllPatients", method = RequestMethod.GET)
	public @ResponseBody ResponseBean getAllPatients() throws Exception {
			System.out.println("PatientsController.getAllPatients() called");
			return patientsService.getAllPatients();
	}
	
	@RequestMapping(value="/updatePatient", method= RequestMethod.POST)
	public @ResponseBody ResponseBean updatePatient(@RequestBody ResponseBean responsebean) throws Exception {
		System.out.println("PatientsController.updatePatient() :: ");
		return patientsService.updatePatient(responsebean);
	}
	
	
	@RequestMapping(value="/deletePatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean deletePatient(@RequestBody ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsController.deletePatient() :: ");
		return patientsService.deletePatient(responsebean);
	}
	
	
}
