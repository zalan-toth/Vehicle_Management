/**
 * This class handles the Scooter model (concrete).
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package models;

import utils.lang.Colors;

public class Scooter extends Vehicle {

	private int power = 250;
	private int topRiderWeight = 100;
	private float weight = 5;

	private double factor = 15000;

	//░█████╗░░█████╗░███╗░░██╗░██████╗████████╗██████╗░██╗░░░██╗░█████╗░████████╗░█████╗░██████╗░
	//██╔══██╗██╔══██╗████╗░██║██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗
	//██║░░╚═╝██║░░██║██╔██╗██║╚█████╗░░░░██║░░░██████╔╝██║░░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝
	//██║░░██╗██║░░██║██║╚████║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗
	//╚█████╔╝╚█████╔╝██║░╚███║██████╔╝░░░██║░░░██║░░██║╚██████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║
	//░╚════╝░░╚════╝░╚═╝░░╚══╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝
	public Scooter(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, float weight, int topRiderWeight) {
		super(regNumber, model, cost, manufacturer, year);
		setPower(power);
		setWeight(weight);
		setTopRiderWeight(topRiderWeight);
	}

	//░██████╗░███████╗████████╗████████╗███████╗██████╗░░██████╗
	//██╔════╝░██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
	//██║░░██╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
	//██║░░╚██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
	//╚██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
	//░╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░
	public int getPower() {
		return power;
	}

	public int getTopRiderWeight() {
		return topRiderWeight;
	}

	public float getWeight() {
		return weight;
	}

	@Override
    public double getCarbonFootPrint() {
		return getPower() * getWeight() * getAge() / factor;
	}
	//░██████╗███████╗████████╗████████╗███████╗██████╗░░██████╗
	//██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
	//╚█████╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
	//░╚═══██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
	//██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
	//╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


	public void setPower(int power) {
		if ((power >= 250) && (power <= 1000)) {
			this.power = power;
		}
	}

	public void setTopRiderWeight(int topRiderWeight) {
		if ((topRiderWeight >= 100) && (topRiderWeight <= 120)) {
			this.topRiderWeight = topRiderWeight;
		}
	}

	public void setWeight(float weight) {
		if ((weight >= 5) && (weight <= 100)) {
			this.weight = weight;
		}
	}

	//██████
	//EQUALS
	//██████
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Scooter scooter)) return false;
		if (!super.equals(o)) return false;
		return getPower() == scooter.getPower() && getTopRiderWeight() == scooter.getTopRiderWeight() && Float.compare(scooter.getWeight(), getWeight()) == 0;
	}

	//████████╗░█████╗░░██████╗████████╗██████╗░██╗███╗░░██╗░██████╗░
	//╚══██╔══╝██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║████╗░██║██╔════╝░
	//░░░██║░░░██║░░██║╚█████╗░░░░██║░░░██████╔╝██║██╔██╗██║██║░░██╗░
	//░░░██║░░░██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║██║╚████║██║░░╚██╗
	//░░░██║░░░╚█████╔╝██████╔╝░░░██║░░░██║░░██║██║██║░╚███║╚██████╔╝
	//░░░╚═╝░░░░╚════╝░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░
	@Override
	public String toString() {
		return super.toString() + Colors.STARTUP + "\nScooter{" +
				"power=" + power +
				", topRiderWeight=" + topRiderWeight +
				", Weight : " + weight +
				"}\n";
	}
}
