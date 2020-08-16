package com.mindbowser.demo.service;

import org.springframework.stereotype.Service;

import com.mindbowser.demo.exception.DemoException;
import com.mindbowser.demo.request.PaymentRequest;
import com.mindbowser.demo.request.SignUpRequest;
import com.mindbowser.demo.response.LoginResponse;
import com.razorpay.RazorpayException;

@Service
public interface IDemoService {

	String signUp(SignUpRequest signUpRequest) throws DemoException;

	LoginResponse login(SignUpRequest signUpRequest) throws DemoException;

	String payment(PaymentRequest paymentRequest) throws DemoException,RazorpayException;

}
