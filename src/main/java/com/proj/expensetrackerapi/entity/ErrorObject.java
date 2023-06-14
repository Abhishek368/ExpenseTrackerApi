package com.proj.expensetrackerapi.entity;


import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	private Integer statusCode;
	private String message;
	private Date timestamp;
}
