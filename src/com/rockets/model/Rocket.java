package com.rockets.model;

import java.text.DecimalFormat;

public class Rocket implements Runnable {

	private String id;
	private Booster[] boosters;
	private double speed;
	private double speedCalculated;
	private double calculatedPower;
	Thread[] threads;

	private static DecimalFormat df2 = new DecimalFormat("#.00");
	
	public Rocket(String id, int nBoosters) {
		this.id = id;

		boosters = new Booster[nBoosters];

		for (int i = 0; i < nBoosters; i++) {

			boosters[i] = new Booster(i);
		}
		threads = new Thread[boosters.length];
	}

	public String getId() {
		return id;
	}

	public Booster[] getBoosters() {
		return boosters;
	}

	// defineix una potencia màxima per cada propulsor

	public void setMaxPower(int id, double power) {
		boosters[id].setMaxPower(power);
	}

	// defineix la potencia objectiu que ha d'arribar el propulsor.

	public void setObjectivePower(int id, double power) {
		try {
			boosters[id].setObjectivePower(power);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// calcula la potencia actual de tots els propulsors

	public double getPower() {

		calculatedPower = 0;

		for (Booster b : boosters) {
			calculatedPower += b.getPower();
		}
		return calculatedPower;
	}


	// mètode "observer" que comprova si el paràmetre de velocitat ha canviat.
	
	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			getSpeed();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}

	// calcula la velocitat, si ha canviat respecte l'anterior registre imprimeix per pantalla

	public double getSpeed() {

		speedCalculated = 100 * Math.sqrt(getPower());

		if (speed != speedCalculated) {
			speed = speedCalculated;
			System.out.println("Velocitat del coet " + id + " : " + df2.format(speed) + " km/h");
		}
		return speed;
	}

	// inicia els threads dels propulsors

	public void arrancar() {

		for (int i = 0; i < boosters.length; i++) {

			threads[i] = new Thread(boosters[i]);
			threads[i].start();
		}
	}

	// interromp els threads dels propulsors

	public void parar() {

		for (Thread t : threads) {
			if (t != null) {
				t.interrupt();
			}
		}
	}

	// inicia acceleració. (Reinicia tots els threads)

	public void accelerar() {

		parar();
		arrancar();

		System.out.println("Coet " + id + " accelerant.");
		for (Booster b : boosters) {
			b.accelerar();
		}

	}

	// inicia frenada. (Reinicia tots els threads)

	public void frenar() {

		parar();
		arrancar();
		System.out.println("Coet " + id + " frenant.");
		for (Booster b : boosters) {
			b.frenar();
		}

	}

}
