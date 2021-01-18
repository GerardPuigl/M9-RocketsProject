package com.rockets.model;

public class Rocket {

	private String id;
	private Booster[] boosters;
	private double speed;

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

	public void setPowerBooster(int i, double power) throws Exception {
		boosters[i].setPower(power);
	}

	public double getPowerBooster(int i) {
		return boosters[i].getPower();
	}

	public void setMaxPower(int i, double power) {
		boosters[i].setMaxPower(power);
	}

	public double getMaxPower(int i) {
		return boosters[i].getMaxPower();
	}

	public double getSpeed() {
		return speed;
	}
	
	public void accelerate() {
			
			for (int i=0;i < boosters.length;i++) {
				
				//boosters[i].accelerate();
				
			}
		
			
		
	}

}
