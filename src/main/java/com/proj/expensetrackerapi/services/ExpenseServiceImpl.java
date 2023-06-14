package com.proj.expensetrackerapi.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proj.expensetrackerapi.entity.Expense;
import com.proj.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.proj.expensetrackerapi.repositories.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private UserService userService;
//	@Override
//	public Page<Expense> getAllExpenses(Pageable page) {
//		return expenseRepo.findAll(page);
//	}
	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> o = expenseRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
		if(o.isPresent()) 
		return o.get();
		throw new ResourceNotFoundException("Expense not found for id "+ id);
	}
	@Override
	public void deleteExpenseById(Long id) {
		Expense expense = getExpenseById(id);
		expenseRepo.deleteById(id);;
	}
	@Override
	public Expense saveExpenseDetails(Expense e) {
		e.setUser(userService.getLoggedInUser());
		return expenseRepo.save(e); 
	}
	@Override
	public Expense updateExpenseDetails(Long id, Expense e) {
		// TODO Auto-generated method stub
		return expenseRepo.save(e);
	}
	
	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByUserId(userService.getLoggedInUser().getId(),page);
	}
	
	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category, page).toList();
	}
	@Override
	public List<Expense> readByNameContaining(String keyword, Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword, page).toList();
	}
	@Override
	public List<Expense> readByDateBetween(Date startDate, Date endDate, Pageable page) {
		// TODO Auto-generated method stub
		if(startDate == null) {
			startDate = new Date(0);
		}
		if(endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		
		return expenseRepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page).toList();
	}

}
