package com.rockets.view;

<<<<<<< HEAD
=======
import java.awt.Dimension;
import java.awt.Toolkit;

>>>>>>> refs/heads/Nivell3
import com.rockets.controller.RocketController;

public class RocketsApp {

	public static boolean wait;
<<<<<<< HEAD
	
	public static void main(String[] args) throws InterruptedException {

		RocketController controller = new RocketController();

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
=======

	public static void main(String[] args) {

		RocketController controller = RocketController.getInstance();

		// crea els coets
>>>>>>> refs/heads/Nivell3
		
<<<<<<< HEAD
				controller.setSpeed("32WESSDS", 1000);
=======
		controller.addRocket("32WESSDS", 3);
		controller.setMaxPower("32WESSDS", 0, 10);
		controller.setMaxPower("32WESSDS", 1, 30);
		controller.setMaxPower("32WESSDS", 2, 80);
>>>>>>> refs/heads/Nivell3

<<<<<<< HEAD
		// fixar una velocitat inferior a l'anterior (obliga a frenar)
=======
		controller.addRocket("LDSFJA32", 6);
		controller.setMaxPower("LDSFJA32", 0, 30);
		controller.setMaxPower("LDSFJA32", 1, 40);
		controller.setMaxPower("LDSFJA32", 2, 50);
		controller.setMaxPower("LDSFJA32", 3, 50);
		controller.setMaxPower("LDSFJA32", 4, 30);
		controller.setMaxPower("LDSFJA32", 5, 10);
>>>>>>> refs/heads/Nivell3

<<<<<<< HEAD
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

=======
		// crea el panells

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Dimension screen = toolkit.getScreenSize(); // calcula dimensiones pantalla

		
		int screenX = (int) screen.getWidth();
		int screenY = (int) screen.getHeight();
		
		try {
		ControlPanel panel1= new ControlPanel("32WESSDS");

			panel1.setLocation(screenX / 2 + 50, screenY / 2 - 250);

			ControlPanel panel2 = new ControlPanel("LDSFJA32");

			panel2.setLocation(screenX / 2 - 450, screenY / 2 - 250);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
>>>>>>> refs/heads/Nivell3
}
