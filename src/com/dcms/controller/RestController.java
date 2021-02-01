package com.dcms.controller;

import java.io.*;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.*;
import com.dcms.service.*;
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
			ResponseBean responsebean = new ResponseBean();
			System.out.println("RestController.getResponseFormat() ::  " + responsebean);
			return responsebean;
	}
	
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addPatient() throws Exception {
			ResponseBean responsebean = new ResponseBean();
			System.out.println("RestController.addPatient() ::  " + responsebean);
			//return dentalservice.addPatient(responsebean);
			return responsebean;
	}

}
