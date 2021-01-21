package com.rockets.controller;

import java.text.DecimalFormat;
import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();
	private String info = "";

	private static DecimalFormat df2 = new DecimalFormat("#.##");

<<<<<<< HEAD
=======
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
	
>>>>>>> refs/heads/Nivell3
	// construeix un coet i el posa a l'Array

	public void addRocket(String id, int boosters) {
		rockets.add(new Rocket(id, boosters));
		Thread observerRocketThread = new Thread(getRocket(id));
		observerRocketThread.start();
	}

<<<<<<< HEAD
	// torna un objecte coet

	private Rocket getRocket(String id) {
=======
	// torna una llista d'objectes

	public static List<Rocket> getRocketsList() {
		return rockets;
	}
	
	// torna un objecte coet

	public Rocket getRocket(String id) {
>>>>>>> refs/heads/Nivell3

		for (Rocket r : rockets) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
<<<<<<< HEAD

	}

	// torna el número de propulsors d'un coet

	public int getNumBoosters(String id) {

		return getRocket(id).getBoosters().length;
=======
>>>>>>> refs/heads/Nivell3

	}

	// torna tota l'informació de tots els coets.

	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}

<<<<<<< HEAD
=======
	// torna el número de propulsors d'un coet

	public int getNumBoosters(String id) {

		return getRocket(id).getBoosters().length;

	}

>>>>>>> refs/heads/Nivell3
	// torna l'informació d'un coet.

	public String getInfoRocket(Rocket r) {

		int i = 0;

		info = r.getId() + ": \n";

		for (Booster b : r.getBoosters()) {
			i++;
			info = info + "Propulsor " + i + ": " + df2.format(b.getPower()) + " de " + df2.format(b.getMaxPower())
					+ "\n";
		}
		return info;
	}

	// defineix la potencia màxima d'un propulsor.

	public void setMaxPower(String id, int booster, double power) {
		getRocket(id).setMaxPower(booster, power);
	}

	// defineix la potencia d'un propulsor.

	public void setObjectivePower(String id, int booster, double power) {
		getRocket(id).setObjectivePower(booster, power);

	}

<<<<<<< HEAD
	// informa al coet que ha d'arrancar

	public void arrancarCoet(String id) {
=======
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
>>>>>>> refs/heads/Nivell3

		getRocket(id).arrancar();

	}
<<<<<<< HEAD

=======
	
>>>>>>> refs/heads/Nivell3
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

<<<<<<< HEAD
	// defineix una velocitat desitjada pel coet

	public void setSpeed(String id, int i) {
		getRocket(id).setSpeed(i);
	}

=======
>>>>>>> refs/heads/Nivell3
}
