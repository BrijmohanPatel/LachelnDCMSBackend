package com.dcms.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcms.bean.ResponseBean;
import com.dcms.service.AdminWorkdoneMasterService;

@Controller
public class AdminWorkdoneMasterController {
	
	@Autowired
	AdminWorkdoneMasterService adminWrkMasterService;
	
	@RequestMapping(value="saveWorkdoneMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean saveWorkdoneMaster(@RequestBody ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminWorkdoneMasterController.saveWorkdoneMaster() ::  " + responseBean);
		responseBean = adminWrkMasterService.saveWorkdoneMaster(responseBean);
		return responseBean;
	}
	
	@RequestMapping(value="getWorkdoneMaster", method= RequestMethod.GET) 
	public @ResponseBody ResponseBean getWorkdoneMaster() throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminWorkdoneMasterController.getWorkdoneMaster()");
		ResponseBean responseBean = null;
		responseBean =  adminWrkMasterService.getWorkdoneMaster();
		return responseBean;
	}
	
	@RequestMapping(value="updateWorkdoneMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean updateWorkdoneMaster(@RequestBody ResponseBean responseBean)throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminWorkdoneMasterController.updateWorkdoneMaster() ::  " + responseBean);
		responseBean =  adminWrkMasterService.updateWorkdoneMaster(responseBean);
		return responseBean;
	}
	
	@RequestMapping(value="deleteWorkdoneMaster", method= RequestMethod.POST) 
	public @ResponseBody ResponseBean deleteWorkdoneMaster(@RequestBody ResponseBean responseBean)throws SQLException, Exception {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("AdminWorkdoneMasterController.deleteWorkdoneMaster() ::  " + responseBean);
		responseBean =  adminWrkMasterService.deleteWorkdoneMaster(responseBean);
		return responseBean;
	}


}
