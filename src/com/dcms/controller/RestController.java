package com.dcms.controller;

import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dcms.bean.*;
import com.dcms.service.DentalService;

@Controller
public class RestController {
	
	@Autowired
	DentalService dentalservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String show(ModelMap model) throws IOException {
		try {
			System.out.println("Dental Clinic project is Started");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping(value = "/getResponseFormat", method = RequestMethod.GET)
	public @ResponseBody ResponseBean getResponseFormat() throws Exception {
			ResponseBean rb = new ResponseBean();
			System.out.println("RestController.getResponseFormat() ::  " + rb);
			return rb;
	}
	
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.addPatient() ::  " + responsebean);
			return dentalservice.addPatient(responsebean);
	}
	
	@RequestMapping(value = "/addTreatmentPlan", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addTreatmentPlan(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.addTreatmentPlan() ::  " + responsebean);
			return dentalservice.addTreatmentPlan(responsebean);
	}
	
	@RequestMapping(value = "/searchPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean searchPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.searchPatient() ::  " + responsebean);
			return dentalservice.searchPatient(responsebean);
	}
	
	@RequestMapping(value = "/selectPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean selectPatient(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.selectPatient() ::  " + responsebean);
			return dentalservice.selectPatient(responsebean);
	}

}
