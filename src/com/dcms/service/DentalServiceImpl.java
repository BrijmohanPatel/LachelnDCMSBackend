package com.dcms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.*;
import com.dcms.dao.*;


@Service
public class DentalServiceImpl implements DentalService {
	
	@Autowired
	DentalDao dentaldao;
	
	
	@Override
	public ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.addPatient().");
		return dentaldao.addPatient(responsebean);
	}

	

}
