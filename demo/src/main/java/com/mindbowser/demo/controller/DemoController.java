package com.mindbowser.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindbowser.demo.exception.DemoException;
import com.mindbowser.demo.exception.ErrorCodes;
import com.mindbowser.demo.request.PaymentRequest;
import com.mindbowser.demo.request.SignUpRequest;
import com.mindbowser.demo.response.DemoResponse;
import com.mindbowser.demo.response.LoginResponse;
import com.mindbowser.demo.service.IDemoService;
import com.mindbowser.demo.validator.RequestValidator;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	IDemoService demoService;

	@Autowired
	RequestValidator requestValidator;

	/**
	 * Sign up api
	 * 
	 * @param signUpRequest
	 * @return String
	 * @throws DemoException
	 */

	@PostMapping(value = "/signUp")
	public ResponseEntity<DemoResponse<String>> signUp(@RequestBody SignUpRequest signUpRequest) throws DemoException {

		requestValidator.checkSignUpRequest(signUpRequest);

		DemoResponse<String> demoResponse = new DemoResponse<>();

		String response = demoService.signUp(signUpRequest);
		demoResponse.setData(response);
		demoResponse.setMessage(ErrorCodes.SUCCESS.getMessage());
		return new ResponseEntity<>(demoResponse, HttpStatus.OK);

	}

	/**
	 * Login api
	 * 
	 * @param signUpRequest
	 * @return {@link LoginResponse}
	 * @throws DemoException
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<DemoResponse<LoginResponse>> login(@RequestBody SignUpRequest loginRequest)
			throws DemoException {

		requestValidator.checkLoginRequest(loginRequest);

		DemoResponse<LoginResponse> demoResponse = new DemoResponse<>();

		LoginResponse response = demoService.login(loginRequest);
		demoResponse.setData(response);
		demoResponse.setMessage(ErrorCodes.SUCCESS.getMessage());
		return new ResponseEntity<>(demoResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/payment")
	public ResponseEntity<DemoResponse<String>> payment(@RequestBody PaymentRequest paymentRequest)
			throws DemoException, ParseException, RazorpayException {

		requestValidator.checkPaymentRequest(paymentRequest);

		DemoResponse<String> demoResponse = new DemoResponse<>();

		String response = demoService.payment(paymentRequest);
		demoResponse.setData(response);
		demoResponse.setMessage(ErrorCodes.SUCCESS.getMessage());
		return new ResponseEntity<>(demoResponse, HttpStatus.OK);

	}

}
