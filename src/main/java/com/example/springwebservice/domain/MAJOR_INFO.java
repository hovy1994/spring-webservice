package com.example;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MAJOR_INFO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long major_idx;
	
	@Column(name="MAJOR_NAME", nullable=false)
	private String major_name;
	
	public Long getId() {
		return major_idx;
	}
	
	public void setId(Long id) {
		this.major_idx = id;
	}
	
	public String getMajorName() {
		return major_name;
	}
	
	public void setMajorName(String Major_name) {
		this.major_name = Major_name;
	}
}
