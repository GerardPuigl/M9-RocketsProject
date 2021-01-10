package com.rockets.model;

public class Rocket {

	private String id;
	private double[] boosters;

	public Rocket(String id, int boosters) {
		this.id = id;
		this.boosters = new double[boosters];

	}

	public String getId() {
		return id;
	}

	public double[] getBoosters() {
		return boosters;
	}

}
