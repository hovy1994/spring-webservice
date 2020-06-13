package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.service.KakaoPayService;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.service.mapper.MemberMapper;
import com.example.springwebservice.service.mapper.PaymentMapper;
import com.example.springwebservice.web.PaymentSaveRequestDto;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import lombok.extern.java.Log;

@Log
@Transactional(readOnly = false)
@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {

    @Autowired
    private MemberMapper memberMapper;
    private PaymentMapper paymentMapper;
    private RentalService rentalService;
    private KakaoPayService kakaoPayService;
    private RentRepository rentRepository;


    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public ArrayList<Cabinet> recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());
        System.out.println("End time : " + info.getEnd());

        return rentalService.findCabinet(info);
    }

//    @RequestMapping(path = "/Test")
//    public String Test(@RequestBody String st){
//        System.out.println("hello payTest");
//
//        return "redirect:/payTest";
//        //return "kakaoPay";
//    }
//
//    @RequestMapping(path = "/payTest")
//    public String payTest(){
//        System.out.println("redirect 성공");
//
//        return "kakaoPay";
//    }

    @PostMapping(path = "/pay")
    @GetMapping(path = "/pay")
    public String kakaoPayRequest(@RequestBody RentalRequestInfo info){
        log.info("kakaoPay post............................................");

        KakaoPayApprovalVO approvalVO = new KakaoPayApprovalVO();

        return "redirect:" + kakaoPayService.kakaoPayReady(info);
    }

    @PostMapping(path = "/kakaoPaySucess")
    @GetMapping(path = "/kakaoPaySucess")
    public KakaoPayApprovalVO kakaoPayRequest(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));

        return (KakaoPayApprovalVO)model.getAttribute("info");
    }


    @GetMapping(path = "/payCheck")
    public Payment payCheck(String tid){  // null 리턴되면 결제 제대로 안된 것
        Payment payment=kakaoPayService.paySuccess(tid);

        return payment;
    }

    @PostMapping(path = "/apply")
    @GetMapping(path = "/apply")
    public RentSaveRequestDto apply(@RequestBody RentalRequestInfo info){
        System.out.println("User Id : " + info.getUser_id());
        System.out.println("Start Cabinet Idx : " + info.getStart_cabinet_idx());

        RentSaveRequestDto dto=new RentSaveRequestDto();

        dto.setSTART_CABINET_IDX(info.getStart_cabinet_idx());
        dto.setEND_CABINET_IDX(info.getEnd_cabinet_idx());
        //dto.setITEM_IDX(applyItem.getITEM_IDX());
        dto.setUSER_ID(info.getUser_id());
        dto.setSTART_TIME(info.getStart());
        dto.setEND_TIME(info.getEnd());
        dto.setITEM_IDX(info.getItem_idx());
        dto.setAMOUNT(info.getTotal_amount());
        dto.setAPPROVED_AT(info.getApproved_at());

        rentRepository.save(dto.toEntity());

        // stamp 추가 -> member 정보 update
        if(info.getRecommend()==1)
            memberMapper.addStamp(info.getUser_id(),2);
        else
            memberMapper.addStamp(info.getUser_id(),1);

        return dto;
    }


    @PostMapping(path = "/kakaoCancel")
    @GetMapping(path = "/kakaoCancel")
    public void kakaoCancel(String tid){

        /*
        * 카카오페이 취소 구현
        * */

        // 신청 내역(Payment)에서 삭제
        Payment payment = paymentMapper.findPayment(tid);

        paymentMapper.deletePayment(tid);
    }

}
