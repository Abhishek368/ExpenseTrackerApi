package com.proj.expensetrackerapi.repositories;


import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.expensetrackerapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>{
	Page<Expense> findByUserIdAndCategory(Long id,String category,Pageable page);
	
	Page<Expense> findByUserIdAndNameContaining(Long id,String keyword, Pageable page);
	
	Page<Expense> findByUserIdAndDateBetween(Long id,Date startDate,Date endDate, Pageable page);
	
	Page<Expense> findByUserId(Long id, Pageable page);
	
	Optional<Expense> findByUserIdAndId(Long userId,Long expenseId); 
}
