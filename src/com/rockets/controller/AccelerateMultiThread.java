package com.rockets.controller;

public class AccelerateMultiThread implements Runnable {

	RocketController controller=new RocketController();
	
	private String id;
	private int booster;
	private double power;


	public AccelerateMultiThread(String id,int booster, double power) {
		
		this.id=id;
		this.booster=booster;
		this.power=power;
	}


	@Override
	public void run() {
		//controller.accelerateByPower(id, booster, power);
	}
}
