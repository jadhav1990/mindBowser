package com.mindbowser.demo.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoResponse<T> {

	HttpStatus httpStatus;

	String message;

	T data;
}
