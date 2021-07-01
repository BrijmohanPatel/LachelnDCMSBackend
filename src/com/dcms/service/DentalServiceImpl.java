package com.dcms.service;

import java.sql.SQLException;
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
	
	
	@Override
	public ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.addTreatmentPlan().");
		return dentaldao.addTreatmentPlan(responsebean);
	}


	@Override
	public ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.searchPatient().");
		return dentaldao.searchPatient(responsebean);
	}


	@Override
	public ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.selectPatient().");
		return dentaldao.selectPatient(responsebean);
	}


	@Override
	public ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.addTreatmentv().");
		return dentaldao.addTreatment(responsebean);
	}


	@Override
	public ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("DentalServiceImpl.getTreatments().");
		return dentaldao.getTreatments(responsebean);
	}

	

}
