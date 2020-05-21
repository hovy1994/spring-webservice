package com.example.springwebservice.domain.inquiry;

import com.example.springwebservice.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
