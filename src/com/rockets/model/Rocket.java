package com.rockets.model;

public class Rocket {

	private String id;
	private int[] boosters;
	private int[] boostersMaxPower;

	public Rocket(String id, int boosters) {
		this.id = id;
		this.boosters = new int[boosters];
		this.boostersMaxPower = new int[boosters];

	}

	public String getId() {
		return id;
	}

	public int[] getBoosters() {
		return boosters;
	}

	public void setPowerBooster(int booster, int power) throws Exception {
		
		if (boostersMaxPower[booster] < power) {
			
			throw new Exception("Propulsor " + booster + " ha alcanzado la máxima potencia.");

		} else {
			boosters[booster] = power;
		}
	}

	public int getPowerBooster(int booster) {
		return boosters[booster];
	}

	public void setMaxPower(int booster, int power) {
		boostersMaxPower[booster] = power;
	}

	public int getMaxPower(int booster) {
		return boostersMaxPower[booster];
	}

}
