package com.dcms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.AdminTreatmentMasterService;

@Controller
public class AdminTreatmentMasterController {
	
	@Autowired
	AdminTreatmentMasterService adminTrtMasterService;
	
	@RequestMapping(value="saveTreatmentMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean saveTreatmentMaster(@RequestBody ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminTreatmentMasterController.saveTreatmentMaster() ::  " + responseBean);
		responseBean = adminTrtMasterService.saveTreatmentMaster(responseBean);
		return responseBean;
	}
	
	@RequestMapping(value="getTreatmentMaster", method= RequestMethod.GET) 
	public @ResponseBody ResponseBean getTreatmentMaster() throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminTreatmentMasterController.getTreatmentMaster()");
		ResponseBean responseBean = null;
		responseBean =  adminTrtMasterService.getTreatmentMaster();
		return responseBean;
	}
	
	@RequestMapping(value="updateTreatmentMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean updateTreatmentMaster(@RequestBody ResponseBean responseBean)throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminTreatmentMasterController.updateTreatmentMaster() ::  " + responseBean);
		responseBean =  adminTrtMasterService.updateTreatmentMaster(responseBean);
		return responseBean;
	}
	
	@RequestMapping(value="deleteTreatmentMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean deleteTreatmentMaster(@RequestBody ResponseBean responseBean)throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminTreatmentMasterController.deleteTreatmentMaster() ::  " + responseBean);
		responseBean =  adminTrtMasterService.deleteTreatmentMaster(responseBean);
		return responseBean;
	}

}
