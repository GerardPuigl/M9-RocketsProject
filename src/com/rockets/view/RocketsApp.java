package com.rockets.view;

import javax.management.loading.PrivateClassLoader;

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
	 
	 //imprime información de los cohetes
	 System.out.println("Output: \n" + controller.getAllRocketsInfo());
	 
	 //acelerar cohete 1 
	 
	 controller.accelerate("32WESSDS",0,8);
	 controller.accelerate("32WESSDS", 1, 40);
	 controller.accelerate("32WESSDS", 2, 75);
	 
	}

}
