package com.rockets.model;

import java.text.DecimalFormat;


public class Booster implements Runnable {

	int id;
	private double objectivePower;
	private double boosterPower;
	private double boostersMaxPower;
	private int cadencia=1;

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public Booster(int id) {
		this.id = id;
	}

	public double getPower() {
		return boosterPower;
	}

	public void setPower(double power) throws Exception {

		if (boostersMaxPower < power) {

			throw new Exception(" Propulsor " + id + " ha arribat a la potencia màxima!");

		} else if (0 >= power) {

			throw new Exception(" Propulsor " + id + " està a 0.");

		} else {
			boosterPower = power;

		}
	}

	public double getMaxPower() {
		return boostersMaxPower;
	}

	public void setMaxPower(double boostersMaxPower) {
		this.boostersMaxPower = boostersMaxPower;
	}

	@Override
	public void run() {

		while (objectivePower < boosterPower) {

			boosterPower += cadencia;

			System.out.println("Propulsor " + id + " : " + df2.format(boosterPower));

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
