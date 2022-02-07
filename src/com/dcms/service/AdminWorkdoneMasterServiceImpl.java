package com.dcms.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcms.bean.ResponseBean;
import com.dcms.dao.*;

@Service
public class AdminWorkdoneMasterServiceImpl implements AdminWorkdoneMasterService{
	
	@Autowired
	AdminWorkdoneMasterDao adminWrkMasterDao;

	@Override
	public ResponseBean saveWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterService.saveWorkdoneMaster");
		return adminWrkMasterDao.saveWorkdoneMaster(responseBean);
	}

	@Override
	public ResponseBean getWorkdoneMaster() throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterService.getWorkdoneMaster");
		return adminWrkMasterDao.getWorkdoneMaster();
	}

	@Override
	public ResponseBean updateWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterService.updateWorkdoneMaster");
		return adminWrkMasterDao.updateWorkdoneMaster(responseBean);
	}

	@Override
	public ResponseBean deleteWorkdoneMaster(ResponseBean responseBean) throws SQLException, Exception {
		System.out.println("AdminWorkdoneMasterService.deleteWorkdoneMaster");
		return adminWrkMasterDao.deleteWorkdoneMaster(responseBean);
	}
}
