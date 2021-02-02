package com.dcms.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.dcms.bean. * ;
import com.dcms.modal.*;
import com.dcms.utilities.*;
import com.dcms.constants.*;

import com.google.gson.Gson;

@Component
public class DentalDaoImpl implements DentalDao {
 
  Gson gson = new Gson();


  @Override
  public ResponseBean addPatient(ResponseBean responsebean) {
    System.out.println("DentalDaoImpl.addPatient()");
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    Patients patient = new Patients();
    
    try {
    	patient.setReg_date(responsebean.getData().getPatientdata().getReg_date());
        patient.setPatient_id(responsebean.getData().getPatientdata().getPatient_id());
        patient.setPatient_name(responsebean.getData().getPatientdata().getPatient_name());
        patient.setMobile_number(responsebean.getData().getPatientdata().getMobile_number());
        patient.setAddress(responsebean.getData().getPatientdata().getAddress());
        patient.setGender(responsebean.getData().getPatientdata().getGender());
        patient.setAge(responsebean.getData().getPatientdata().getAge());        
        patient.setOccupation(responsebean.getData().getPatientdata().getOccupation());
        patient.setMedical_history(responsebean.getData().getPatientdata().getMedical_history());
        patient.setChief_complaint(responsebean.getData().getPatientdata().getChief_complaint());
        session.save(patient);
        tx.commit();
        responsebean.setMessage("Patient added successfully");
        responsebean.setStatus(CommonConstants.SUCCESS);
        responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
        
    } catch(Exception e) {
        e.printStackTrace();
        responsebean.setMessage("Failed to add patient");
        responsebean.setStatus(CommonConstants.FAILED);
        responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
    } finally {
        session.close();
    }  
    
    return responsebean;
  }

}