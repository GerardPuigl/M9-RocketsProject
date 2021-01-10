package com.rockets.model;

public class Rocket {

	private String id;
	private double[] boosters;
	private double[] boostersMaxPower;
	private double speed;

	public Rocket(String id, int boosters) {
		this.id = id;
		this.boosters = new double[boosters];
		this.boostersMaxPower = new double[boosters];

	}

	public String getId() {
		return id;
	}

	public double[] getBoosters() {
		return boosters;
	}

	// setear potencia del cohete impidiendo que supere la potencia máxima o reducir por debajo de 0.
	public void setPowerBooster(int booster, double power) throws Exception {

		if (boostersMaxPower[booster] < power) {

			throw new Exception(" Propulsor " + booster + " ha arribat a la potencia màxima!");

		} else if (0 >= power) {

			throw new Exception(" Propulsor " + booster + " està a 0.");

		} else {

			boosters[booster] = power;
		}
	}

	public double getPowerBooster(int booster) {
		return boosters[booster];
	}

	public void setMaxPower(int booster, double power) {
		boostersMaxPower[booster] = power;
	}

	public double getMaxPower(int booster) {
		return boostersMaxPower[booster];
	}

}
