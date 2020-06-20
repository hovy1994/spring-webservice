package com.example.cancelproject;

import java.util.Date;
import com.example.cancelproject.AmountVO;
import com.example.cancelproject.CanceledAmountVO;
import com.example.cancelproject.CancelAvailableAmountVO;
import lombok.Data;

public class KakaoPayCancelVO {
    //response
    public String aid, tid, cid;
    public String status;
    public String partner_order_id, partner_user_id, payment_method_type;
    public AmountVO amount;
    public CanceledAmountVO canceled_amount;
    public CancelAvailableAmountVO cancel_available_amount;
    public String item_name, item_code, payload;
    public int quantity;
    public Date created_at, approved_at, canceled_at;
}
