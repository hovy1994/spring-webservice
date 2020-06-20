package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.KakaoPayCancelVO;
import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.KakaoPay.PaymentRepository;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.service.KakaoPayService;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.service.mapper.MemberMapper;
import com.example.springwebservice.service.mapper.PaymentMapper;
import com.example.springwebservice.service.mapper.RentMapper;
import com.example.springwebservice.web.PaymentSaveRequestDto;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;

@Log
@Transactional(readOnly = false)
@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {

    @Autowired
    private MemberMapper memberMapper;
    private ItemMapper itemMapper;
    private PaymentMapper paymentMapper;
    private RentMapper rentMapper;
    private RentalService rentalService;
    private KakaoPayService kakaoPayService;
    private RentRepository rentRepository;
    private PaymentRepository paymentRepository;


    // 사용자가 대여할 물품과 시간을 선택하면 이용 가능 캐비넷과 추천 캐비넷 정보(리스트) 반환
    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public ArrayList<Cabinet> recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());
        System.out.println("End time : " + info.getEnd());

        return rentalService.findCabinet(info);
    }

    // 카카오페이 결제 요청
    @PostMapping(path = "/kakaoPay")
    @GetMapping(path = "/kakaoPay")
    public String kakaoPayRequest(@RequestBody RentalRequestInfo info){
        log.info("kakaoPay post............................................");

        //KakaoPayApprovalVO approvalVO = new KakaoPayApprovalVO();

        //return "redirect:" + kakaoPayService.kakaoPayReady(info);
        return kakaoPayService.kakaoPayReady(info);
    }

    // 카카오페이 성공하면 카카오 서버에서 이 api로 리다이렉트
    @PostMapping(path = "/kakaoPaySucess")
    @GetMapping(path = "/kakaoPaySucess")
    public KakaoPayApprovalVO kakaoPayRequest(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));

        return (KakaoPayApprovalVO)model.getAttribute("info");
    }

    // 사용자의 결제 내역 리스트 리턴
    @GetMapping(path = "/returnPaymentList")
    @PostMapping(path="/returnPaymentList")
    public List<Payment> returnPayment(String user_id){  // null 리턴되면 결제 제대로 안된 것
        List<Payment> payment=kakaoPayService.returnPaymentList(user_id);

        return payment;
    }

    // 사용자의 대여 내역 리스트 리턴
    @GetMapping(path = "/returnRentList")
    @PostMapping(path="/returnRentList")
    public List<Rent> returnRent(String user_id){  // null 리턴되면 결제 제대로 안된 것
        List<Rent> rent=rentalService.returnRentList(user_id);

        return rent;
    }

    // 물품 반납 -> rent 정보 업데이트(상태:이용 완료), item 정보 업데이트(상태: 이용 가능)
    @GetMapping(path="/returnItem")
    @PostMapping(path="/returnItem")
    public void returnItem(String user_id){
        rentMapper.updateRent(user_id);
        Rent rent = rentMapper.updateRentInfo(user_id);
        itemMapper.updateReturnItem(rent.getITEM_IDX());
    }

    // 대여 신청 -> item, rent, member 정보 업데이트 및 추가
    @PostMapping(path = "/apply")
    @GetMapping(path = "/apply")
    public RentSaveRequestDto apply(@RequestBody RentalRequestInfo info){
        System.out.println("User Id : " + info.getUser_id());
        System.out.println("Start Cabinet Idx : " + info.getStart_cabinet_idx());

        RentSaveRequestDto dto= rentalService.applyService(info);

        // stamp 추가 -> member 정보 update
        if(info.getRecommend()==1)
            memberMapper.addStamp(info.getUser_id(),2);
        else
            memberMapper.addStamp(info.getUser_id(),1);

        return dto;
    }


    // 신청 취소 -> 결제 취소 및 대여 내역 삭제
    @PostMapping(path = "/kakaoCancel")
    @GetMapping(path = "/kakaoCancel")
    public KakaoPayCancelVO kakaoCancel(String user_id){
        log.info("kakaoCancel post............................................");

        kakaoPayService.applyCancelService(user_id);
        return kakaoPayService.kakaoCancelGO(user_id);
    }

}
