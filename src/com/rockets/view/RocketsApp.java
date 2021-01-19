package com.rockets.view;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static boolean wait;
	
	public static void main(String[] args) throws InterruptedException {

		RocketController controller = RocketController.getInstance();

		// crea els coets
		controller.addRocket("32WESSDS", 3);
		controller.setMaxPower("32WESSDS", 0, 10);
		controller.setMaxPower("32WESSDS", 1, 30);
		controller.setMaxPower("32WESSDS", 2, 80);

		controller.addRocket("LDSFJA32", 6);
		controller.setMaxPower("LDSFJA32", 0, 30);
		controller.setMaxPower("LDSFJA32", 1, 40);
		controller.setMaxPower("LDSFJA32", 2, 50);
		controller.setMaxPower("LDSFJA32", 3, 50);
		controller.setMaxPower("LDSFJA32", 4, 30);
		controller.setMaxPower("LDSFJA32", 5, 10);

		// fixar una velocitat superior a inicial (obliga a accelerar)
		
				controller.setSpeed("32WESSDS", 1000);

		// fixar una velocitat inferior a l'anterior (obliga a frenar)

		Thread.sleep(30000);
		controller.setSpeed("32WESSDS", 800);

		
		/*Nivell 1
		// fixa potencia objectiu
		controller.setObjectivePower("32WESSDS", 0, 30);
		controller.setObjectivePower("32WESSDS", 1, 5.5);
		controller.setObjectivePower("32WESSDS", 2, 30);

		// accelera el coet

		controller.accelerar("32WESSDS");

		Thread.sleep(2050);

		controller.frenar("32WESSDS");

		Thread.sleep(4050);

		System.out.println("\nInformació coets: \n" + controller.getAllRocketsInfo());
		
		*/

	}

}
