package com.example.springwebservice.service;

import java.net.URI;
import java.net.URISyntaxException;


import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.KakaoPayReadyVO;
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

    //    @PostMapping(path = "/getKakaoAuth")
//    public String echoKakao(@RequestBody String res) {
//        MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
//
//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(res);
//
//        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//        //JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//        String id = element.getAsJsonObject().get("id").getAsString();
//        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//        //String email = kakao_account.getAsJsonObject().get("email").getAsString();
//
//        memberSaveRequestDto.setUSER_ID(String.valueOf(id));
//        memberSaveRequestDto.setUSER_NICKNAME(nickname);
//        memberSaveRequestDto.setUSER_NAME("name");
//        memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//        memberSaveRequestDto.setUSER_PW("1232");
//
//        memberRepository.save(memberSaveRequestDto.toEntity());
//        //System.out.println("id : " + res.getId());
//
//        return "nickname : " + nickname;
//    }

    public String kakaoPayReady(KakaoPayApprovalVO approvalVO) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "52fe79625ce67af1245667efbb7ae4de");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", approvalVO.getCid());
        params.add("partner_order_id", approvalVO.getPartner_order_id());
        params.add("partner_user_id", approvalVO.getPartner_user_id());
        params.add("item_name", approvalVO.getItem_name());
        params.add("quantity", String.valueOf(approvalVO.getQuantity()));
        params.add("total_amount", String.valueOf(approvalVO.getAmount().getTotal()));
        params.add("tax_free_amount", String.valueOf(approvalVO.getAmount().getTax_free()));
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoCancel");
        params.add("fail_url", "http://localhost:8080/kakaoFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";
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
        //params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        //params.add("partner_order_id", "1004");
        //params.add("partner_user_id", "BEBLET_USER1");
        //params.add("pg_token", pg_token);
        //params.add("total_amount", "15000");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);
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
}
