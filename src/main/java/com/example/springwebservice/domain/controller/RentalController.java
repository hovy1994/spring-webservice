package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.KakaoPayCancelVO;
import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.KakaoPay.PaymentRepository;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.rent.Rent;
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
    private PaymentMapper paymentMapper;
    private RentalService rentalService;
    private KakaoPayService kakaoPayService;
    private RentRepository rentRepository;
    private PaymentRepository paymentRepository;



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

    @PostMapping(path = "/kakaoPay")
    @GetMapping(path = "/kakaoPay")
    public String kakaoPayRequest(@RequestBody RentalRequestInfo info){
        log.info("kakaoPay post............................................");

        PaymentSaveRequestDto dto =new PaymentSaveRequestDto();
        dto.setTAX_FREE_AMOUNT(0);
        dto.setTOTAL_AMOUNT(info.getTotal_amount());
        dto.setQUANTITY(1);
        dto.setITEM_NAME(String.valueOf(info.getItem_idx()));
        dto.setPARTNER_USER_ID(info.getUser_id());
        dto.setPARTNER_ORDER_ID(info.getUser_id());
        paymentRepository.save(dto.toEntity());

        //KakaoPayApprovalVO approvalVO = new KakaoPayApprovalVO();

        //return "redirect:" + kakaoPayService.kakaoPayReady(info);
        return kakaoPayService.kakaoPayReady(info);
    }

    @PostMapping(path = "/kakaoPaySucess")
    @GetMapping(path = "/kakaoPaySucess")
    public KakaoPayApprovalVO kakaoPayRequest(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));

        return (KakaoPayApprovalVO)model.getAttribute("info");
    }


    @GetMapping(path = "/returnPaymentList")
    public List<Payment> returnPayment(String user_id){  // null 리턴되면 결제 제대로 안된 것
        List<Payment> payment=kakaoPayService.returnPaymentList(user_id);

        return payment;
    }
    @GetMapping(path = "/returnRentList")
    public List<Rent> returnRent(String user_id){  // null 리턴되면 결제 제대로 안된 것
        List<Rent> rent=rentalService.returnRentList(user_id);

        return rent;
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
    public KakaoPayCancelVO kakaoCancel(String user_id){
        log.info("kakaoCancel post............................................");

        kakaoPayService.applyCancelService(user_id);
        return kakaoPayService.kakaoCancelGO(user_id);
    }

}
