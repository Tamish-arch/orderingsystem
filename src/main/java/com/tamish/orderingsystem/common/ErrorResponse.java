package com.tamish.orderingsystem.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private int status;
	private LocalDateTime timestamp;

}
