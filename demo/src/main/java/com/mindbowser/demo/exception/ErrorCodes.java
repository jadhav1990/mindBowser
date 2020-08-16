package com.mindbowser.demo.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public enum ErrorCodes implements IErrorCode {
	// Demo specific response error codes and error response.
	MANDATORY_ELEMENT_MISSING(1001, HttpStatus.BAD_REQUEST, "Mandatory element {0} missing in the request."),
	ERROR_IN_SERVICE(1002, HttpStatus.BAD_REQUEST, "Error while executing {0} service."),
	SUCCESS(1003, HttpStatus.OK, "Success"), SIGNUP_SUCCESS(1004, HttpStatus.OK, "Registered Successfully."),
	EMAIL_EXIST(1005, HttpStatus.BAD_REQUEST, "Email {0} already exists."),
	RECORD_NOT_FOUND(1006, HttpStatus.NOT_FOUND, "Invalid Credentials"),
	INVALID_CARD_DETAILS(1007, HttpStatus.BAD_REQUEST, "Invalid Card Details, {0} is incorrect."),
	INVALID_AMOUNT(1008, HttpStatus.BAD_REQUEST, "Invalid amount."),
	CARD_EXPIRED(1009, HttpStatus.BAD_REQUEST, "Card expired."),
	PAYMENT_FAILURE(1010,HttpStatus.BAD_REQUEST,"Payment faiure.")
	;

	/** Begin of the error codes range. */
	public static final int ERROR_CODE_RANGE_BASE = 1000;
	/** End of the error codes range. */
	public static final int ERROR_CODE_RANGE_MAX = 1999;

	/** Error code. */
	private final Integer code;

	/** Error text message. */
	private final String message;

	private final HttpStatus httpCode;

	ErrorCodes(final Integer pCode, final HttpStatus pHttpCode, final String pMessage) {
		code = pCode + ERROR_CODE_RANGE_BASE;
		message = pMessage;
		httpCode = pHttpCode;
	}

	@Override
	public final Integer getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}

	@Override
	public final HttpStatus getHttpCode() {
		return httpCode;
	}

	/**
	 * getter for message.
	 *
	 * @param pArguments the arguments
	 * @return the formatted message
	 */

	public final String getFormattedMessage(final Object... pArguments) {
		return MessageFormat.format(this.getMessage(), pArguments);
	}

	/**
	 * Demo Exception.
	 *
	 * @return the Demo exception
	 */

	public final DemoException createDemoException(final Object... pArguments) {
		return new DemoException(this, this.getFormattedMessage(pArguments));
	}

	/**
	 * Demo Exception with variable arguments.
	 *
	 * @param pMessageArguments the message arguments
	 * @return the Demo exception
	 */
	public final DemoException createDemoException(final Throwable pCause, final Object... pArguments) {
		return new DemoException(this, this.getFormattedMessage(pArguments), pCause);
	}

}