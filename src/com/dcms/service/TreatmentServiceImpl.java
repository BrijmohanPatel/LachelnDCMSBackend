package com.dcms.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.TreatmentDao;

@Service
public class TreatmentServiceImpl implements TreatmentService{
		
	@Autowired
	TreatmentDao treatmentdao;
	

	@Override
	public ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentServiceImpl.addTreatmentv().");
		return treatmentdao.addTreatment(responsebean);
	}


	@Override
	public ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentServiceImpl.getTreatments().");
		return treatmentdao.getTreatments(responsebean);
	}


	@Override
	public ResponseBean deleteTreatment(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentServiceImpl.deleteTreatment().");
		return treatmentdao.deleteTreatment(responsebean);
	}


	@Override
	public ResponseBean updateTreatment(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentServiceImpl.updateTreatment().");
		return treatmentdao.updateTreatment(responsebean);
	}
	
}
