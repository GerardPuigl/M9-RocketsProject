package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();
	private String info = "";
	private Rocket rocket;
	private double originalSpeed = 0;
	private double maxPower = 0;
	private List<Thread> threads = new ArrayList<Thread>();

	// construye un cohete y lo pone en la lista
	public void addRocket(String Id, int boosters) {
		rockets.add(new Rocket(Id, boosters));
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

		info = r.getId() + ": ";

		for (double b : r.getBoosters()) {
			info = info + b + ",";
		}
		return info;
	}

	// define una potencia máxima para un propulsor
	public void setMaxPower(String id, int booster, double power) {
		getRocket(id).setMaxPower(booster, power);
	}

	// Acelerar a una potencia concreta
	public void accelerate(String id, int booster, double power) {

		rocket = getRocket(id);

		try {
			// pregunta la potencia actual y crea un bucle de incremento de 1 en 1
			for (double i = rocket.getPowerBooster(booster); i < power; i++) {
				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + 1);
				System.out.println(rocket.getId() + " propulsor " + booster + ":" + rocket.getPowerBooster(booster));
				Thread.sleep(300);
			}
			//System.out.println(rocket.getId() + " propulsor " + booster + " ha arribat la potencia sol·licitada.");

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

	// Reducir a una potencia concreta
	public void reduce(String id, int booster, int power) {
		rocket = getRocket(id);
		try {
			// pregunta la potencia actual y crea un bucle de reducción de 1 en 1
			for (double i = rocket.getPowerBooster(booster); i > power; i--) {
				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - 1);
				System.out.println(rocket.getId() + " propulsor " + booster + ":" + rocket.getPowerBooster(booster));
				Thread.sleep(300);
			}
			//System.out.println(rocket.getId() + " propulsor " + booster + " ha arribat la potencia sol·licitada.");

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}
	}

	// Accelereación multi hilo
	public void reduceMultiThread(String id, int booster, int power) {
		Runnable re1 = new ReduceMultiThread(id, booster, power);
		Thread re1_1 = new Thread(re1);
		re1_1.start();
	}

	// Cálculo de potencia respecto a una velocidad dada
	public void setSpeed(String id, double speed) {
		rocket = getRocket(id);

		// cálculo potencia necesaria
		double totalPower = Math.pow(((speed - originalSpeed) / 100), 2);

		// cáluclo máximo de la potencia disponible
		for (int i = 0; i < rocket.getBoosters().length; i++) {
			maxPower = maxPower + rocket.getMaxPower(i) - rocket.getPowerBooster(i);
		}

		// cálculo si se puede acelerar a esa velocidad
		if (totalPower > maxPower) {
			System.out.println("No es possible accelerar fins la velocitat sol·licitada.");

		} else {
			powerDistribution(rocket, totalPower);
		}

	}

	// Código para distribuir la potencia uniformemente si es posible
	public void powerDistribution(Rocket rocket, double totalPower) {
		while (totalPower > 0.1) {

			// cálculo de la potencia repartida entre todos los propulsores
			double power = totalPower / rocket.getBoosters().length;

			for (int i = 0; i < rocket.getBoosters().length; i++) {

				// si el propulsor tiene potencia suficiente para almacenar lo que le toca
				if (rocket.getMaxPower(i) - rocket.getPowerBooster(i) > power) {

					accelerateMultiThread(rocket.getId(), i, power);
					totalPower -= power;

					// si el propulsor no tiene suficiente potencia. Poner al 100% y repartir el
					// resto entre los otros
				} else {
					accelerateMultiThread(rocket.getId(), i, rocket.getMaxPower(i));
					totalPower -= rocket.getMaxPower(i) - rocket.getPowerBooster(i);
				}

			}
		}
		for (Thread t : threads) {
			//espera que todos los hilos terminen
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
