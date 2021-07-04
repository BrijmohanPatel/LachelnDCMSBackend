package com.dcms.controller;

import java.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dcms.bean.*;

@Controller
public class RestController {
	

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
	
}
