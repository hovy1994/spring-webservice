package com.example.springwebservice.service.mapper;


import com.example.springwebservice.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM item WHERE CABINET_IDX=#{cabinetIdx}")
    List<Item> findAll(Integer cabinetIdx);
}
