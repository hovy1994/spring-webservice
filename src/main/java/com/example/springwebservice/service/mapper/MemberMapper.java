package com.example.springwebservice.service.mapper;

import com.example.springwebservice.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    @Update("UPDATE member SET STAMP=STAMP+#{num} WHERE USER_ID=#{userID}")
    void addStamp(String userID,Integer num);
}

