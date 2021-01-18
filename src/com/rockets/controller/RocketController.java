package com.rockets.controller;

import java.text.DecimalFormat;
import java.util.*;

import com.rockets.model.*;

public class RocketController {

	private static List<Rocket> rockets = new ArrayList<Rocket>();
	private String info = "";
	private double maxPower = 0;
	private double totalPower = 0;

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	// construeix un coet i el posa a l'Array

	public void addRocket(String id, int boosters) {
		rockets.add(new Rocket(id, boosters));
	}

	// torna un objecte coet
	
	private Rocket getRocket(String id) {
		
		for (Rocket r : rockets) {
			if(r.getId().equals(id)) {
				return r;
			}
		}
		return null;
		
	}

	// torna el número de propulsors d'un coet
	
	public int getNumBoosters(String id) {

		return getRocket(id).getBoosters().length;

	}

	// torna tota l'informació de tots els coets.
	
	public String getAllRocketsInfo() {

		for (Rocket r : rockets) {
			info = info + getInfoRocket(r) + "\n";
		}
		return info;
	}
			
	// torna l'informació d'un coet.
	
	public String getInfoRocket(Rocket r) {

		int i = 0;

		info = r.getId() + ": \n";

		for (Booster b : r.getBoosters()) {
			i++;
			info = info + "Propulsor " + i + ": " + df2.format(b.getPower()) + " - "+ df2.format(b.getMaxPower()) + "\n";
		}
		return info;
	}
			
	// defineix la potencia màxima d'un propulsor.

	public void setMaxPower(String id, int booster, double power) {
		getRocket(id).setMaxPower(booster, power);
	}


		
	/*int saltoAceleracionReduccion = 1;

	//private List<Thread> threads = new ArrayList<Thread>();

	//private static DecimalFormat df2 = new DecimalFormat("#.##");

	




	// recopila toda la información de los cohetes de la Array



	// devuelve información del cohete introducido


	// Acelerar a una potencia concreta

	public void accelerateByPower(String id, int booster, double power) {

		rocket = getRocket(id);

		try {

			// pregunta la potencia actual y crea un bucle de incremento de 1 en 1
			while (power > saltoAceleracionReduccion) {
				
				power -= saltoAceleracionReduccion;
				
				accelerate(booster);

			}

			rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + power);
			System.out.println(
					rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}

	}

	private void accelerate(int booster) throws Exception, InterruptedException {
		
		rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) + saltoAceleracionReduccion);

		System.out.println(
				rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));
		Thread.sleep(300);
	}

	// Acelereación multihilo
	public void accelerateMultiThread(String id, int booster, double power) {

		Runnable ac1 = new AccelerateMultiThread(id, booster, power);
		Thread ac1_1 = new Thread(ac1);
		threads.add(ac1_1);
		ac1_1.start();

	}

	// Código para distribuir la potencia uniformemente cuando acelera
	public void powerDistributionAccelerate(Rocket rocket, double power) {
	
		while (power > saltoAceleracionReduccion) {
	
			for (int i = 0; i < rocket.getBoosters().length; i++) {
			
				if((rocket.getMaxPower(i)-rocket.getPowerBooster(i))>saltoAceleracionReduccion) {
					
					accelerateMultiThread(rocket.getId(), i, saltoAceleracionReduccion);
					power -= saltoAceleracionReduccion;
					
				}
			}
			
			
			sincroThreads();
		}
	
	}

	// Accelereación multi hilo
	public void reduceMultiThread(String id, int booster, double power) {
		Runnable re1 = new ReduceMultiThread(id, booster, power);
		Thread re1_1 = new Thread(re1);
		threads.add(re1_1);
		re1_1.start();
	}

	// Cálculo de potencia respecto a una velocidad dada
	public void setSpeed(String id, double speed) {
		rocket = getRocket(id);

		totalPower = 0;

		if (rocket.getSpeed() < speed) {

			totalPower = Math.pow(((speed - rocket.getSpeed()) / 100), 2);

			for (int i = 0; i < rocket.getBoosters().length; i++) {

				maxPower = maxPower + rocket.getMaxPower(i) - rocket.getPowerBooster(i);

			}

			if (totalPower > maxPower) {
				System.out.println("No es possible accelerar fins la velocitat sol·licitada.");

			} else {
				powerDistributionAccelerate(rocket, totalPower);
			}
		}

		if (rocket.getSpeed() > speed) {

			totalPower = Math.pow(((rocket.getSpeed() - speed) / 100), 2);

			double totalPower = Math.pow(((rocket.getSpeed() - speed) / 100), 2);

			powerDistributionReduce(rocket, totalPower);

		}
		sincroThreads();

	}

	public void reduce(String id, int booster, double power) {

		rocket = getRocket(id);

		try {

			// pregunta la potencia actual y crea un bucle de dremento de 1 en 1

			while (power > saltoAceleracionReduccion) {

				rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - saltoAceleracionReduccion);
				power -= saltoAceleracionReduccion;

				System.out.println(
						rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));
				Thread.sleep(300);

			}

			rocket.setPowerBooster(booster, rocket.getPowerBooster(booster) - power);
			System.out.println(
					rocket.getId() + " propulsor " + booster + ": " + df2.format(rocket.getPowerBooster(booster)));

		} catch (Exception e) {
			System.out.println(rocket.getId() + e.getMessage());
		}

	}
	// Código para distribuir la potencia uniformemente cuando frena
	public void powerDistributionReduce(Rocket rocket, double totalPower) {
		while (totalPower > 0.1) {

			double power = totalPower / rocket.getBoosters().length;

			for (int i = 0; i < rocket.getBoosters().length; i++) {

				// si el propulsor tiene potencia suficiente para almacenar lo que le toca
				if (rocket.getMaxPower(i) - rocket.getPowerBooster(i) > power) {

					reduceMultiThread(rocket.getId(), i, power);
					totalPower -= power;

					// si el propulsor no tiene suficiente potencia. Poner al 100% y repartir el
					// resto entre los otros
				} else {
					reduceMultiThread(rocket.getId(), i, rocket.getMaxPower(i));
					totalPower -= rocket.getMaxPower(i) - rocket.getPowerBooster(i);
				}

			}
		}

	}

	private void sincroThreads() {
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	
	
	}*/

}
