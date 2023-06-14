package com.proj.expensetrackerapi.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank(message="Name should not be blank")
	private String name;
	
	@Column(name ="email",unique=true)
	@NotNull(message ="Email should not be empty")
	@Email(message ="Enter Valid email")
	private String email;
	
	@JsonIgnore
	@NotNull(message="Password should not be empty")
	@Size(min=5,message="Password should be atleast 5 characters")
	private String password;
	
	private Long age;
	
	@Column(name ="created_At",nullable = false,updatable=false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name ="updated_At",nullable=false)
	@UpdateTimestamp
	private Timestamp updatedAt;
}
