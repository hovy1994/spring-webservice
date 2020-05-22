package com.example.springwebservice.domain.category;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Category, Long> {
}
