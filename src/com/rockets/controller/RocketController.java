package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private List<Rocket> rockets = new ArrayList<Rocket>();
	private String info="";

	// construye un cohete y lo pone en la lista
	public void addRocket(String Id, int boosters) {

		rockets.add(new Rocket(Id, boosters));

	}

	//recopila toda la información de los cohetes de la Array
	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

	//devuelve un objeto Rocket con el id deseado
	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id))
				return r;
		}
		return null;
	}

	//devuelve información del cohete introducido
	public String getInfoRocket(Rocket r) {

		info = r.getId() + ": ";
		
		for(int b:r.getBoosters()) {
			info=info+b+",";
		}
		return info;
	}

	//define una potencia para un propulso
	public void setBoosterPower(Rocket r, int booster, int power) {
		r.setPowerBooster(booster, power);
	}
}
