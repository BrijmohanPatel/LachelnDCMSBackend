package com.dcms.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.dcms.bean.ResponseBean;
import com.dcms.bean.TreatmentPlanData;
import com.dcms.constants.CommonConstants;
import com.dcms.modal.Patients;
import com.dcms.modal.Plans;
import com.dcms.modal.TreatmentPlans;
import com.dcms.modal.Treatments;
import com.dcms.utilities.HibernateUtil;
import com.google.gson.Gson;

@Component
public class TreatmentDaoImpl implements TreatmentDao {

    Gson gson = new Gson();
    List < Patients > patientsList = null;
    List < TreatmentPlans > treatmentPlanList = null;
    List < Plans > plans = null;
    List < Treatments > treatmentList = null;
    TreatmentPlanData treatmentPlans = new TreatmentPlanData();
    
    
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




    @Override
    public ResponseBean addTreatment(ResponseBean responsebean) throws SQLException, Exception {
        System.out.println("TreatmentDaoImpl.addTreatment()");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Treatments treatment = new Treatments();
            treatment.setPatient_id(responsebean.getData().getTreatmentData().getPatient_id());
            treatment.setTp_id(responsebean.getData().getTreatmentData().getTp_id());
            treatment.setWork_done(responsebean.getData().getTreatmentData().getWork_done());
            treatment.setTreatment_date(responsebean.getData().getTreatmentData().getTreatment_date());
            treatment.setPaid_amount(responsebean.getData().getTreatmentData().getPaid_amount());
            treatment.setBalance_amount(responsebean.getData().getTreatmentData().getBalance_amount());
            treatment.setPayment_on_date(responsebean.getData().getTreatmentData().getPayment_on_date());
            treatment.setUpperLeftTooth(responsebean.getData().getTreatmentData().getToothData().getUpperLeftTooth());
            treatment.setUpperRightTooth(responsebean.getData().getTreatmentData().getToothData().getUpperRightTooth());
            treatment.setLowerLeftTooth(responsebean.getData().getTreatmentData().getToothData().getLowerLeftTooth());
            treatment.setLowerRightTooth(responsebean.getData().getTreatmentData().getToothData().getLowerRightTooth());
            treatment.setTreatment_added_date(sdf3.format(timestamp));
            session.save(treatment);


            if (responsebean.getData().getTreatmentPlanData() != null && !responsebean.getData().getTreatmentPlanData().getStatus().isEmpty() && responsebean.getData().getTreatmentPlanData().getStatus().equals("CLOSED")) {
                TreatmentPlans tp = new TreatmentPlans();
                tp = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentData().getTp_id());
                tp.setTotal_balance_amount(responsebean.getData().getTreatmentData().getBalance_amount());
                tp.setTotal_paid_amount(responsebean.getData().getTreatmentData().getPaid_amount());
                tp.setStatus(responsebean.getData().getTreatmentPlanData().getStatus()); //diff
                session.update(tp);
            } else {
                TreatmentPlans tp = new TreatmentPlans();
                tp = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentData().getTp_id());
                tp.setTotal_balance_amount(responsebean.getData().getTreatmentData().getBalance_amount());
                tp.setTotal_paid_amount(responsebean.getData().getTreatmentData().getPaid_amount());
                session.update(tp);
            }
            
            Query query_trt_plan = session.createQuery("from treatment_plans where tp_id =:tp_id");
            query_trt_plan.setParameter("tp_id", responsebean.getData().getTreatmentData().getTp_id());
            treatmentPlanList = query_trt_plan.list();

            System.out.println("treatmentPlanList data ::" + gson.toJson(treatmentPlanList));
            
            treatmentPlans.setTp_id(responsebean.getData().getTreatmentData().getTp_id());
            treatmentPlans.setStatus(responsebean.getData().getTreatmentPlanData().getStatus());


            tx.commit();

            responsebean.setMessage("Treatment added  successfully");
            responsebean.setStatus(CommonConstants.SUCCESS);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
            responsebean.getData().setTreatmentPlanData(treatmentPlans);
			responsebean.getData().setTreatmentPlanList(treatmentPlanList);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            responsebean.setMessage("Failed to add treatment");
            responsebean.setStatus(CommonConstants.FAILED);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
        } finally {
            session.close();
        }
        return responsebean;
    }

    @Override
    public ResponseBean getTreatments(ResponseBean responsebean) throws SQLException, Exception {
        System.out.println("TreatmentDaoImpl.getTreatments()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {

            System.out.println("responsebean.getData().getTreatmentData().getTp_id()" + responsebean.getData().getTreatmentData().getTp_id());

            Query query = session.createQuery("from treatments where tp_id =:tp_id_param order by treatment_added_date asc");
            query.setParameter("tp_id_param", responsebean.getData().getTreatmentData().getTp_id());
            treatmentList = query.list();

            System.out.println("treatmentList data ::" + gson.toJson(treatmentList));

            Query query_trt_plan = session.createQuery("from treatment_plans where tp_id =:tp_id");
            query_trt_plan.setParameter("tp_id", responsebean.getData().getTreatmentData().getTp_id());
            treatmentPlanList = query_trt_plan.list();

            System.out.println("treatmentPlanList data ::" + gson.toJson(treatmentPlanList));

            responsebean.setMessage("Treatment fetched  successfully");
            responsebean.getData().setTreatmentsList(treatmentList);
            responsebean.getData().setTreatmentPlanList(treatmentPlanList);


            responsebean.setStatus(CommonConstants.SUCCESS);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            responsebean.setMessage("Failed to fetch treatment");
            responsebean.setStatus(CommonConstants.FAILED);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
        } finally {
            session.close();
        }
        return responsebean;
    }

    @SuppressWarnings("unused")
    @Override
    public ResponseBean deleteTreatment(ResponseBean responsebean) throws SQLException, Exception {
        System.out.println("TreatmentDaoImpl.deleteTreatment");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.println("transaction started..");
            Treatments treatments = session.get(Treatments.class, responsebean.getData().getTreatmentData().getT_id());
            if (treatments != null) {
                System.out.println("inside treatment that means treatment is present.");
                session.delete(treatments);
                System.out.println("Treatment is deleted.");

                Query query = session.createQuery("from treatments where tp_id =:tp_id_param order by treatment_added_date asc");
                query.setParameter("tp_id_param", responsebean.getData().getTreatmentData().getTp_id());
                treatmentList = query.list();
                System.out.println("treatmentList is fetched :" + treatmentList);
                TreatmentPlans trtplan = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentData().getTp_id());
                if (trtplan != null && treatmentList.size() > 0) {
                    System.out.println("Inside trtplan if condition");
                    treatmentList = deleteTreamentValuesManager(treatmentList, trtplan.getTotal_amount());
                    for (Treatments trt: treatmentList) {
                        System.out.println("Inside for each loop" + gson.toJson(trt));
                        session.update(trt);
                    }
                    
                    trtplan.setTotal_balance_amount(treatmentList.get(treatmentList.size() - 1).getBalance_amount());
                    trtplan.setTotal_paid_amount(treatmentList.get(treatmentList.size() - 1).getPaid_amount());
                    session.update(trtplan);
                    
                } else if(trtplan != null && treatmentList.size() == 0) {
                	
                    trtplan.setTotal_balance_amount(trtplan.getTotal_amount());
                    trtplan.setTotal_paid_amount("0");
                    session.update(trtplan);
                	
                } else if (trtplan == null && treatmentList.size() <= 0) {
                    responsebean.setMessage("Treatment List not present in DB.");
                    responsebean.setStatus(CommonConstants.FAILED);
                    responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
                }
               
                System.out.println("deleted treatments is " + treatments);
                responsebean.setMessage("Treatment deteled and values updated.");
                responsebean.setStatus(CommonConstants.SUCCESS);
                responsebean.setStatusCode(CommonConstants.STATUSCODE_200);
                
                
            } else {
                responsebean.setMessage("Treatment not present in DB.");
                responsebean.setStatus(CommonConstants.FAILED);
                responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
            }

            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (tx != null) tx.rollback();
            e.printStackTrace();
            responsebean.setMessage("Failed to delete treatment");
            responsebean.setStatus(CommonConstants.FAILED);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
        } finally {
            session.close();
        }
        return responsebean;
    }


    public List < Treatments > deleteTreamentValuesManager(List < Treatments > oldList, String totalAmount) {
        List < Treatments > newList = oldList;
        Long totalPaid = 0L;
        Long totalBalance = 0L;

        for (int i = 0; i < oldList.size(); i++) {
            totalPaid = totalPaid + Long.parseLong(oldList.get(i).getPayment_on_date());
            newList.get(i).setPaid_amount(totalPaid.toString());

            totalBalance = Long.parseLong(totalAmount) - totalPaid;
            newList.get(i).setBalance_amount(totalBalance.toString());
        }
        return newList;
    }

    @Override
    public ResponseBean updateTreatment(ResponseBean responsebean) throws SQLException, Exception {
        System.out.println("TreatmentDaoImpl.updateTreatment");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.println("transaction started..");

            Treatments treatments = session.get(Treatments.class, responsebean.getData().getTreatmentData().getT_id());
            if (treatments != null) {
                System.out.println("inside treatment that means treatment is present.");
                treatments.setTreatment_date(responsebean.getData().getTreatmentData().getTreatment_date());
                treatments.setWork_done(responsebean.getData().getTreatmentData().getWork_done());
                treatments.setLowerLeftTooth(responsebean.getData().getTreatmentData().getToothData().getLowerLeftTooth());
                treatments.setLowerRightTooth(responsebean.getData().getTreatmentData().getToothData().getLowerRightTooth());
                treatments.setUpperLeftTooth(responsebean.getData().getTreatmentData().getToothData().getUpperLeftTooth());
                treatments.setUpperRightTooth(responsebean.getData().getTreatmentData().getToothData().getUpperRightTooth());
                treatments.setBalance_amount(responsebean.getData().getTreatmentData().getBalance_amount());
                treatments.setPaid_amount(responsebean.getData().getTreatmentData().getPaid_amount());
                treatments.setPayment_on_date(responsebean.getData().getTreatmentData().getPayment_on_date());
                session.update(treatments);

                Query query = session.createQuery("from treatments where tp_id =:tp_id_param order by treatment_added_date asc");
                query.setParameter("tp_id_param", responsebean.getData().getTreatmentData().getTp_id());
                treatmentList = query.list();
                System.out.println("treatmentList is fetched :" + treatmentList);
                TreatmentPlans trtplan = session.get(TreatmentPlans.class, responsebean.getData().getTreatmentData().getTp_id());
                if (trtplan != null && treatmentList.size() > 0) {
                    System.out.println("Inside trtplan if condition");
                    treatmentList = deleteTreamentValuesManager(treatmentList, trtplan.getTotal_amount());
                    for (Treatments trt: treatmentList) {
                        System.out.println("Inside for each loop" + gson.toJson(trt));
                        session.update(trt);
                    }
                    trtplan.setTotal_balance_amount(treatmentList.get(treatmentList.size() - 1).getBalance_amount());
                    trtplan.setTotal_paid_amount(treatmentList.get(treatmentList.size() - 1).getPaid_amount());
                    session.update(trtplan);
                    
                    System.out.println("updated treatments is " + treatments);
                    responsebean.setMessage("Treatment updated successfully.");
                    responsebean.setStatus(CommonConstants.SUCCESS);
                    responsebean.setStatusCode(CommonConstants.STATUSCODE_200);

                } else if (trtplan == null && treatmentList.size() <= 0) {
                    responsebean.setMessage("Treatment List not present in DB.");
                    responsebean.setStatus(CommonConstants.FAILED);
                    responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
                } 
            } else {
                responsebean.setMessage("Treatment not present in DB.");
                responsebean.setStatus(CommonConstants.FAILED);
                responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (tx != null) tx.rollback();
            e.printStackTrace();
            responsebean.setMessage("Failed to update treatment");
            responsebean.setStatus(CommonConstants.FAILED);
            responsebean.setStatusCode(CommonConstants.STATUSCODE_9000);
        } finally {
            session.close();
        }
        return responsebean;
    }



}