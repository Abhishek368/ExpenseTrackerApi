package com.proj.expensetrackerapi.services;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proj.expensetrackerapi.entity.Expense;

public interface ExpenseService {
	Page<Expense> getAllExpenses(Pageable page); 
	
	Expense getExpenseById(Long id);
	
	void deleteExpenseById(Long id);
	
	Expense saveExpenseDetails(Expense e);
	
	Expense updateExpenseDetails(Long id , Expense e);
	
	List<Expense> readByCategory(String category, Pageable page);
	
	List<Expense> readByNameContaining(String keyword, Pageable page);
	
	List<Expense> readByDateBetween(Date startDate, Date endDate, Pageable page);
}
