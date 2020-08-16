package com.mindbowser.demo.serviceImpl;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindbowser.demo.exception.DemoException;
import com.mindbowser.demo.exception.ErrorCodes;
import com.mindbowser.demo.model.ManagerModel;
import com.mindbowser.demo.repository.DemoRepository;
import com.mindbowser.demo.request.PaymentRequest;
import com.mindbowser.demo.request.SignUpRequest;
import com.mindbowser.demo.response.LoginResponse;
import com.mindbowser.demo.service.IDemoService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private DemoRepository demoRepository;

	@Override
	@Transactional
	public String signUp(SignUpRequest signUpRequest) throws DemoException {

		log.info("Registering Manager {}", signUpRequest);

		ManagerModel managerModel = new ManagerModel();

		try {

			// copying request data to ManagerModel
			BeanUtils.copyProperties(signUpRequest, managerModel);

			// saving ManagerModel into database
			ManagerModel savedManagerModel = demoRepository.save(managerModel);

			if (savedManagerModel != null) {

				log.info("Registered Successfully");

				return ErrorCodes.SIGNUP_SUCCESS.getMessage();

			} else {

				log.info("Registration Failure");

				throw ErrorCodes.ERROR_IN_SERVICE.createDemoException();
			}

		} catch (Exception e) {

			e.printStackTrace();

			log.error("Error while registering manager {}", e);

			throw ErrorCodes.ERROR_IN_SERVICE.createDemoException("signUp");
		}
	}

	@Override
	@Transactional
	public LoginResponse login(SignUpRequest loginRequest) throws DemoException {

		log.info("Login Manager {}", loginRequest);

		LoginResponse loginResponse = new LoginResponse();

		try {

			ManagerModel managerFound = demoRepository.findByEmailAndPassword(loginRequest.getEmail().trim(),
					loginRequest.getPassword().trim());

			if (managerFound != null) {

				log.info("Login Successfully");

				// copying model data to LoginResponse
				BeanUtils.copyProperties(managerFound, loginResponse);

			} else {

				log.info("Invalid credentials");

				throw ErrorCodes.RECORD_NOT_FOUND.createDemoException();
			}

		} catch (Exception e) {

			e.printStackTrace();

			log.error("Error while login {}", e);

			throw ErrorCodes.RECORD_NOT_FOUND.createDemoException();
		}
		return loginResponse;
	}

	@Override
	public String payment(PaymentRequest paymentRequest) throws DemoException, RazorpayException {

		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_k7i0bQbSkbgJQC", "b5WZ7DjceZRVs4V4v7lKqAcU");

		try {
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", paymentRequest.getAmount());
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			orderRequest.put("payment_capture", Boolean.TRUE);

			Order order = razorpayClient.Orders.create(orderRequest);
			log.info("{}", order.toString());

			if (order.get("id") != null) {
				return "Payment Success";
			} else {
				throw ErrorCodes.PAYMENT_FAILURE.createDemoException();
			}

		} catch (Exception e) {

			e.printStackTrace();

			log.error("Error while payment {}", e);

			throw ErrorCodes.PAYMENT_FAILURE.createDemoException();
		}
	}
}
