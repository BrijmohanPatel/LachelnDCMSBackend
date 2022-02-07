package com.report;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import com.dcms.bean.ResponseBean;
import com.dcms.constants.CommonConstants;
import com.dcms.modal.Patients;
import com.dcms.modal.Plans;
import com.dcms.modal.TreatmentPlans;
import com.dcms.modal.Treatments;
import com.dcms.utilities.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class mainClass {
	
	  static List<Patients> patientsList = null;
	  static List<TreatmentPlans> treatmentPlanList = null;
	  List<Plans> plans = null;
	  static List<Treatments> treatmentsList = null;
	  private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	  
	
	public static void main(String[] args) throws IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String fileName = "Raj_UNQ00355_TP01802"+"_"+sdf3.format(timestamp);
		String filePath = "D:/Installed_Softwares/apache-tomcat-8.5.68-windows-x64/apache-tomcat-8.5.68/webapps/Unique_Dental_Backend/Case_Reports/generatedReports/";
		String headerImage= "D:/Installed_Softwares/apache-tomcat-8.5.68-windows-x64/apache-tomcat-8.5.68/webapps/Unique_Dental_Backend/Case_Reports/Required_docs/unique_Dental_logo.png";
		String consentImage = "D:/Installed_Softwares/apache-tomcat-8.5.68-windows-x64/apache-tomcat-8.5.68/webapps/Unique_Dental_Backend/Case_Reports/Required_docs/consent.png";
		System.out.println(PDFGenerationUtility_new.generatePDF(getLoadedResponse(), fileName, filePath, headerImage, consentImage));
	}
	
	
	public static ResponseBean getLoadedResponse() {		
		ResponseBean responsebean = new ResponseBean();
		try {
	         JsonReader reader = new JsonReader(new FileReader("D:/Installed_Softwares/apache-tomcat-8.5.68-windows-x64/apache-tomcat-8.5.68/webapps/Unique_Dental_Backend/LachelnDCMSBackend/src/com/dcms/Json_data/ResponseJson.json"));
	         
	         Gson gson = new GsonBuilder().create(); 
	         
	         responsebean = gson.fromJson(reader, ResponseBean.class);
	         System.out.println("response Data received is "+ new Gson().toJson(responsebean));
	         responsebean.getData().setTreatmentPlanList(responsebean.getData().getTreatmentPlanList());
	         responsebean.getData().setPatientsList(responsebean.getData().getPatientsList());
	         responsebean.getData().setTreatmentsList(responsebean.getData().getTreatmentsList());
		     responsebean.setStatus(CommonConstants.SUCCESS);
		     responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
			
		}catch(Exception e) {
	         e.printStackTrace();
	    }
		
		return responsebean;
		
	}

}
