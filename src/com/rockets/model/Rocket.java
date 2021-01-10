package com.rockets.model;

public class Rocket {

	private String id;
	private int[] boosters;

	public Rocket(String id, int boosters) {
		this.id = id;
		this.boosters = new int[boosters];

	}

	public String getId() {
		return id;
	}

	public int[] getBoosters() {
		return boosters;
	}
	
	public int getPowerBooster(int booster) {
		return boosters[booster];
	}
	
	public void setPowerBooster(int booster, int power) {
		boosters[booster]=power;
	}

}
