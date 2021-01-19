package com.rockets.model;

public class Rocket {

	private String id;
	private Booster[] boosters;
	private double speed;
	Thread[] threads;
	
	public Rocket(String id, int nBoosters) {
		this.id = id;

		boosters = new Booster[nBoosters];
		
		for (int i = 0; i < nBoosters; i++) {

			boosters[i] = new Booster(i);
		}
	}
	
	public String getId() {
		return id;
	}

	public Booster[] getBoosters() {
		return boosters;
	}
		
	//defineix una potencia màxima per cada propulsor
	
	public void setMaxPower(int i, double power) {
		boosters[i].setMaxPower(power);
	}

	public double getMaxPower(int i) {
		return boosters[i].getMaxPower();
	}
	
	//defineix la potencia objectiu que ha d'arribar el propulsor.
	
	public void setObjectivePower(int i, double power) {
		try {
			boosters[i].setObjectivePower(power);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public double getPowerBooster(int i) {
		return boosters[i].getPower();
	}


	public double getSpeed() {
		return speed;
	}
	
	public void arrancar() {

		threads = new Thread[boosters.length];
		
		for (int i=0;i<boosters.length;i++) {

			threads[i] = new Thread(boosters[i]);
			threads[i].start();
			
		}
	
	}
	
	public void parar() {

		if (threads != null) {
			for (Thread t : threads) {
				t.interrupt();
			}
		}

	}
	
	public void accelerar() {
		
		parar();
		arrancar();
		
		System.out.println("Coet " + id + " accelerant.");
		for(Booster b:boosters) {
			b.accelerar();
		}

				
	}

	public void frenar() {
		
		parar();
		arrancar();
		System.out.println("Coet " + id + " frenant.");
		for(Booster b:boosters) {
			b.frenar();
		}

	}
	
	

}
