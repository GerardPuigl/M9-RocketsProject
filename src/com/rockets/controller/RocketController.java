package com.rockets.controller;

import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private List<Rocket> rockets = new ArrayList<Rocket>();
	private String info="";

	// construeix un coet i el posa a l'Array
	public void addRocket(String Id, int boosters) {

		rockets.add(new Rocket(Id, boosters));

	}

	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id))
				return r;
		}
		return null;
	}

	public String getInfoRocket(Rocket r) {

		info = "Codi del cohet: " + r.getId() + ". Número de propulsors: " + r.getBoosters().length;
		return info;
	}

}
