package com.rockets.controller;

import java.text.DecimalFormat;
import java.util.*;

import com.rockets.model.*;
import com.rockets.view.ControlPanel;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();

	// Patron Singleton
	private static RocketController instancia;

	private RocketController() {
	}

	public static RocketController getInstance() {
		if (instancia == null) {
			instancia = new RocketController();
		}
		return instancia;
	}	
	
	// construeix un coet i el posa a l'Array

	public void addRocket(String id, int boosters) {
		rockets.add(new Rocket(id, boosters));
	}

	// torna un objecte coet

	public Rocket getRocket(String id) {

		for (Rocket r : rockets) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;

	}

	// defineix la potencia màxima d'un propulsor.

	public void setMaxPower(String id, int booster, double power) {
		getRocket(id).setMaxPower(booster, power);
	}


	// defineix una velocitat desitjada pel coet

	public void setSpeed(String id, double i) throws maxPowerException {
		getRocket(id).setSpeed(i);
	}

	// defineix la cadencia dels propulsors la pasa als coets
	
	public void setCadencia(String idRocket, int i) {
		getRocket(idRocket).setCadencia(i);

	}

	// informa al coet que ha d'arrancar

	public void arrancar(String id) {

		getRocket(id).arrancar();

	}
	
	// informa al coet que ha de parar

	public void parar(String id) {
		getRocket(id).parar();

	}

	// informa al coet que ha accelerar

	public void accelerar(String id) {

		getRocket(id).accelerar();

	}

	// informa al coet que ha frenar

	public void frenar(String id) {
		getRocket(id).frenar();

	}
	
	// envia l'objecte observer a cada coet

	public void setObserver(String idRocket, ControlPanel panel) {
		getRocket(idRocket).setObserver(panel);
		
	}

}
