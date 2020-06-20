package com.example.cancelproject;

import java.net.URI;
import java.net.URISyntaxException;

import com.example.cancelproject.KakaoPayCancelVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
public class kakaoCancel {
    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayCancelVO kakaoPayCancelVO1;

    public KakaoPayCancelVO kakaoCancelGO() {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "52fe79625ce67af1245667efbb7ae4de");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", "T2774441757150778911");
        params.add("cancel_amount", "200");
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