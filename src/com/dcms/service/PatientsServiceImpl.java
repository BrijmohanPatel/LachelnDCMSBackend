package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.PatientsDaoImpl;


@Service
public class PatientsServiceImpl implements PatientsService{

	
	@Autowired
	PatientsDaoImpl patientsDaoImpl;
	
	@Override
	public ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.addPatient().");
		return patientsDaoImpl.addPatient(responsebean);
	}
	
	@Override
	public ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.searchPatient().");
		return patientsDaoImpl.searchPatient(responsebean);
	}


	@Override
	public ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.selectPatient().");
		return patientsDaoImpl.selectPatient(responsebean);
	}
	
}
