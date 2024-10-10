/**
 * This class handles Carbon Fuel Cars (concrete).
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package models;

import utils.lang.Colors;

import java.util.Objects;

public class CarbonFuelCar extends Car {
	private float fuelConsumption = 5;
	private float carbonEmission = 1;
	private boolean automatic = false;
	private String fuelType = "petrol";
	private int engineSize = 800;

	private int factor = 2000;


	//░█████╗░░█████╗░███╗░░██╗░██████╗████████╗██████╗░██╗░░░██╗░█████╗░████████╗░█████╗░██████╗░
	//██╔══██╗██╔══██╗████╗░██║██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗
	//██║░░╚═╝██║░░██║██╔██╗██║╚█████╗░░░░██║░░░██████╔╝██║░░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝
	//██║░░██╗██║░░██║██║╚████║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗
	//╚█████╔╝╚█████╔╝██║░╚███║██████╔╝░░░██║░░░██║░░██║╚██████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║
	//░╚════╝░░╚════╝░╚═╝░░╚══╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝


	public CarbonFuelCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed, float fuelConsumption, float carbonEmission, boolean automatic, String fuelType, int engineSize) {
		super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
		setFuelConsumption(fuelConsumption);
		setCarbonEmission(carbonEmission);
		setAutomatic(automatic);
		setFuelType(fuelType);
		setEngineSize(engineSize);
	}


	//░██████╗░███████╗████████╗████████╗███████╗██████╗░░██████╗
	//██╔════╝░██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
	//██║░░██╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
	//██║░░╚██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
	//╚██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
	//░╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


	public float getFuelConsumption() {
		return fuelConsumption;
	}

	public float getCarbonEmission() {
		return carbonEmission;
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public String getFuelType() {
		return fuelType;
	}

	public int getEngineSize() {
		return engineSize;
	}

	@Override
	public double getCarbonFootPrint() {
		return (getEngineSize() * getFuelConsumption() * getCarbonEmission() * getAge()) / factor;
	}

	//░██████╗███████╗████████╗████████╗███████╗██████╗░░██████╗
	//██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
	//╚█████╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
	//░╚═══██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
	//██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
	//╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


	public void setFuelConsumption(float fuelConsumption) {
		if ((fuelConsumption >= 5) && (fuelConsumption <= 20)) {
			this.fuelConsumption = fuelConsumption;
		}
	}

	public void setCarbonEmission(float carbonEmission) {
		if (carbonEmission > 1) {
			this.carbonEmission = carbonEmission;
		}
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}

	public void setFuelType(String fuelType) {
		if (utils.FuelTypeUtility.validFuelType(fuelType)) {
			this.fuelType = fuelType;
		}
	}

	public void setEngineSize(int engineSize) {
		if ((engineSize >= 800) && (engineSize <= 2500)) {
			this.engineSize = engineSize;
		}
	}


	//████████╗░█████╗░░██████╗████████╗██████╗░██╗███╗░░██╗░██████╗░
	//╚══██╔══╝██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║████╗░██║██╔════╝░
	//░░░██║░░░██║░░██║╚█████╗░░░░██║░░░██████╔╝██║██╔██╗██║██║░░██╗░
	//░░░██║░░░██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║██║╚████║██║░░╚██╗
	//░░░██║░░░╚█████╔╝██████╔╝░░░██║░░░██║░░██║██║██║░╚███║╚██████╔╝
	//░░░╚═╝░░░░╚════╝░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░


	@Override
	public String toString() {
		return super.toString() + Colors.SORT + "CarbonFuelCar{" +
				"fuelConsumption=" + fuelConsumption +
				", carbonEmission=" + carbonEmission +
				", automatic=" + automatic +
				", fuelType='" + fuelType +
				", engineSize=" + engineSize +
				", factor=" + factor +
				"}\n";
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CarbonFuelCar that)) return false;
		if (!super.equals(o)) return false;
		return Float.compare(that.getFuelConsumption(), getFuelConsumption()) == 0 && Float.compare(that.getCarbonEmission(), getCarbonEmission()) == 0 && isAutomatic() == that.isAutomatic() && getEngineSize() == that.getEngineSize() && factor == that.factor && Objects.equals(getFuelType(), that.getFuelType());
	}

}
