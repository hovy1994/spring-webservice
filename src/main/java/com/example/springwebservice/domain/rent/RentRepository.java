package com.example.springwebservice.domain.rent;

import com.example.springwebservice.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository  extends JpaRepository<Rent, Long> {
}
