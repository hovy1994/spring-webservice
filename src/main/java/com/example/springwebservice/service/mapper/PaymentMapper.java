package com.example.springwebservice.service.mapper;

import com.example.springwebservice.domain.KakaoPay.Payment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentMapper {
//    @Update("UPDATE member SET STAMP=STAMP+#{num} WHERE USER_ID=#{userID}")
//    void addStamp(String userID,Integer num);

    @Select("SELECT * FROM payment WHERE TID=#{tid}")
    Payment findPayment(String tid);

    @Delete("DELETE FROM payment WHERE TID=#{tid}")
    void deletePayment(String tid);
}
