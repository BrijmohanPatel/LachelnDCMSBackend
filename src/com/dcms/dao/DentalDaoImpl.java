package com.dcms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.boot.internal.ParsedPersistenceXmlDescriptor;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dcms.bean. * ;
import com.designerz.constants.CommonConstants;

import com.google.gson.Gson;

@Component
public class DentalDaoImpl implements DentalDao {

  private final String DEST;
  private final String USER_NAME;
  private final String PASSWORD;
  private final String EXPIRY_DATE;

  //static SessionFactory factory = null;

  @Autowired
  public DentalDaoImpl(@Value("${DEST}") String dest, @Value("${USER_NAMES}") String userName, @Value("${PASSWORD}") String password, @Value("${EXPIRY_DATE}") String expiry_date) {
    this.DEST = dest;
    this.USER_NAME = userName;
    this.PASSWORD = password;
    this.EXPIRY_DATE = expiry_date;
  }

 
  Gson gson = new Gson();

  /*
     * Session session = null; SessionFactory sessionFactory; Transaction tx = null;
     */
  @Override
  public ResponseBean addPatient(ResponseBean responsebean) {
    System.out.println("DentalDaoImpl.addPatient()::");
        return responsebean;
  }

}