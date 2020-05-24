package com.example.springwebservice.domain.item;

import com.example.springwebservice.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
