package com.rockets.model;

import java.text.DecimalFormat;

public class Rocket {

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

	// defineix una potència màxima per cada propulsor

	public void setMaxPower(int id, double power) {
		boosters[id].setMaxPower(power);
	}

	// defineix la potència objectiu que ha d'arribar el propulsor.

	public void setObjectivePower(int id, double power) {
		try {
			boosters[id].setObjectivePower(power);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// calcula la potència actual de tots els propulsors

	public double getPower() {

		calculatedPower = 0;

		for (Booster b : boosters) {
			calculatedPower += b.getPower();
		}
		return calculatedPower;
	}

	// calcula la potència que té com a objectiu cada propulsor

	public double getObjectivePower() {

		double power = 0;

		for (Booster b : boosters) {
			power += b.getObjectivePower();
		}
		return power;
	}

	// rep una velocitat i en calcula la potenica.

	public void setSpeed(double speed) throws maxPowerException {

		powerDistribution(Math.pow(((speed) / 100), 2));

	}
	
	public double getMaxSpeed () {
		double maxSpeed=100 * Math.sqrt(maxPower());
		return maxSpeed;
	}

	// distribueix una potència donada uniformement fins que el propulsor està ple.

	public void powerDistribution(double power) throws maxPowerException {

		if (power > maxPower()) {
			power = maxPower();
			System.out.println("No es pot superar la velocitat: " + df2.format(getMaxSpeed()) + " km/h.");
			throw new maxPowerException("No es pot superar la velocitat: " + df2.format(getMaxSpeed()) + " km/h.", 0);
		}

		for (Booster b : boosters) {
			b.setObjectivePower(0);
		}
		
		// accuray per millorar el repartiment de potencia a nivell decimal i acconseguir la velocitat desitjada.
		// dubte: com es podria fer d'una manera exacta?
		
		double accuray=1;
		while (power >= 0) {
			
			if(power <= 1*boosters.length && power >= 0.01*boosters.length) accuray=0.01;
			if(power <= 0.01*boosters.length) accuray=0.00001;
			
			
			for (Booster b : boosters) {

				try {
					b.setObjectivePower(b.getObjectivePower() + accuray);
					power -= accuray;
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

	// calcula la velocitat, si ha canviat respecte l'anterior registre imprimeix
	// per pantalla

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

	// decideix si frenar o accelerar en funció de la potència objectiu del coet

	public void autoAccelerarFrenar() {
		if (getPower() < getObjectivePower()) {
			accelerar();
		} else if (getPower() > getObjectivePower()) {
			frenar();
		}

	}

	public void setCadencia(int cadencia) {
		for (Booster b : boosters) {
			b.setCadencia(cadencia);
		}
	}

}
