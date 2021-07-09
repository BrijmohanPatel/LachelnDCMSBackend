package com.dcms.controller;

import java.lang.annotation.Annotation;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.TreatmentPlanService;
import com.google.gson.Gson;


@Controller
public class TreatmentPlanController {
	
	@Autowired
	TreatmentPlanService treatmentPlanService;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/addTreatmentPlan", method = RequestMethod.POST)
	public @ResponseBody ResponseBean addTreatmentPlan(@RequestBody ResponseBean responsebean) throws Exception {
			System.out.println("TreatmentPlanController.addTreatmentPlan() ::  " + gson.toJson(responsebean));
			return treatmentPlanService.addTreatmentPlan(responsebean);
	}
	
	@RequestMapping(value="/updateTreatmentPlan", method= RequestMethod.POST)
	public @ResponseBody ResponseBean UpdateTreatmentPlan(@RequestBody ResponseBean responsebean) throws Exception {
		System.out.println("TreatmentPlanController.UpdateTreatmentPlan() :: "+gson.toJson(responsebean));
		return treatmentPlanService.updateTreatmentPlan(responsebean);
	}
	
	
	@RequestMapping(value="/deleteTreatmentPlan", method = RequestMethod.POST)
	public @ResponseBody ResponseBean deteleTreatmentPlan(@RequestBody ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentPlanController.deteleTreatmentPlan() :: "+gson.toJson(responsebean));
		return treatmentPlanService.deteleTreatmentPlan(responsebean);
	}
	
}
