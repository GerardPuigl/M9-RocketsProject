package com.rockets.view;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static void main(String[] args) {
		
	 RocketController controller=new RocketController();

	 
	 //crea los cohetes
	 controller.addRocket("32WESSDS", 3);
	 controller.setMaxPower("32WESSDS",0, 10);
	 controller.setMaxPower("32WESSDS",1, 30);
	 controller.setMaxPower("32WESSDS",2, 80);
	 controller.addRocket("LDSFJA32",6);
	 controller.setMaxPower("LDSFJA32",0, 30);
	 controller.setMaxPower("LDSFJA32",1, 40);
	 controller.setMaxPower("LDSFJA32",2, 50);
	 controller.setMaxPower("LDSFJA32",3, 50);
	 controller.setMaxPower("LDSFJA32",4, 30);
	 controller.setMaxPower("LDSFJA32",5, 10);
	 
	 //reparte la potencia en función de una velocidad dada
	 controller.setSpeed("32WESSDS", 200);
	 
	 try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 //imprime información de los cohetes
	 System.out.println("Output: \n" + controller.getAllRocketsInfo());

	}

}
