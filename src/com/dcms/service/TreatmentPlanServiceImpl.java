package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.TreatmentPlanDao;


@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService {
	
	@Autowired
	TreatmentPlanDao treatmentPlanDao;
	
	@Override
	public ResponseBean addTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentPlanServiceImpl.addTreatmentPlan().");
		return treatmentPlanDao.addTreatmentPlan(responsebean);
	}
	
	@Override
	public ResponseBean updateTreatmentPlan(ResponseBean responsebean) throws SQLException, Exception {
		System.out.println("TreatmentPlanServiceImpl.updateTreatmentPlan().");
		return treatmentPlanDao.updateTreatmentPlan(responsebean);
	}


}
