package com.rockets.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.rockets.controller.RocketController;

public class RocketsApp {

	public static void main(String[] args) {

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

		// calcula dimensiones pantalla

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Dimension screen = toolkit.getScreenSize();

		int screenX = (int) screen.getWidth();
		int screenY = (int) screen.getHeight();

		// crea el panells de control i els separa entre ells.

		try {
			ControlPanel panel1 = new ControlPanel("32WESSDS");

			panel1.setLocation(screenX / 2 + 50, screenY / 2 - 250);

			controller.setObserver("32WESSDS", panel1);

			ControlPanel panel2 = new ControlPanel("LDSFJA32");

			controller.setObserver("LDSFJA32", panel2);

			panel2.setLocation(screenX / 2 - 450, screenY / 2 - 250);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
