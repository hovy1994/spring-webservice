package com.example.springwebservice.web;


import com.example.springwebservice.domain.KakaoPay.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class PaymentSaveRequestDto {

    private String PARTNER_ORDER_ID;

    private String TID;

    private String PARTNER_USER_ID;

    private String ITEM_NAME;

    private Integer QUANTITY;

    private Integer TOTAL_AMOUNT;

    private Integer TAX_FREE_AMOUNT;

    public Payment toEntity(){
        return Payment.builder()
                .PARTNER_ORDER_ID(PARTNER_ORDER_ID)
                .TID(TID)
                .PARTNER_USER_ID(PARTNER_USER_ID)
                .ITEM_NAME(ITEM_NAME)
                .QUANTITY(QUANTITY)
                .TOTAL_AMOUNT(TOTAL_AMOUNT)
                .TAX_FREE_AMOUNT(TAX_FREE_AMOUNT)
                .build();
    }
}
