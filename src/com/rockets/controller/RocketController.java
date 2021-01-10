package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private List<Rocket> rockets = new ArrayList<Rocket>();
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
		
		rocket=getRocket(id);
		
		try {
			for(int i=rocket.getPowerBooster(booster);i<power;i++){//pregunta la potencia actual y crea un bucle de incremento de 1 en 1
				rocket.setPowerBooster(booster,rocket.getPowerBooster(booster)+1);
				System.out.println(rocket.getId() + " Propulsor " + booster +":"+rocket.getPowerBooster(booster));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		


		
	}



}
