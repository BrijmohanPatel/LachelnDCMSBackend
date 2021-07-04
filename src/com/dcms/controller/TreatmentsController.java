package com.dcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.TreatmentService;

public class TreatmentsController {
	
	@Autowired
	TreatmentService treatmentService;
	
	
	@RequestMapping(value = "/addTreatment", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addTreatment(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.addTreatment() ::  " + responsebean);
			return treatmentService.addTreatment(responsebean);
	}
	
	@RequestMapping(value = "/getTreatments", method = RequestMethod.POST)
	public @ResponseBody ResponseBean getTreatments(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("RestController.getTreatments() ::  " + responsebean);
			return treatmentService.getTreatments(responsebean);
	}

}
