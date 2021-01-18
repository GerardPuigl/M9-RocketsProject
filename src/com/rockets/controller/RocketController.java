package com.rockets.controller;

import java.text.DecimalFormat;
import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();
	private String info = "";
	private Rocket rocket;
	private double maxPower = 0;
	private double totalPower = 0;
	int saltoAceleracionReduccion = 1;

	private List<Thread> threads = new ArrayList<Thread>();

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	// construye un cohete y lo pone en la lista

	public void addRocket(String Id, int boosters) {
		rockets.add(new Rocket(Id, boosters));
	}

	// define una potencia máxima para un propulsor

	public void setMaxPower(String id, int booster, double power) {
		getRocket(id).setMaxPower(booster, power);
	}

	// devuelve un objeto Rocket con el id dado

	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id))
				return r;
		}
		return null;
	}

	// recopila toda la información de los cohetes de la Array

	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

	// devuelve información del cohete introducido

	public String getInfoRocket(Rocket r) {

		int i = 0;

		info = r.getId() + ": \n";

		for (double b : r.getBoosters()) {
			i++;
			info = info + "Propulsor " + i + ": " + df2.format(b) + "\n";
		}
		return info;
	}

	// Acelerar a una potencia concreta

	public void accelerate(String id, int booster, double power) {

		rocket = getRocket(id);

		try {

			// pregunta la potencia actual y crea un bucle de incremento de 1 en 1

			while (power > saltoAceleracionReduccion) {

				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + saltoAceleracionReduccion);
				power -= saltoAceleracionReduccion;

				System.out.println(
						rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));
				Thread.sleep(300);

			}

			rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + power);
			System.out.println(
					rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}

	}

	// Acelereación multihilo
	public void accelerateMultiThread(String id, int booster, double power) {

		Runnable ac1 = new AccelerateMultiThread(id, booster, power);
		Thread ac1_1 = new Thread(ac1);
		threads.add(ac1_1);
		ac1_1.start();

	}

	// Código para distribuir la potencia uniformemente cuando acelera
	public void powerDistributionAccelerate(Rocket rocket, double power) {
	
		while (power > saltoAceleracionReduccion) {
	
			for (int i = 0; i < rocket.getBoosters().length; i++) {
			
				if((rocket.getMaxPower(i)-rocket.getPowerBooster(i))>saltoAceleracionReduccion) {
					
					accelerateMultiThread(rocket.getId(), i, saltoAceleracionReduccion);
					power -= saltoAceleracionReduccion;
					
				}
			}
			
			
			sincroThreads();
		}
	
	}

	// Accelereación multi hilo
	public void reduceMultiThread(String id, int booster, double power) {
		Runnable re1 = new ReduceMultiThread(id, booster, power);
		Thread re1_1 = new Thread(re1);
		threads.add(re1_1);
		re1_1.start();
	}

	// Cálculo de potencia respecto a una velocidad dada
	public void setSpeed(String id, double speed) {
		rocket = getRocket(id);

		totalPower = 0;

		if (rocket.getSpeed() < speed) {

			totalPower = Math.pow(((speed - rocket.getSpeed()) / 100), 2);

			for (int i = 0; i < rocket.getBoosters().length; i++) {

				maxPower = maxPower + rocket.getMaxPower(i) - rocket.getPowerBooster(i);

			}

			if (totalPower > maxPower) {
				System.out.println("No es possible accelerar fins la velocitat sol·licitada.");

			} else {
				powerDistributionAccelerate(rocket, totalPower);
			}
		}

		if (rocket.getSpeed() > speed) {

			totalPower = Math.pow(((rocket.getSpeed() - speed) / 100), 2);

			double totalPower = Math.pow(((rocket.getSpeed() - speed) / 100), 2);

			powerDistributionReduce(rocket, totalPower);

		}
		sincroThreads();

	}

	public void reduce(String id, int booster, double power) {

		rocket = getRocket(id);

		try {

			// pregunta la potencia actual y crea un bucle de dremento de 1 en 1

			while (power > saltoAceleracionReduccion) {

				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - saltoAceleracionReduccion);
				power -= saltoAceleracionReduccion;

				System.out.println(
						rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));
				Thread.sleep(300);

			}

			rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - power);
			System.out.println(
					rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}

	}
	// Código para distribuir la potencia uniformemente cuando frena
	public void powerDistributionReduce(Rocket rocket, double totalPower) {
		while (totalPower > 0.1) {

			double power = totalPower / rocket.getBoosters().length;

			for (int i = 0; i < rocket.getBoosters().length; i++) {

				// si el propulsor tiene potencia suficiente para almacenar lo que le toca
				if (rocket.getMaxPower(i) - rocket.getPowerBooster(i) > power) {

					reduceMultiThread(rocket.getId(), i, power);
					totalPower -= power;

					// si el propulsor no tiene suficiente potencia. Poner al 100% y repartir el
					// resto entre los otros
				} else {
					reduceMultiThread(rocket.getId(), i, rocket.getMaxPower(i));
					totalPower -= rocket.getMaxPower(i) - rocket.getPowerBooster(i);
				}

			}
		}

	}

	private void sincroThreads() {
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
