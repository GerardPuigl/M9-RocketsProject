package com.rockets.controller;

public class ReduceMultiThread implements Runnable {

	RocketController controller=new RocketController();
	
	private String id;
	private int booster;
	private int power;


	public ReduceMultiThread(String id,int booster, int power) {
		
		this.id=id;
		this.booster=booster;
		this.power=power;
	}


	@Override
	public void run() {
		controller.reduce(id, booster, power);
	}
}
