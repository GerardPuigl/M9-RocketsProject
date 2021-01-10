package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();
	private String info = "";
	private Rocket rocket;

	// construye un cohete y lo pone en la lista
	public void addRocket(String Id, int boosters) {

		rockets.add(new Rocket(Id, boosters));

	}

	// recopila toda la información de los cohetes de la Array
	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

	// devuelve un objeto Rocket con el id dado
	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id))
				return r;
		}
		return null;
	}

	// devuelve información del cohete introducido
	public String getInfoRocket(Rocket r) {

		info = r.getId() + ": ";

		for (int b : r.getBoosters()) {
			info = info + b + ",";
		}
		return info;
	}

	// define una potencia máxima para un propulso
	public void setMaxPower(String id, int booster, int power) {
		getRocket(id).setMaxPower(booster, power);
	}

	// Acelerar a una potencia concreta
	public void accelerate(String id, int booster, int power) {

		rocket = getRocket(id);

		try {
			// pregunta la potencia actual y crea un bucle de incremento de 1 en 1
			for (int i = rocket.getPowerBooster(booster); i < power; i++) {
				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + 1);
				System.out.println(rocket.getId() + " propulsor " + booster + ":" + rocket.getPowerBooster(booster));
				Thread.sleep(300);
			}
			System.out.println(rocket.getId() + " propulsor " + booster + " ha arribat la potencia sol·licitada.");

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}

	}

	// Acelereación multi hilo
	public void accelerateMultiThread(String id, int booster, int power) {
		Runnable ac1 = new AccelerateMultiThread(id, booster, power);
		Thread ac1_1 = new Thread(ac1);
		ac1_1.start();
	}

	// Reducir a una potencia concreta
	public void reduce(String id, int booster, int power) {

		rocket = getRocket(id);

		// pregunta la potencia actual y crea un bucle de reducción de 1 en 1
		try {
			for (int i = rocket.getPowerBooster(booster); i > power; i--) {
				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - 1);
				System.out.println(rocket.getId() + " propulsor " + booster + ":" + rocket.getPowerBooster(booster));
				Thread.sleep(300);
			}
			System.out.println(rocket.getId() + " propulsor " + booster + " ha arribat la potencia sol·licitada.");

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

}
