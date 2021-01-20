package com.rockets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.rockets.controller.RocketController;

import com.rockets.model.Rocket;
import com.rockets.model.maxPowerException;

public class ControlPanel extends JFrame implements Runnable, ActionListener {

	private void observerJpanel() {
		Thread observerJpanel = new Thread(this);
		observerJpanel.start();
	}

	private double speed = 0;
	private double[] boosterPower;
	private double[] boosterMaxPower;

	private Rocket rocket;
	private String idRocket;
	
	private JLabel labelSpeed = new JLabel();
	private JLabel[] labelBooster;
	private JTextField inputSpeed;
	private JButton stopRocket;

	private RocketController controller = RocketController.getInstance();
	
	private DecimalFormat df2 = new DecimalFormat("0.00");
	
	private DecimalFormat df0 = new DecimalFormat("00");
	
	public ControlPanel(String idRocket) {

		RocketController controller = RocketController.getInstance();

		rocket = controller.getRocket(idRocket);
		this.idRocket = idRocket;

		boosterPower = new double[rocket.getBoosters().length];
		
		boosterMaxPower = new double[rocket.getBoosters().length];
		
		labelBooster = new JLabel[rocket.getBoosters().length];

		
		// calcular dimensions de la pantalla on es reprodueix l'aplicació

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Dimension tamanoPantalla = toolkit.getScreenSize(); // calcula dimensiones pantalla

		
		// construir el panell base

		int anchoPanel = 400;

		int altoPanel = 500;

		int ejeX = (int) tamanoPantalla.getWidth() / 2 - anchoPanel / 2;

		int ejeY = (int) tamanoPantalla.getHeight() / 2 - altoPanel / 2;

		setBounds(ejeX, ejeY, anchoPanel, altoPanel);

		setSize(anchoPanel, altoPanel);
		// títol de la finestra

		setTitle("Panel de control del coet: " + idRocket);

		
		// acció quan tanques la finestra

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		add(panelInfo());
		add(panelControls());

		setVisible(true);// hay que ponerla siempre visible

		observerJpanel();

	}

	private JPanel panelInfo() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.setAlignmentX(LEFT_ALIGNMENT);
		
		LaminaImagen image=new LaminaImagen();	
		panel.add(image);
		
		JLabel infoCoet = new JLabel("Nom del coet: " + idRocket);
		panel.add(infoCoet);

		JLabel infoPropulsors = new JLabel("Número de propulsors: " + rocket.getBoosters().length);
		panel.add(infoPropulsors);
		
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		labelSpeed.setText("Velocitat: " + df2.format(speed) + " km/h.");
		panel.add(labelSpeed);

		for (int i = 0; i < rocket.getBoosters().length; i++) {
			
			boosterMaxPower[i] = rocket.getBoosters()[i].getMaxPower();
			
			labelBooster[i] = new JLabel("Propulsor " + i + " :  " + df0.format(boosterPower[i]) + " de " + df0.format(boosterMaxPower[i]) );
			panel.add(labelBooster[i]);
		}
		
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		//panel.add(panelControls());

		return panel;
	}
	
	private JPanel panelControls() {
		
		JPanel panel = new JPanel();
	
		panel.setLayout(new BorderLayout());
		panel.setAlignmentX(LEFT_ALIGNMENT);
		
		inputSpeed = new JTextField("Introdueix una velocitat i pulsa Enter");
		inputSpeed.addActionListener(this);
		panel.add(inputSpeed,BorderLayout.CENTER);

		stopRocket = new JButton("Parar");
		stopRocket.addActionListener(this);
		panel.add(stopRocket,BorderLayout.SOUTH);

		return panel;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		Object desencadentate = e.getSource();

		if (desencadentate == stopRocket) {
			controller.parar(idRocket);
		}

		if (desencadentate == inputSpeed) {
			try {
				controller.setSpeed(idRocket, Integer.parseInt(inputSpeed.getText()));
			} catch (NumberFormatException ex) {
				inputSpeed.setText("Has d'introduir un número.");
			} catch (maxPowerException ex) {
				inputSpeed.setText(ex.getMessage());
			}

		}

	}

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			speed = rocket.getSpeed();

			for (int i = 0; i < rocket.getBoosters().length; i++) {
				boosterPower[i] = rocket.getBoosters()[i].getPower();

			}

			labelSpeed.setText("Velocitat: " + df2.format(speed) + " km/h.");

			for (int i = 0; i < rocket.getBoosters().length; i++) {
				labelBooster[i].setText("Propulsor " + i + " :" + df0.format(boosterPower[i]) + " de " + df0.format(boosterMaxPower[i]));
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}	
	
}


//for fun. Afegeix una imatge al JFrame.

class LaminaImagen extends JPanel {
	
	private Image rocketImage;
	
	@Override	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		File imageFile=new File("src/com/rockets/graphics/rocket.jpg");
		
		try {
			rocketImage=ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		g.drawImage(rocketImage,5,5, 390,150,null);
		
		this.setSize(395, 155);
	}
	
	
	
	  @Override
	  public Dimension getPreferredSize() {
	     if (rocketImage != null) {

	        return new Dimension(400, 170);
	     }
	     return super.getPreferredSize();
	  }
}

