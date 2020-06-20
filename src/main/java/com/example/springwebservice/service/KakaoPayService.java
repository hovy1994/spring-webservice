package com.example.springwebservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;


import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.KakaoPayReadyVO;
import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.KakaoPay.PaymentRepository;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
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


    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    @Autowired
    private PaymentRepository paymentRepository;

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

//        params.add("cid", "TC0ONETIME");
//        params.add("partner_order_id", "1004");
//        params.add("partner_user_id", "BEBLET_USER1");
//        params.add("item_name", "Tablet1");
//        params.add("quantity", "1");
//        params.add("total_amount", "15000");
//        params.add("tax_free_amount", "0");

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
            return kakaoPayReadyVO.getNext_redirect_pc_url();
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

    public Payment paySuccess(String tid){

        List<Payment> paymentList=paymentRepository.findAll();

        for(Payment payment: paymentList){
            if(payment.getTID()==tid){
                return payment;
            }
        }
        return null;
    }

//    public void applyCancelService(String tid){
//        List<Payment> paymentList=paymentRepository.findAll();
//
//        for(Payment payment: paymentList){
//            if(payment.getTID()==tid){
//
//            }
//        }
//    }
}
