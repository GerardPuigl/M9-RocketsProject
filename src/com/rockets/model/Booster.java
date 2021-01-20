package com.rockets.model;

import java.text.DecimalFormat;


public class Booster implements Runnable {

	private int id;
	private double objectivePower;
	private double boosterPower;
	private double boostersMaxPower;
	private int cadencia=1;
	private String state="parat";

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public Booster(int id) {
		this.id = id;
	}
	
	public double getMaxPower() {
		return boostersMaxPower;
	}

	public void setMaxPower(double boostersMaxPower) {
		this.boostersMaxPower = boostersMaxPower;
	}
	
	public double getObjectivePower() {
		return objectivePower;
	}

	public double getPower() {
		return boosterPower;
	}

	// fixa potencia objectiu i vigila que no pugui superar la potencia màxima. 
	// en cas de superar-la el posa al màxim i retorna la potencia no assignada.
	
	public void setObjectivePower(double power) throws maxPowerException{
		
		if(boostersMaxPower<power) {
			
			objectivePower=boostersMaxPower;
			
			throw new maxPowerException("No es pot superar la potencia màxima.\n"
					+ "Potencia no adjudicada al propulsor: " + id + " - "
					+ (power - boostersMaxPower), power - boostersMaxPower);
		}
		objectivePower=power;
	}
	// augmenta la potencia i vigila que no superi la potencia màxima.
	
	public void setPower(double power) throws Exception {

		if (boostersMaxPower < power) {

			throw new Exception("Propulsor " + id + " no pot superar la potencia màxima!");
			
		} else if (boostersMaxPower == power) {
			
			boosterPower = power;
			throw new Exception("Propulsor " + id + " ha arribat a la potencia màxima.");


		} else if (0 >= power) {
			
			boosterPower = power;
			throw new Exception("Propulsor " + id + " està a 0.");


		} else {
			boosterPower = power;

		}
	}

	// mètode runnable: Accelera o frena (en funció de l'estat) fins la potencia objectiu.
	
	@Override
	public void run() {

		while (objectivePower != boosterPower && !Thread.currentThread().isInterrupted()) {

			try {

				if (state.equals("accelerar")) {

					setPower(boosterPower += cadencia);

				} else if (state.equals("frenar")) {

					setPower(boosterPower -= cadencia);
				}
				System.out.println("Propulsor " + id + " : " + df2.format(boosterPower) + " de " + df2.format(boostersMaxPower));
			} catch (Exception e) {

				System.out.println("Propulsor " + id + " : " + df2.format(boosterPower) + " de " + df2.format(boostersMaxPower));
				System.out.println(e.getMessage());
				break;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		state="parat";

	}

	public void accelerar() {
				
		state="accelerar";
		
	}

	public void frenar() {
		
		state="frenar";
		
	}

}
