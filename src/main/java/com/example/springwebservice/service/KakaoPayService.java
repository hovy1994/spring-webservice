package com.example.springwebservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;


import com.example.springwebservice.domain.KakaoPay.*;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.service.mapper.PaymentMapper;
import com.example.springwebservice.service.mapper.RentMapper;
import com.example.springwebservice.web.PaymentSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPayService {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayCancelVO kakaoPayCancelVO1;
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    @Autowired
    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private RentMapper rentMapper;

    public String kakaoPayReady(RentalRequestInfo info) {
        System.out.println("여기10");
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("여기11");
        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "16dbafbd576dc51ec0cb7a3ade20a721");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        System.out.println("여기15");
        int tid = (int)paymentRepository.count();

        PaymentSaveRequestDto dto =new PaymentSaveRequestDto();
        dto.setTAX_FREE_AMOUNT(0);
        dto.setTOTAL_AMOUNT(info.getTotal_amount());
        dto.setTID(String.valueOf(tid));
        dto.setQUANTITY(1);
        dto.setITEM_NAME(String.valueOf(info.getItem_idx()));
        dto.setSTART_TIME(info.getStart());
        dto.setPARTNER_USER_ID(info.getUser_id());
        dto.setPARTNER_ORDER_ID(info.getUser_id());
        paymentRepository.save(dto.toEntity());

//        Random rand = new Random();
//        String tmp = String.valueOf(tid*10+rand.nextInt(100));
//        while(tmp.length()==10) tmp="0"+tmp;
        //String tmp=String.valueOf(tid*10)+"-"+String.valueOf(rand.nextInt(10000));
        System.out.println("여기19");
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        System.out.println("여기1");
        params.add("partner_order_id", "1004");
        System.out.println("여기2");
        params.add("partner_user_id", info.getUser_id());
        System.out.println("여기3");
        params.add("item_name", String.valueOf(info.getItem_idx()));
        params.add("quantity","1");
        System.out.println("여기4");
        params.add("total_amount", String.valueOf(info.getTotal_amount()));
        System.out.println("여기5");
        params.add("tax_free_amount", "0");


        params.add("approval_url", "http://13.125.236.67:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://13.125.236.67:8080/kakaoCancel");
        params.add("fail_url", "http://13.125.236.67:8080/kakaoFail");

        System.out.println("여기20");
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            System.out.println("여기20-1");
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            System.out.println("여기20-2");
            log.info("" + kakaoPayReadyVO);
            System.out.println("여기21");
            return kakaoPayReadyVO.getNext_redirect_app_url();
        } catch (RestClientException e) {
            System.out.println("여기22");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.out.println("여기23");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("여기24");
        return "/kakaoPay";
    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("1");
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "52fe79625ce67af1245667efbb7ae4de");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid", "TC0ONETIME");

        params.add("tid", kakaoPayReadyVO.getTid());
        System.out.println("kakaoPayReadVO.getTid(): "+kakaoPayReadyVO.getTid());

        params.add("partner_order_id", "1004");
        params.add("partner_user_id", "BEBLET_USER1");
        params.add("pg_token", pg_token);
        params.add("total_amount", "15000");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);


            PaymentSaveRequestDto dto = new PaymentSaveRequestDto();
            dto.setTID(kakaoPayReadyVO.getTid());
            dto.setPARTNER_ORDER_ID(kakaoPayApprovalVO.getPartner_order_id());
            dto.setPARTNER_USER_ID(kakaoPayApprovalVO.getPartner_order_id());
            dto.setITEM_NAME(kakaoPayApprovalVO.getItem_name());
            dto.setQUANTITY(kakaoPayApprovalVO.getQuantity());
            dto.setTOTAL_AMOUNT(kakaoPayApprovalVO.getAmount().getTotal());
            dto.setTAX_FREE_AMOUNT(kakaoPayApprovalVO.getTax_free_amount());

            paymentRepository.save(dto.toEntity());

            return kakaoPayApprovalVO;
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public List<Payment> returnPaymentList(String user_id){
        List<Payment> payment = paymentMapper.findPaymentList(user_id);

        return payment;
    }

    public void applyCancelService(String user_id){
        rentMapper.deleteRent(user_id);
        paymentMapper.updatePayment(user_id);
    }



    public KakaoPayCancelVO kakaoCancelGO(String user_id) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "52fe79625ce67af1245667efbb7ae4de");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        List<Payment> paymentList=paymentMapper.findPaymentList(user_id);
        Payment payment=paymentList.get(0);

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", payment.getTID());
        params.add("cancel_amount", String.valueOf(payment.getTOTAL_AMOUNT()));
        params.add("cancel_tax_free_amount", "0");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayCancelVO1 = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayCancelVO.class);

            log.info("" + kakaoPayCancelVO1);

            System.out.println("aid : " + kakaoPayCancelVO1.aid.toString());
            System.out.println("tid : " + kakaoPayCancelVO1.tid.toString());
            System.out.println("status : " + kakaoPayCancelVO1.status.toString());
            System.out.println("payment_method_type : " + kakaoPayCancelVO1.payment_method_type.toString());
            System.out.println("item_name : " + kakaoPayCancelVO1.item_name.toString());
            System.out.println("quantity : " + Integer.toString(kakaoPayCancelVO1.quantity));
            System.out.println("amount's total : " + Integer.toString(kakaoPayCancelVO1.amount.total));
            System.out.println("canceled_amount total : " + Integer.toString(kakaoPayCancelVO1.canceled_amount.total));
            System.out.println("cancel_available_amount : " + Integer.toString(kakaoPayCancelVO1.cancel_available_amount.total));
            return kakaoPayCancelVO1;
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
