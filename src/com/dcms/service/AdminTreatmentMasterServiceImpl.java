package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.AdminTreatmentMasterDao;


@Service
public class AdminTreatmentMasterServiceImpl implements AdminTreatmentMasterService{
	
	@Autowired
	AdminTreatmentMasterDao adminTrtMasterDao;

	@Override
	public ResponseBean saveTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterService.saveTreatmentMaster");
		return adminTrtMasterDao.saveTreatmentMaster(responseBean);
	}

	@Override
	public ResponseBean getTreatmentMaster() throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterService.getTreatmentMaster");
		return adminTrtMasterDao.getTreatmentMaster();
	}

	@Override
	public ResponseBean updateTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterService.updateTreatmentMaster");
		return adminTrtMasterDao.updateTreatmentMaster(responseBean);
	}

	@Override
	public ResponseBean deleteTreatmentMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminTreatmentMasterService.deleteTreatmentMaster");
		return adminTrtMasterDao.deleteTreatmentMaster(responseBean);
	}

}
