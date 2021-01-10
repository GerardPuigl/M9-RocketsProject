package com.rockets.view;

import javax.management.loading.PrivateClassLoader;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static void main(String[] args) {
		
	 RocketController controller=new RocketController();
	 
	 controller.addRocket("x", 3);
	 controller.addRocket("LDSFJA32",6);
	 
	 System.out.println("Output: \n" + controller.getAllRocketsInfo());
	 
	 
	}

}
