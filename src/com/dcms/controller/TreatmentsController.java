package com.dcms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.TreatmentService;
import com.google.gson.Gson;

@Controller
public class TreatmentsController {
	
	@Autowired
	TreatmentService treatmentService;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/addTreatment", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addTreatment(@RequestBody ResponseBean responsebean) throws Exception {
		System.out.println("------------------------------------------------------------------------------------");
			System.out.println("RestController.addTreatment() ::  " + responsebean);
			return treatmentService.addTreatment(responsebean);
	}
	
	@RequestMapping(value = "/getTreatments", method = RequestMethod.POST)
	public @ResponseBody ResponseBean getTreatments(@RequestBody ResponseBean responsebean) throws Exception {
		System.out.println("------------------------------------------------------------------------------------");
			System.out.println("RestController.getTreatments() ::  " + responsebean);
			return treatmentService.getTreatments(responsebean);
	}
	
	@RequestMapping(value="/deleteTreatment", method = RequestMethod.POST)
	public @ResponseBody ResponseBean deteleTreatment(@RequestBody ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("TreatmentsController.deteleTreatment() :: "+gson.toJson(responsebean));
		return treatmentService.deleteTreatment(responsebean);
	}
	
	@RequestMapping(value="/updateTreatment", method = RequestMethod.POST)
	public @ResponseBody ResponseBean updateTreatment(@RequestBody ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("TreatmentsController.updateTreatment() :: "+gson.toJson(responsebean));
		return treatmentService.updateTreatment(responsebean);
	}

}
