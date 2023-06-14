package com.proj.expensetrackerapi.controllers;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proj.expensetrackerapi.entity.Expense;
import com.proj.expensetrackerapi.services.ExpenseService;

import jakarta.validation.Valid;


@RestController
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public Page<Expense> getAllExpense(Pageable page) {
		return expenseService.getAllExpenses(page);
	}
	
	@GetMapping("expenses/{id}")
	public Expense getExpenseById(@PathVariable("id") Long id) {
		return expenseService.getExpenseById(id);
	}
	
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@DeleteMapping("expenses")
	public void deleteById(@RequestParam("id") Long id) {
		expenseService.deleteExpenseById(id);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@PostMapping("/expenses")
	public Expense saveExpenseDetails(@Valid @RequestBody Expense e) {
		return expenseService.saveExpenseDetails(e);
	}
	
	@PutMapping("expenses/{id}")
	public Expense updateExpenseDetails(@PathVariable Long id ,@RequestBody Expense e) {
		return expenseService.updateExpenseDetails(id, e);
	}
	
	@GetMapping("expenses/category")
	public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
		return expenseService.readByCategory(category, page);
	}
	
	@GetMapping("/expenses/name")
	public List<Expense> getExpensesByNameContaining(@RequestParam String keyword, Pageable page){
		return expenseService.readByNameContaining(keyword, page);
	}
	
	@GetMapping("/expenses/date")
	public List<Expense> getExpenseByDateBetween(@RequestParam(required=false) Date startDate, @RequestParam(required=false)  Date endDate,Pageable page){
		return expenseService.readByDateBetween(startDate, endDate, page);
	}
}