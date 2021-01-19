package com.rockets.view;

import java.awt.desktop.ScreenSleepEvent;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static boolean wait;
	
	public static void main(String[] args) {
		
	 RocketController controller=new RocketController();

	 
	 //crea los cohetes
	 controller.addRocket("32WESSDS", 3);
	 controller.setMaxPower("32WESSDS",0, 10);
	 controller.setMaxPower("32WESSDS",1, 30);
	 controller.setMaxPower("32WESSDS",2, 80);
	 
	 //fixa potencia objectiu 
	 controller.setObjectivePower("32WESSDS",0, 30);
	 controller.setObjectivePower("32WESSDS",1, 5.5);
	 controller.setObjectivePower("32WESSDS",2, 30); 
	 
	 
	 
	 controller.addRocket("LDSFJA32",6);
	 controller.setMaxPower("LDSFJA32",0, 30);
	 controller.setMaxPower("LDSFJA32",1, 40);
	 controller.setMaxPower("LDSFJA32",2, 50);
	 controller.setMaxPower("LDSFJA32",3, 50);
	 controller.setMaxPower("LDSFJA32",4, 30);
	 controller.setMaxPower("LDSFJA32",5, 10);
	 
	 //accelera el coet
	 
	 controller.accelerar("32WESSDS");
	 

		try {
			Thread.sleep(2050);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("parar coet.");
		// controller.parar("32WESSDS");


	 
	 try {
		Thread.sleep(2050);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 controller.frenar("32WESSDS");
	 
	 /*
	 //reparte la potencia en función de una velocidad dada
	 controller.setSpeed("32WESSDS", 1000);
	 controller.setSpeed("32WESSDS", 500); 
	 //imprime información de los cohetes
	  */
	 try {
		Thread.sleep(4050);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 System.out.println("\nInformació coets: \n" + controller.getAllRocketsInfo());

	}

}
