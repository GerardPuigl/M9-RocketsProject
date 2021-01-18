package com.rockets.controller;

public class ReduceMultiThread implements Runnable {

	RocketController controller=new RocketController();
	
	private String id;
	private int booster;
	private double power;

	public ReduceMultiThread(String id,int booster, double power) {
		
		this.id=id;
		this.booster=booster;
		this.power=power;
	}


	@Override
	public void run() {
		controller.reduce(id, booster, power);
	}
}
