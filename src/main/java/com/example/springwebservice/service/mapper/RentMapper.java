package com.example.springwebservice.service.mapper;

import com.example.springwebservice.domain.rent.Rent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RentMapper {
    @Select("SELECT * FROM rent WHERE USER_ID=#{user_id}")
    List<Rent> findRentList(String user_id);

    @Delete("DELETE * FROM rent WHERE USER_ID=#{user_id}")
    void deleteRent(String user_id);
}
