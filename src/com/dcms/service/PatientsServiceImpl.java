package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.PatientsDao;
import com.dcms.dao.PatientsDaoImpl;


@Service
public class PatientsServiceImpl implements PatientsService{

	
	@Autowired
	PatientsDao patientsDao;
	
	@Override
	public ResponseBean addPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.addPatient().");
		return patientsDao.addPatient(responsebean);
	}
	
	@Override
	public ResponseBean searchPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.searchPatient().");
		return patientsDao.searchPatient(responsebean);
	}


	@Override
	public ResponseBean selectPatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.selectPatient().");
		return patientsDao.selectPatient(responsebean);
	}

	@Override
	public ResponseBean getAllPatients() throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.getAllPatients().");
		ResponseBean  rb =  patientsDao.getAllPatients();
		return rb;
	}

	@Override
	public ResponseBean updatePatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.updatePatient().");
		return patientsDao.updatePatient(responsebean);
	}

	@Override
	public ResponseBean deletePatient(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("PatientsServiceImpl.deletePatient().");
		return patientsDao.deletePatient(responsebean);
	}
	
}
