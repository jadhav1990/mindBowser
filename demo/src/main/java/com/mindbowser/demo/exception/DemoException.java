package com.mindbowser.demo.exception;

import org.springframework.http.HttpStatus;

public class DemoException extends Exception {

	private static final long serialVersionUID = 1L;

	private final int code;

	private final HttpStatus httpCode;

	/**
	 * Instantiates a new DemoException.
	 *
	 * @param pErrorCode the causing error code
	 *
	 * @param pMessage   the error message
	 */
	public DemoException(final IErrorCode pErrorCode, final String pMessage) {
		super(pMessage);
		code = pErrorCode.getCode();
		httpCode = pErrorCode.getHttpCode();
	}

	/**
	 * Instantiates a new DemoException with underlying cause
	 *
	 * @param pErrorCode the causing error code
	 * @param pMessage   the error message
	 * @param pCause     the causing exception; required
	 */
	public DemoException(final IErrorCode pErrorCode, final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
		code = pErrorCode.getCode();
		httpCode = pErrorCode.getHttpCode();
	}

	public int getCode() {
		return code;
	}

	public final HttpStatus getHttpCode() {
		return httpCode;
	}

}
