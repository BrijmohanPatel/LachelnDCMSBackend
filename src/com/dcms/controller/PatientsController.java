package com.dcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
			return patientsService.selectPatient(responsebean);
	}
}
