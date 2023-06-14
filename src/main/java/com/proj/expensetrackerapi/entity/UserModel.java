package com.proj.expensetrackerapi.entity;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserModel {
	private String name;
	
	@Column(name ="email",unique=true)	
	private String email;
	
	private String password;
	
	private Long age=0L;
}
