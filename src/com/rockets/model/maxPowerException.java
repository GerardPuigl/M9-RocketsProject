package com.rockets.model;

public class maxPowerException extends RuntimeException {

	String errorMessage;
	double power;

	// excepció personalitzada
	public maxPowerException(String errorMessage, double power) {
		super(errorMessage);
	}

	public double getExtraPower() {
		return power;
	}

}
