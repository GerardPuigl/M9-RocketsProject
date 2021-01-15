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

	// calcula la potencia que té com a objectiu cada propulsor

	public double getObjectivePower() {

		double power = 0;

		for (Booster b : boosters) {
			power += b.getObjectivePower();
		}
		return power;
	}

	// rep una velocitat i en calcula la potenica.

	public void setSpeed(double speed) {

		powerDistribution(Math.pow(((speed) / 100), 2));

	}

	// distribueix una potencia donada

	public void powerDistribution(double power) {

		if (power > maxPower()) {
			power = maxPower();
			System.out.println("No es pot superar la velocitat: " + 100 * Math.sqrt(maxPower()));
		}

		for (Booster b : boosters) {
			b.setObjectivePower(0);
		}

		while (power >= 1) {
			for (Booster b : boosters) {

				try {
					b.setObjectivePower(b.getObjectivePower() + 1);
					power -= 1;
				} catch (maxPowerException e) {
					power += e.getExtraPower();
				}
			}
		}
		autoAccelerarFrenar();
	}

	// calcula la potenica màxima que pot generar el coet

	public double maxPower() {
		double maxPower = 0;

		for (Booster b : boosters) {
			maxPower += b.getMaxPower();
		}
		return maxPower;
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

	// decideix si frenar o accelerar en funció de la potencia objectiu del coet

	public void autoAccelerarFrenar() {
		if (getPower() < getObjectivePower()) {
			accelerar();
		} else if (getPower() > getObjectivePower()) {
			frenar();
		}

	}
	public void test() {
		
	}
	
}
