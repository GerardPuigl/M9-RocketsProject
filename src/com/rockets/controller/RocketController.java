package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private List<Rocket> rockets = new ArrayList<Rocket>();
	private String info="";

	// construeix un coet i el posa a la llista
	public void addRocket(String Id, int boosters) {

		rockets.add(new Rocket(Id, boosters));

	}

	//recopila l'informació de tots els cets
	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

	//torna un objecte coet amb un id
	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id))
				return r;
		}
		return null;
	}

	//retorna l'informació d'un coet introduit
	public String getInfoRocket(Rocket r) {

		info = r.getId() + ": ";
		
		for(int b:r.getBoosters()) {
			info=info+b+",";
		}
		return info;
	}

	//defineix una potencia per cada propulsor
	public void setBoosterPower(Rocket r, int booster, int power) {
		r.setPowerBooster(booster, power);
	}
}
