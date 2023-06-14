package com.proj.expensetrackerapi.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="tbl_expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="expense_name")
	@NotBlank(message = "Expense Name must not be blank")
	@Size(min = 3, message ="Expense Name should be atleast 3 characters")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name ="expense_amount")
	@NotNull(message = "Expense amount must not be null")
	private BigDecimal amount;
	
	@Column(name="category")
	@NotBlank(message = "Expense Category must not be blank")
	private String category;
	
	@Column(name="date")
	@NotNull(message = "Date must not be null")
	private Date date;
	
	@Column(name ="created_At",nullable = false,updatable=false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name ="updated_At",nullable=false)
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	
}
