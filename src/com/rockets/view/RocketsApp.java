package com.rockets.view;

import javax.management.loading.PrivateClassLoader;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static void main(String[] args) {
		
	 RocketController controller=new RocketController();
	 
	 //crea los cohetes
	 controller.addRocket("32WESSDS", 3);
	 controller.getRocket("32WESSDS").setPowerBooster(0, 10);
	 controller.getRocket("32WESSDS").setPowerBooster(1, 30);
	 controller.getRocket("32WESSDS").setPowerBooster(2, 80);
	 controller.addRocket("LDSFJA32",6);
	 controller.getRocket("LDSFJA32").setPowerBooster(0, 30);
	 controller.getRocket("LDSFJA32").setPowerBooster(1, 40);
	 controller.getRocket("LDSFJA32").setPowerBooster(2, 50);
	 controller.getRocket("LDSFJA32").setPowerBooster(3, 50);
	 controller.getRocket("LDSFJA32").setPowerBooster(4, 30);
	 controller.getRocket("LDSFJA32").setPowerBooster(5, 10);
	 
	 //imprime información de los cohetes
	 System.out.println("Output: \n" + controller.getAllRocketsInfo());
	 
	 
	}

}
