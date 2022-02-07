package com.dcms.utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.dcms.bean.*;
import com.dcms.modal.Patients;
import com.dcms.modal.Plans;
import com.dcms.modal.TreatmentPlans;
import com.dcms.modal.Treatments;
import com.google.gson.Gson;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.sun.prism.paint.Color;

public class PDFGenerationUtility_new {
	
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final float cell_Padding = 10f;
	private static final int cell_border = 0;
	static Font value_f = new Font(FontFamily.HELVETICA, 11.0f, Font.NORMAL, BaseColor.BLACK);
	static Font label_f = new Font(FontFamily.HELVETICA, 11.0f, Font.BOLD, BaseColor.BLACK);
	private static String header_image_url;
	private static String consent_image_url;

	Gson gson = new Gson();
	
	public static String generatePDF(ResponseBean responsebean, String fileName, String filePath, String headerImage, String consentImage) throws IOException {
		header_image_url = headerImage;
		consent_image_url = consentImage;
		String filegenerated = GenerateFileNameAndLocation(fileName, filePath);
		System.out.println("filegenerated is "+filegenerated);
		Document document = new Document();
		document.setMargins(1 , 1 , 1 , 1);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filegenerated));
	        document.open();
	        
	        document.add(generateHeader());
	        document.add(generatePatientData(responsebean));
	        document.add(generateTreatmentPlanData(responsebean));
	        document.add(generateTreatmentData(responsebean));
	        
	        document.close();
	        writer.close();
	 
	        System.out.println("PDF is generated !!");        
	        
		}catch (Exception e){
	        e.printStackTrace();
	    }
		return filegenerated;
		
	}
	
	public static PdfPTable generateHeader() {
		PdfPTable table_header = new PdfPTable(1);
		Image image_header = null;
		try {
	        table_header.setWidthPercentage(100); //Width 100%
	        table_header.getDefaultCell().setBorder(0);
	        table_header.setSpacingBefore(0);
	        
	        PdfPCell cell_header = new PdfPCell();
	        cell_header.setPadding(0);
	            
			image_header = Image.getInstance(header_image_url);
			
		}catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		}
            
        table_header.addCell(image_header);
        table_header.setSpacingAfter(12.5f);
        
        return table_header;
	}
	
	
	public static PdfPTable generatePatientData(ResponseBean responsebean) throws MalformedURLException, IOException {
		PdfPTable table_PatientData = new PdfPTable(6);
		table_PatientData.setWidthPercentage(100); //Width 100%
		table_PatientData.setSpacingBefore(12.5f); //Space Before table
		table_PatientData.setSpacingAfter(30f); //Space after table
		table_PatientData.getDefaultCell().setBorder(0);
		
        try {
            float[] table_PatientWidth = {1.5f, 1f, 1f, 1f, 1f, 0.5f};
			table_PatientData.setWidths(table_PatientWidth);
			
			List<Patients> patients= responsebean.getData().getPatientsList();
			
			/* First Row start */
			PdfPCell case_no = new PdfPCell(new Paragraph("Case No:", label_f));
			case_no.setPadding(cell_Padding);
			case_no.setBorder(cell_border);
			case_no.setVerticalAlignment(Element.ALIGN_RIGHT);
			case_no.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(case_no);
						
			PdfPCell case_no_data = new PdfPCell(new Paragraph(patients.get(0).getPatient_id(), value_f));
			case_no_data.setColspan(2);
			case_no_data.setPadding(cell_Padding);
			case_no_data.setBorder(cell_border);
			table_PatientData.addCell(case_no_data);
			
			PdfPCell case_date = new PdfPCell(new Paragraph("Date:", label_f));
			case_date.setPadding(cell_Padding);
			case_date.setBorder(cell_border);
			case_date.setVerticalAlignment(Element.ALIGN_RIGHT);
			case_date.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(case_date);
			
			PdfPCell case_date_value = new PdfPCell(new Paragraph(patients.get(0).getReg_date(), value_f));
			case_date_value.setColspan(2);
			case_date_value.setPadding(cell_Padding);
			case_date_value.setBorder(cell_border);
			table_PatientData.addCell(case_date_value);
			/* First Row End */
			
			/* Second Row Start */			
			PdfPCell patient_name = new PdfPCell(new Paragraph("Patient Name:", label_f));
			patient_name.setPadding(cell_Padding);
			patient_name.setBorder(cell_border);
			patient_name.setVerticalAlignment(Element.ALIGN_RIGHT);
			patient_name.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(patient_name);
			
			PdfPCell patient_name_data = new PdfPCell(new Paragraph(patients.get(0).getPatient_name(), value_f));
			patient_name_data.setPadding(cell_Padding);
			patient_name_data.setColspan(2);
			patient_name_data.setBorder(cell_border);
			table_PatientData.addCell(patient_name_data);
			
			PdfPCell age = new PdfPCell(new Paragraph("Age:", label_f));
			age.setPadding(cell_Padding);
			age.setBorder(cell_border);
			age.setVerticalAlignment(Element.ALIGN_RIGHT);
			age.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(age);
			
			PdfPCell age_data = new PdfPCell(new Paragraph(patients.get(0).getAge()+"", value_f));
			age_data.setPadding(cell_Padding);
			age_data.setBorder(cell_border);
			age_data.setColspan(2);
			table_PatientData.addCell(age_data);
			/* Second Row End */
			
			
			/* Third Row Start */
			PdfPCell gender = new PdfPCell(new Paragraph("Gender:", label_f));
			gender.setPadding(cell_Padding);
			gender.setBorder(cell_border);
			gender.setVerticalAlignment(Element.ALIGN_RIGHT);
			gender.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(gender);
			
			PdfPCell gender_data = new PdfPCell(new Paragraph(patients.get(0).getGender(), value_f));
			gender_data.setPadding(cell_Padding);
			gender_data.setBorder(cell_border);
			gender_data.setColspan(2);
			table_PatientData.addCell(gender_data);
			
			PdfPCell mobile_no = new PdfPCell(new Paragraph("Mobile No:", label_f));
			mobile_no.setPadding(cell_Padding);
			mobile_no.setBorder(cell_border);
			mobile_no.setVerticalAlignment(Element.ALIGN_RIGHT);
			mobile_no.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(mobile_no);
			
			PdfPCell mobile_no_data = new PdfPCell(new Paragraph(patients.get(0).getMobile_number()+"", value_f));
			mobile_no_data.setPadding(cell_Padding);
			mobile_no_data.setBorder(cell_border);
			mobile_no_data.setColspan(2);
			table_PatientData.addCell(mobile_no_data);

			
			/* Third Row End */
			
			/* Fourth Row Start */
			PdfPCell address = new PdfPCell(new Paragraph("Address:", label_f));
			address.setPadding(cell_Padding);
			address.setBorder(cell_border);
			address.setVerticalAlignment(Element.ALIGN_RIGHT);
			address.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(address);
			
			PdfPCell address_data = new PdfPCell(new Paragraph(patients.get(0).getAddress(), value_f));
			address_data.setPadding(cell_Padding);
			address_data.setColspan(5);
			address_data.setBorder(cell_border);
			table_PatientData.addCell(address_data);

			/* Fourth Row End */
			
			/* Fifth Row Start */
			
			PdfPCell occupation = new PdfPCell(new Paragraph("Occupation:", label_f));
			occupation.setPadding(cell_Padding);
			occupation.setVerticalAlignment(Element.ALIGN_RIGHT);
			occupation.setHorizontalAlignment(Element.ALIGN_RIGHT);
			occupation.setBorder(cell_border);
			table_PatientData.addCell(occupation);
			
			PdfPCell occupation_data = new PdfPCell(new Paragraph(patients.get(0).getOccupation()+"", value_f));
			occupation_data.setPadding(cell_Padding);
			occupation_data.setColspan(5);
			occupation_data.setBorder(cell_border);
			table_PatientData.addCell(occupation_data);
			/* Fifth Row End */
			
			/* Sixth Row Start */
			PdfPCell chief_complaint = new PdfPCell(new Paragraph("Chief Complaint:", label_f));
			chief_complaint.setPadding(cell_Padding);
			chief_complaint.setBorder(cell_border);
			chief_complaint.setVerticalAlignment(Element.ALIGN_RIGHT);
			chief_complaint.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(chief_complaint);
			
			PdfPCell chief_complaint_data = new PdfPCell(new Paragraph(patients.get(0).getChief_complaint(), value_f));
			chief_complaint_data.setPadding(cell_Padding);
			chief_complaint_data.setColspan(5);
			chief_complaint_data.setBorder(cell_border);
			table_PatientData.addCell(chief_complaint_data);
			/* Sixth Row End */
			
			/* Seventh Row Start */
			
			PdfPCell medical_history = new PdfPCell(new Paragraph("Medical History:", label_f));
			medical_history.setPadding(cell_Padding);
			medical_history.setBorder(cell_border);
			medical_history.setVerticalAlignment(Element.ALIGN_RIGHT);
			medical_history.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(medical_history);
			
			PdfPCell medical_history_data = new PdfPCell(new Paragraph(patients.get(0).getMedical_history(), value_f));
			medical_history_data.setPadding(cell_Padding);
			medical_history_data.setColspan(5);
			medical_history_data.setBorder(cell_border);
			table_PatientData.addCell(medical_history_data);
			
			/* Seventh Row End */
			
			/*Eight Row Start */
			
			PdfPCell int_oral_exam = new PdfPCell(new Paragraph("Intra Oral Examination:", label_f));
			int_oral_exam.setPadding(cell_Padding);
			int_oral_exam.setBorder(cell_border);
			int_oral_exam.setVerticalAlignment(Element.ALIGN_RIGHT);
			int_oral_exam.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(int_oral_exam);
			
			PdfPCell int_oral_exam_data = new PdfPCell(new Paragraph(patients.get(0).getIntra_oral_examination(), value_f));
			int_oral_exam_data.setPadding(cell_Padding);
			int_oral_exam_data.setColspan(5);
			int_oral_exam_data.setBorder(cell_border);
			table_PatientData.addCell(int_oral_exam_data);
			/*Eight Row End */
			
			
			PdfPCell dummy_cell1 = new PdfPCell(new Paragraph("  ", value_f)); // just dummy cell for space.
			dummy_cell1.setPadding(15f);
			dummy_cell1.setColspan(6);
			dummy_cell1.setBorder(0);
			table_PatientData.addCell(dummy_cell1);
			
			Image image_consent = Image.getInstance(consent_image_url);
			image_consent.scaleAbsolute(159f, 159f);
			PdfPCell image_cell = new PdfPCell();
			image_cell.setImage(image_consent);
			image_cell.setColspan(6);
			image_cell.setFixedHeight(200);
			image_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			image_cell.setVerticalAlignment(Element.ALIGN_CENTER);
			image_cell.setBorder(0);
			table_PatientData.addCell(image_cell);
			
			PdfPCell dummy_cell2 = new PdfPCell(new Paragraph("  ", value_f)); // just dummy cell for space.
			dummy_cell2.setPadding(20f);
			dummy_cell2.setColspan(6);
			dummy_cell2.setBorder(0);
			table_PatientData.addCell(dummy_cell2);
			
			PdfPCell dummy_cell3 = new PdfPCell(new Paragraph("  ", value_f)); // just dummy cell for space.
			dummy_cell3.setPadding(20f);
			dummy_cell3.setColspan(6);
			dummy_cell3.setBorder(0);
			table_PatientData.addCell(dummy_cell3);
			
			
			PdfPCell signature_thump = new PdfPCell(new Paragraph("Patient Sign / Parent Sign", label_f)); // just dummy cell for space.
			signature_thump.setPadding(20f);
			signature_thump.setColspan(6);
			signature_thump.setBorder(0);
			signature_thump.setVerticalAlignment(Element.ALIGN_RIGHT);
			signature_thump.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table_PatientData.addCell(signature_thump);
			
	        
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return table_PatientData;
	}
	
	public static PdfPTable generateTreatmentPlanData(ResponseBean responsebean) throws MalformedURLException, IOException {

		PdfPTable table_treatmentplanData = new PdfPTable(3);
		table_treatmentplanData.setWidthPercentage(100); //Width 100%
		table_treatmentplanData.setSpacingBefore(12.5f); //Space Before table
		table_treatmentplanData.setSpacingAfter(30f); //Space after table
				
		try {			
			float[] table_treatment_width = {1f, 1f, 1f};
			table_treatmentplanData.setWidths(table_treatment_width);
			
			List<TreatmentPlans> treatmentPlans = responsebean.getData().getTreatmentPlanList();
			List<Treatments> treatments = responsebean.getData().getTreatmentsList();
			
			Font f = new Font(FontFamily.HELVETICA, 10.0f, Font.BOLD, BaseColor.BLACK);
			PdfPCell cell_treatmentplanData = new PdfPCell(new Paragraph("TREATMENT PLAN DETAILS", f));
			cell_treatmentplanData.setBorderColor(BaseColor.BLACK);
			cell_treatmentplanData.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_treatmentplanData.setVerticalAlignment(Element.ALIGN_CENTER);
			cell_treatmentplanData.setPadding(10f);	
			cell_treatmentplanData.setColspan(3);
			table_treatmentplanData.addCell(cell_treatmentplanData);
			
			
			table_treatmentplanData.addCell(createColumnsCell("TREATMENT"));
			table_treatmentplanData.addCell(createColumnsCell("TEETH"));
			table_treatmentplanData.addCell(createColumnsCell("ESTIMATED AMOUNT"));
			
			for (TreatmentPlans treatmentplan : treatmentPlans) {
				for (Plans plan : treatmentplan.getPlans()) {
					table_treatmentplanData.addCell(createCell(plan.getTreatment()));
					table_treatmentplanData.addCell(createTrtPlanToothTable(plan));
					table_treatmentplanData.addCell(createCell("₹ "+plan.getEstimatedAmount()));
				}
				
				PdfPCell total_amount = new PdfPCell(new Paragraph("Total Amount", f));
				total_amount.setBorderColor(BaseColor.BLACK);
				total_amount.setHorizontalAlignment(Element.ALIGN_CENTER);
				total_amount.setVerticalAlignment(Element.ALIGN_CENTER);
				total_amount.setPadding(10f);
				total_amount.setColspan(2);
				table_treatmentplanData.addCell(total_amount);
				table_treatmentplanData.addCell(createColumnsCell("₹ "+treatmentplan.getTotal_amount()));
				
				PdfPCell total_paid = new PdfPCell(new Paragraph("Total Paid", f));
				total_paid.setBorderColor(BaseColor.BLACK);
				total_paid.setHorizontalAlignment(Element.ALIGN_CENTER);
				total_paid.setVerticalAlignment(Element.ALIGN_CENTER);
				total_paid.setPadding(10f);
				total_paid.setColspan(2);
				table_treatmentplanData.addCell(total_paid);
				table_treatmentplanData.addCell(createColumnsCell(treatmentplan.getTotal_paid_amount()));
				
				if(treatmentplan.getTotal_paid_amount() != treatmentplan.getTotal_amount()) {
					PdfPCell total_balance = new PdfPCell(new Paragraph("Total Balance", f));
					total_balance.setBorderColor(BaseColor.BLACK);
					total_balance.setHorizontalAlignment(Element.ALIGN_CENTER);
					total_balance.setVerticalAlignment(Element.ALIGN_CENTER);
					total_balance.setPadding(10f);
					total_balance.setColspan(2);
					table_treatmentplanData.addCell(total_balance);
					table_treatmentplanData.addCell(createColumnsCell("₹ "+treatmentplan.getTotal_balance_amount()));
				}
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return table_treatmentplanData;
		
	}
	
	
	public static PdfPTable generateTreatmentData(ResponseBean responsebean) throws MalformedURLException, IOException {
		PdfPTable table_treatmentData = new PdfPTable(6);
		table_treatmentData.setWidthPercentage(100); //Width 100%
		table_treatmentData.setSpacingBefore(20f); //Space Before table
		table_treatmentData.setSpacingAfter(15f); //Space after table
		
		try {			
			float[] table_treatment_width = {1f, 1.5f, 1f, 0.5f, 1f, 1f};
			table_treatmentData.setWidths(table_treatment_width);
			
			List<TreatmentPlans> treatmentPlans = responsebean.getData().getTreatmentPlanList();
			List<Treatments> treatments = responsebean.getData().getTreatmentsList();
			
			Font f = new Font(FontFamily.HELVETICA, 10.0f, Font.BOLD, BaseColor.BLACK);
			PdfPCell cell_treatmentData = new PdfPCell(new Paragraph("TREATMENT DETAILS", f));
			cell_treatmentData.setBorderColor(BaseColor.BLACK);
			cell_treatmentData.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_treatmentData.setVerticalAlignment(Element.ALIGN_CENTER);
			cell_treatmentData.setPadding(10f);	
			cell_treatmentData.setColspan(6);
			table_treatmentData.addCell(cell_treatmentData);
			
			table_treatmentData.addCell(createColumnsCell("TREATMENT DATE"));
			table_treatmentData.addCell(createColumnsCell("WORK DONE"));
			table_treatmentData.addCell(createColumnsCell("UNITS"));
			table_treatmentData.addCell(createColumnsCell("₹ PAID"));
			table_treatmentData.addCell(createColumnsCell("₹ BALANCE "));
			table_treatmentData.addCell(createColumnsCell("SIGNATURE"));
			
			for (Treatments trt_data : treatments) {
				
				table_treatmentData.addCell(createCell(trt_data.getTreatment_date()));
				table_treatmentData.addCell(createCell(trt_data.getWork_done()));
				table_treatmentData.addCell(createTrtToothTable(trt_data));
				table_treatmentData.addCell(createCell("₹ "+trt_data.getPaid_amount()));
				table_treatmentData.addCell(createCell("₹ "+trt_data.getBalance_amount()));
				table_treatmentData.addCell(createCell(trt_data.getWork_done()));
				
								
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return table_treatmentData;
	}
	
	public static String GenerateFileNameAndLocation(String fileName, String filePath) {
		
		fileName = fileName.replaceAll("\\.", "_");
		fileName = fileName.replaceAll(" ", "_").toUpperCase();
		String FileNameAndDest = filePath+fileName+".pdf"; 
		return FileNameAndDest;
	}
	
	public static PdfPCell createColumnsCell(String cellvalue) {
		Font f = new Font(FontFamily.HELVETICA, 11.0f, Font.BOLD, BaseColor.WHITE);
		PdfPCell cell = new PdfPCell(new Paragraph(cellvalue, f));
		cell.setBorderColor(BaseColor.BLACK);
		cell.setBackgroundColor(BaseColor.GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(3f);
        return cell;
	}
	
	public static PdfPCell createCell(String cellvalue) {
		Font f = new Font(FontFamily.HELVETICA, 10.0f, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Paragraph(cellvalue, f));
		cell.setBorderColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5f);
		//cell.setBorder(0);
        return cell;
	}
	
	public static PdfPTable createTrtPlanToothTable(Plans plan) throws DocumentException {
		PdfPTable table_tooth = new PdfPTable(2);
		table_tooth.setWidthPercentage(100); //Width 100%
		table_tooth.getDefaultCell().setBorder(0);
		
		float[] table_tooth_width = {1f, 1f};
		table_tooth.setWidths(table_tooth_width);
		
		PdfPCell ul = new PdfPCell(new Paragraph(plan.getUpperLeftTooth(), label_f));
		ul.setVerticalAlignment(Element.ALIGN_CENTER);
		ul.setHorizontalAlignment(Element.ALIGN_CENTER);
		ul.setBorder(2);
		table_tooth.addCell(ul);
		
		PdfPCell ur = new PdfPCell(new Paragraph(plan.getUpperRightTooth(), label_f));
		ur.setVerticalAlignment(Element.ALIGN_CENTER);
		ur.setHorizontalAlignment(Element.ALIGN_CENTER);
		ur.setBorder(4);
		table_tooth.addCell(ur);
		
		
		PdfPCell ll = new PdfPCell(new Paragraph(plan.getLowerLeftTooth(), label_f));
		ll.setVerticalAlignment(Element.ALIGN_CENTER);
		ll.setHorizontalAlignment(Element.ALIGN_CENTER);
		ll.setBorder(8);
		table_tooth.addCell(ll);
		
		PdfPCell lr = new PdfPCell(new Paragraph(plan.getLowerRightTooth(), label_f));
		lr.setVerticalAlignment(Element.ALIGN_CENTER);
		lr.setHorizontalAlignment(Element.ALIGN_CENTER);
		lr.setBorder(1);
		table_tooth.addCell(lr);
			
		
		table_tooth.setSpacingBefore(10);
		table_tooth.setSpacingAfter(10);
		
		return table_tooth;
	}
	
	
	public static PdfPTable createTrtToothTable(Treatments trt_data) throws DocumentException {
		PdfPTable table_tooth = new PdfPTable(2);
		table_tooth.setWidthPercentage(100); //Width 100%
		table_tooth.getDefaultCell().setBorder(0);
		
		float[] table_tooth_width = {1f, 1f};
		table_tooth.setWidths(table_tooth_width);
		
		PdfPCell ul = new PdfPCell(new Paragraph(trt_data.getUpperLeftTooth(), label_f));
		ul.setVerticalAlignment(Element.ALIGN_CENTER);
		ul.setHorizontalAlignment(Element.ALIGN_CENTER);
		ul.setBorder(2);
		table_tooth.addCell(ul);
		
		PdfPCell ur = new PdfPCell(new Paragraph(trt_data.getUpperRightTooth(), label_f));
		ur.setVerticalAlignment(Element.ALIGN_CENTER);
		ur.setHorizontalAlignment(Element.ALIGN_CENTER);
		ur.setBorder(4);
		table_tooth.addCell(ur);
		
		
		PdfPCell ll = new PdfPCell(new Paragraph(trt_data.getLowerLeftTooth(), label_f));
		ll.setVerticalAlignment(Element.ALIGN_CENTER);
		ll.setHorizontalAlignment(Element.ALIGN_CENTER);
		ll.setBorder(8);
		table_tooth.addCell(ll);
		
		PdfPCell lr = new PdfPCell(new Paragraph(trt_data.getLowerRightTooth(), label_f));
		lr.setVerticalAlignment(Element.ALIGN_CENTER);
		lr.setHorizontalAlignment(Element.ALIGN_CENTER);
		lr.setBorder(1);
		table_tooth.addCell(lr);
					
		table_tooth.setSpacingBefore(10);
		table_tooth.setSpacingAfter(10);
		
		return table_tooth;
	}

}
