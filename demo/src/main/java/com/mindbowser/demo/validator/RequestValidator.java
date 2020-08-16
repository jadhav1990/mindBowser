package com.mindbowser.demo.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mindbowser.demo.exception.DemoException;
import com.mindbowser.demo.exception.ErrorCodes;
import com.mindbowser.demo.model.ManagerModel;
import com.mindbowser.demo.repository.DemoRepository;
import com.mindbowser.demo.request.PaymentRequest;
import com.mindbowser.demo.request.SignUpRequest;

@Component
public class RequestValidator {

	@Autowired
	DemoRepository demoRepository;

	public void checkSignUpRequest(SignUpRequest signUpRequest) throws DemoException {

		if (StringUtils.isEmpty(signUpRequest.getEmail().trim())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("Email");
		}
		if (StringUtils.isEmpty(signUpRequest.getCompany().trim())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("Company");
		}
		if (StringUtils.isEmpty(signUpRequest.getFirstName().trim())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("FirstName");
		}
		if (StringUtils.isEmpty(signUpRequest.getLastName().trim())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("LastName");
		}
		if (StringUtils.isEmpty(signUpRequest.getPassword().trim())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("Password");
		}

		if (!StringUtils.isEmpty(signUpRequest.getEmail().trim())) {
			ManagerModel managerModel = demoRepository.findByEmail(signUpRequest.getEmail());
			if (managerModel != null) {
				throw ErrorCodes.EMAIL_EXIST.createDemoException(signUpRequest.getEmail());
			}
		}

	}

	public void checkLoginRequest(SignUpRequest signUpRequest) throws DemoException {

		if (StringUtils.isEmpty(signUpRequest.getEmail())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("Email");
		}
		if (StringUtils.isEmpty(signUpRequest.getPassword())) {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("Password");
		}
	}

	public void checkPaymentRequest(PaymentRequest paymentRequest) throws DemoException, ParseException {

		if (!Objects.isNull(paymentRequest.getCardNumber())) {
			if (String.valueOf(paymentRequest.getCardNumber()).length() < 16) {
				throw ErrorCodes.INVALID_CARD_DETAILS.createDemoException("card number");
			}
		} else {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("card number");
		}
		if (!Objects.isNull(paymentRequest.getCvv())) {
			if (String.valueOf(paymentRequest.getCvv()).length() < 3) {
				throw ErrorCodes.INVALID_CARD_DETAILS.createDemoException("cvv");
			}
		} else {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("cvv");
		}

		if (!Objects.isNull(paymentRequest.getAmount())) {
			if (paymentRequest.getAmount() <= 0 ) {
				throw ErrorCodes.INVALID_AMOUNT.createDemoException();
			}
		} else {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("amount");
		}

		if (!Objects.isNull(paymentRequest.getExpiryDate())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
			simpleDateFormat.setLenient(false);
			Date expiry = simpleDateFormat.parse(paymentRequest.getExpiryDate().toString());
			boolean expired = expiry.before(new Date());
			if (expired) {
				throw ErrorCodes.CARD_EXPIRED.createDemoException();
			}

		} else {
			throw ErrorCodes.MANDATORY_ELEMENT_MISSING.createDemoException("expiry date");
		}

	}

}
