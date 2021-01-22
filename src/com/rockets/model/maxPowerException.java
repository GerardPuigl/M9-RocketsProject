package com.rockets.model;

public class maxPowerException extends RuntimeException {

	private double power;

	// excepció personalitzada 
	public maxPowerException(String errorMessage, double power) {
		super(errorMessage);
	}

	public double getExtraPower() {
		return power;
	}

}
