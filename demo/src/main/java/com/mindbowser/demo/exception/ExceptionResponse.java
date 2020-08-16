package com.mindbowser.demo.exception;

/**
 * Class represents Exception Response.
 *
 * @author ctpl
 *
 */
public class ExceptionResponse {

	private int errorCode;
	private String errorMessage;

	/**
	 * Errorcode getter.
	 *
	 * @return {@link int}
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Errorcode setter.
	 *
	 * @param pErrorCode {@link String}
	 */
	public void setErrorCode(final int pErrorCode) {
		errorCode = pErrorCode;
	}

	/**
	 * ErrorMessage getter.
	 *
	 * @return {@link String}
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Error mesage getter.
	 *
	 * @param pErrorMessage {@link String}
	 */
	public void setErrorMessage(final String pErrorMessage) {
		errorMessage = pErrorMessage;
	}

}
