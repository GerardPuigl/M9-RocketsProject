package com.rockets.controller;

public class AccelerateMultiThread implements Runnable {

	RocketController controller=new RocketController();
	
	private String id;
	private int booster;
	private int power;


	public AccelerateMultiThread(String id,int booster, int power) {
		
		this.id=id;
		this.booster=booster;
		this.power=power;
	}


	@Override
	public void run() {
		controller.accelerate(id, booster, power);
	}
}
