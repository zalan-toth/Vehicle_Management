/**
 * This class handles the ArrayList of the vehicles.
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.Serializer;
import utils.lang.Colors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VehicleAPI implements Serializer {

	private List<Vehicle> vehicles = new ArrayList<>();

	public VehicleAPI(File file) {
		this.file = file;
	}

	private File file;


	//██╗░░░██╗░█████╗░██╗░░░░░██╗██████╗░░█████╗░████████╗██╗░█████╗░███╗░░██╗
	//██║░░░██║██╔══██╗██║░░░░░██║██╔══██╗██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║
	//╚██╗░██╔╝███████║██║░░░░░██║██║░░██║███████║░░░██║░░░██║██║░░██║██╔██╗██║
	//░╚████╔╝░██╔══██║██║░░░░░██║██║░░██║██╔══██║░░░██║░░░██║██║░░██║██║╚████║
	//░░╚██╔╝░░██║░░██║███████╗██║██████╔╝██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║
	//░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝

	/**
	 * Returns if the regNumber already does not exist
	 *
	 * @param regNumber regNumber
	 * @return Returns if the regNumber already does not exist
	 */
	public boolean isValidNewRegNumber(String regNumber) {
		for (Vehicle vehicle : vehicles)
			if (vehicle.getRegNumber().equals(regNumber))
				return false;
		return true;
	}

	/**
	 * Returns if the regNumber is valid in the arraylist
	 *
	 * @param regNumber regNumber
	 * @return Returns if the regNumber is valid in the arraylist
	 */
	public boolean isValidRegNumber(String regNumber) {
		for (Vehicle vehicle : vehicles)
			if (vehicle.getRegNumber().equals(regNumber))
				return true;
		return false;
	}

	/**
	 * Returns if the index is valid in the arraylist
	 *
	 * @param index index
	 * @return Returns if the index is valid in the arraylist
	 */
	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < vehicles.size());
	}
	//ARRAY
	//██╗░░░░░██╗░██████╗████████╗    ░█████╗░██████╗░██╗░░░██╗██████╗░
	//██║░░░░░██║██╔════╝╚══██╔══╝    ██╔══██╗██╔══██╗██║░░░██║██╔══██╗
	//██║░░░░░██║╚█████╗░░░░██║░░░    ██║░░╚═╝██████╔╝██║░░░██║██║░░██║
	//██║░░░░░██║░╚═══██╗░░░██║░░░    ██║░░██╗██╔══██╗██║░░░██║██║░░██║
	//███████╗██║██████╔╝░░░██║░░░    ╚█████╔╝██║░░██║╚██████╔╝██████╔╝
	//╚══════╝╚═╝╚═════╝░░░░╚═╝░░░    ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═════╝░


	/**
	 * Adds a vehicle to the arraylist
	 *
	 * @param vehicle New vehicle
	 * @return Returns if the add was successful in a boolean value
	 */
	public boolean addVehicle(Vehicle vehicle) {
		if ((vehicle == null) || (vehicle.getRegNumber() == "No reg") || (vehicle.getModel() == "No model")) {
			return false;
		}
		return vehicles.add(vehicle);
	}

	/**
	 * Removes the vehicle by the given index from the arraylist
	 *
	 * @param indexToDelete index
	 * @return Returns the removed vehicle or null if unsuccessful
	 */
	public Vehicle deleteVehicleByIndex(int indexToDelete) {
		if (isValidIndex(indexToDelete)) {
			return vehicles.remove(indexToDelete);
		}
		return null;
	}

	/**
	 * Removes the vehicle by the given regNumber from the arraylist
	 *
	 * @param regNumber regNumber
	 * @return Returns the removed vehicle or null if unsuccessful
	 */
	public Vehicle deleteVehicleByRegNumber(String regNumber) {
		if (isValidRegNumber(regNumber)) {
			return vehicles.remove(getIndexByRegNumber(regNumber));
		}
		return null;
	}

	//░██████╗░███████╗████████╗
	//██╔════╝░██╔════╝╚══██╔══╝
	//██║░░██╗░█████╗░░░░░██║░░░
	//██║░░╚██╗██╔══╝░░░░░██║░░░
	//╚██████╔╝███████╗░░░██║░░░
	//░╚═════╝░╚══════╝░░░╚═╝░░░

	/**
	 * Provides the index by the given regNumber
	 *
	 * @param regNumber regNumber
	 * @return Returns the index
	 */
	public int getIndexByRegNumber(String regNumber) {
		int index = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getRegNumber().equals(regNumber))
				return index;
			index++;
		}
		return 0;
	}

	/**
	 * Provides the vehicle by the given index
	 *
	 * @param index index
	 * @return Returns the vehicle
	 */
	public Vehicle getVehicleByIndex(int index) {
		int indexSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			if (indexSoFar == index)
				return vehicle;
			indexSoFar++;
		}
		return null;
	}

	/**
	 * Provides the vehicle by the given regNumber
	 *
	 * @param regNumber regNumber
	 * @return Returns the vehicle
	 */
	public Vehicle getVehicleByRegNumber(String regNumber) {
		if (isValidRegNumber(regNumber)) {
			for (Vehicle vehicle : vehicles) {
				if (regNumber.equals(vehicle.getRegNumber())) {
					return vehicle;
				}
			}
		}
		return null;
	}

	//██████╗░███████╗██████╗░░█████╗░██████╗░████████╗██╗███╗░░██╗░██████╗░
	//██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██║████╗░██║██╔════╝░
	//██████╔╝█████╗░░██████╔╝██║░░██║██████╔╝░░░██║░░░██║██╔██╗██║██║░░██╗░
	//██╔══██╗██╔══╝░░██╔═══╝░██║░░██║██╔══██╗░░░██║░░░██║██║╚████║██║░░╚██╗
	//██║░░██║███████╗██║░░░░░╚█████╔╝██║░░██║░░░██║░░░██║██║░╚███║╚██████╔╝
	//╚═╝░░╚═╝╚══════╝╚═╝░░░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚═════╝░

	/**
	 * Provides a list of vehicles
	 *
	 * @return Returns the list of vehicles
	 */
	public String listAllVehicles() {
		if (vehicles.equals(null)) {
			return "No vehicles";
		}
		String str = "";
		for (Vehicle vehicle : vehicles) {
			str += vehicle + "\n";
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of scooters
	 *
	 * @return Returns the list of vehicles
	 */
	public String listAllScooters() {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Scooter) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of electric cars
	 *
	 * @return Returns the list of vehicles
	 */
	public String listAllElectricCars() {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof ElectricCar) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of carbon fuel cars
	 *
	 * @return Returns the list of vehicles
	 */
	public String listAllCarbonFuelCars() {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof CarbonFuelCar) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}


	/**
	 * Provides a list of carbon fuel cars by a given fuel type
	 *
	 * @param fuelType Fuel type
	 * @return Returns the list of vehicles
	 */
	public String listAllCarbonFuelsByFuelType(String fuelType) {
		String str = "";
		if (!utils.FuelTypeUtility.validFuelType(fuelType)) {
			return "Fuel type is not valid.";
		}
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof CarbonFuelCar) {
				if (((CarbonFuelCar) vehicle).getFuelType().equals(fuelType)) {
					str += vehicle + "\n";
				}
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of vehicles by a given manufacturer
	 *
	 * @param manufacturer Manufacturer
	 * @return Returns the list of vehicles
	 */
	public String listAllVehicleByChosenManufacturer(Manufacturer manufacturer) {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getManufacturer().equals(manufacturer)) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of vehicles equal to a given year
	 *
	 * @param year Given year
	 * @return Returns the list of vehicles
	 */
	public String listAllVehiclesEqualToAGivenYear(int year) {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getYear() == year) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides a list of vehicles after a given year
	 *
	 * @param year Given year
	 * @return Returns the list of vehicles
	 */
	public String listAllVehiclesAfterAGivenYear(int year) {
		String str = "";
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getYear() > year) {
				str += vehicle + "\n";
			}
		}
		if (str == "") {
			return "No vehicles";
		}
		return str;
	}

	/**
	 * Provides the number of vehicles
	 *
	 * @return Returns the amount
	 */
	public int numberOfVehicles() {
		int numberSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			numberSoFar++;
		}
		return numberSoFar;
	}

	/**
	 * Provides the number of scooters
	 *
	 * @return Returns the amount
	 */
	public int numberOfScooters() {
		int numberSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof Scooter) {
				numberSoFar++;
			}
		}
		return numberSoFar;
	}

	/**
	 * Provides the number of electric cars
	 *
	 * @return Returns the amount
	 */
	public int numberOfElectricCars() {
		int numberSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof ElectricCar) {
				numberSoFar++;
			}
		}
		return numberSoFar;
	}

	/**
	 * Provides the number of carbon fuel cars
	 *
	 * @return Returns the amount
	 */
	public int numberOfCarbonCars() {
		int numberSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof CarbonFuelCar) {
				numberSoFar++;
			}
		}
		return numberSoFar;
	}

	/**
	 * Provides the number of vehicles by manufacturer
	 *
	 * @param manufacturer Manufacturer to select
	 * @return Returns the amount
	 */
	public int numberOfVehicleByChosenManufacturer(Manufacturer manufacturer) {
		int numberSoFar = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getManufacturer().equals(manufacturer)) {
				numberSoFar++;
			}
		}
		return numberSoFar;
	}


	//██╗░░░██╗██████╗░██████╗░░█████╗░████████╗███████╗
	//██║░░░██║██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝
	//██║░░░██║██████╔╝██║░░██║███████║░░░██║░░░█████╗░░
	//██║░░░██║██╔═══╝░██║░░██║██╔══██║░░░██║░░░██╔══╝░░
	//╚██████╔╝██║░░░░░██████╔╝██║░░██║░░░██║░░░███████╗
	//░╚═════╝░╚═╝░░░░░╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚══════╝

	/**
	 * Update ElectricCar
	 *
	 * @param regNo      regNumber (the unique ID to change)
	 * @param updatedCar The updated ElectricCar
	 * @return Returns if update was successful
	 */
	public boolean upDateElectricCar(String regNo, ElectricCar updatedCar) {
		if (isValidRegNumber(regNo)) {
			return null != vehicles.set(getIndexByRegNumber(regNo), updatedCar);
		}

		return false;
	}

	/**
	 * Update CarbonFuelCar
	 *
	 * @param regNo      regNumber (the unique ID to change)
	 * @param updatedCar The updated CarbonFuelCar
	 * @return Returns if update was successful
	 */
	public boolean upDateCarbonFuelCar(String regNo, CarbonFuelCar updatedCar) {
		if (isValidRegNumber(regNo)) {
			return null != vehicles.set(getIndexByRegNumber(regNo), updatedCar);
		}

		return false;
	}

	/**
	 * Update Scooter
	 *
	 * @param regNo      regNumber (the unique ID to change)
	 * @param updatedCar The updated Scooter
	 * @return Returns if update was successful
	 */
	public boolean upDateScooter(String regNo, Scooter updatedCar) {
		if (isValidRegNumber(regNo)) {
			return null != vehicles.set(getIndexByRegNumber(regNo), updatedCar);
		}

		return false;
	}

	//░██████╗░█████╗░██████╗░████████╗
	//██╔════╝██╔══██╗██╔══██╗╚══██╔══╝
	//╚█████╗░██║░░██║██████╔╝░░░██║░░░
	//░╚═══██╗██║░░██║██╔══██╗░░░██║░░░
	//██████╔╝╚█████╔╝██║░░██║░░░██║░░░
	//╚═════╝░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░

	/**
	 * Sorts the vehicles by cost in descending order
	 */
	public void sortByCostDescending() {
		for (int checkNumber = 0; vehicles.size() > checkNumber; checkNumber++) {
			int highestSoFarPos = checkNumber;
			for (int inner = checkNumber; inner < vehicles.size(); inner++) {
				if (vehicles.get(inner).getCost() > vehicles.get(highestSoFarPos).getCost()) {
					highestSoFarPos = inner;
				}
			}
			if (vehicles.get(highestSoFarPos).getCost() > vehicles.get(checkNumber).getCost()) {
				swapProducts(vehicles, highestSoFarPos, checkNumber);
			}
		}
	}

	/**
	 * Sorts the vehicles by age in ascending order
	 */
	public void sortByAgeAscending() {
		for (int checkNumber = 0; vehicles.size() > checkNumber; checkNumber++) {
			int highestSoFarPos = checkNumber; //highestSoFarPos is actually the lowest in this sorting method!
			for (int inner = checkNumber; inner < vehicles.size(); inner++) {
				if (vehicles.get(inner).getAge() < vehicles.get(highestSoFarPos).getAge()) {
					highestSoFarPos = inner;
				}
			}
			if (vehicles.get(highestSoFarPos).getAge() < vehicles.get(checkNumber).getAge()) {
				swapProducts(vehicles, highestSoFarPos, checkNumber);
			}
		}
	}

	/**
	 * Sorts the vehicles by carbon footprint in ascending order
	 */
	public void sortByCarbonFootprintAscending() {
		for (int checkNumber = 0; vehicles.size() > checkNumber; checkNumber++) {
			int highestSoFarPos = checkNumber; //highestSoFarPos is actually the lowest in this sorting method!
			for (int inner = checkNumber; inner < vehicles.size(); inner++) {
				if (vehicles.get(inner).getCarbonFootPrint() < vehicles.get(highestSoFarPos).getCarbonFootPrint()) {
					highestSoFarPos = inner;
				}
			}
			if (vehicles.get(highestSoFarPos).getCarbonFootPrint() < vehicles.get(checkNumber).getCarbonFootPrint()) {
				swapProducts(vehicles, highestSoFarPos, checkNumber);
			}
		}
	}

	private void swapProducts(List<Vehicle> vehicles, int firstToSwap, int secondToSwap) {
		Vehicle one = vehicles.get(firstToSwap);
		Vehicle two = vehicles.get(secondToSwap);

		vehicles.set(firstToSwap, two);
		vehicles.set(secondToSwap, one);
	}


	//░█████╗░████████╗██╗░░██╗███████╗██████╗░
	//██╔══██╗╚══██╔══╝██║░░██║██╔════╝██╔══██╗
	//██║░░██║░░░██║░░░███████║█████╗░░██████╔╝
	//██║░░██║░░░██║░░░██╔══██║██╔══╝░░██╔══██╗
	//╚█████╔╝░░░██║░░░██║░░██║███████╗██║░░██║
	//░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝


	/**
	 * Provides the 5 lowest carbon footprint vehicles
	 *
	 * @return Returns the 5 lowest carbon footprint vehicles
	 */
	public String topFiveCarbonVehicles() { //top 5 lowest by carbon footprint
		sortByCarbonFootprintAscending();
		ArrayList<Vehicle> topFiveArray = new ArrayList<>();
		int k = 5;
		if (vehicles.size() < 5) {
			k = vehicles.size();
		}
		for (int i = 0; i < k; i++) {
			topFiveArray.add(vehicles.get(i)); //adding the top 5 vehicles into an array
			//str+= Colors.WARNING + "["+ i+1 +"]" + Colors.RESET + " " +  vehicles.get(i)+"\n"; //doing the same with String
		}

		String str = "";
		int top = 1;
		for (Vehicle vehicle : topFiveArray) {
			str += Colors.WARNING + "[" + top + "]" + Colors.RESET + " " + vehicle.toString() + "\n";
			top++;
		}
		if (str.isEmpty()) {
			str = "No vehicles";
		}
		return str;
	}

	//██████╗░███████╗██████╗░░██████╗██╗░██████╗████████╗███████╗███╗░░██╗░█████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██╔════╝██║██╔════╝╚══██╔══╝██╔════╝████╗░██║██╔══██╗██╔════╝
	//██████╔╝█████╗░░██████╔╝╚█████╗░██║╚█████╗░░░░██║░░░█████╗░░██╔██╗██║██║░░╚═╝█████╗░░
	//██╔═══╝░██╔══╝░░██╔══██╗░╚═══██╗██║░╚═══██╗░░░██║░░░██╔══╝░░██║╚████║██║░░██╗██╔══╝░░
	//██║░░░░░███████╗██║░░██║██████╔╝██║██████╔╝░░░██║░░░███████╗██║░╚███║╚█████╔╝███████╗
	//╚═╝░░░░░╚══════╝╚═╝░░╚═╝╚═════╝░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░╚════╝░╚══════╝

	/**
	 * The load method uses the XStream component to read all the objects from the xml
	 * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
	 *
	 * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
	 */
	@Override
    @SuppressWarnings("unchecked")
	public void load() throws Exception {
		//list of classes that you wish to include in the serialisation, separated by a comma
		Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
				ElectricCar.class, Scooter.class, Manufacturer.class};

		//setting up the xstream object with default security and the above classes
		XStream xstream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(classes);

		//doing the actual serialisation to an XML file
		ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
		vehicles = (List<Vehicle>) in.readObject();
		in.close();
	}

	/**
	 * The save method uses the XStream component to write all the objects in the ArrayList
	 * to the xml file stored on the hard disk.
	 *
	 * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
	 */
	@Override
    public void save() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
		out.writeObject(vehicles);
		out.close();
	}

	/**
	 * Gets the fileName
	 *
	 * @return Returns the fileName
	 */
	@Override
    public String fileName() {
		return this.file.toString();
	}

	//███████╗░██████╗░██╗░░░██╗░█████╗░██╗░░░░░░██████╗
	//██╔════╝██╔═══██╗██║░░░██║██╔══██╗██║░░░░░██╔════╝
	//█████╗░░██║██╗██║██║░░░██║███████║██║░░░░░╚█████╗░
	//██╔══╝░░╚██████╔╝██║░░░██║██╔══██║██║░░░░░░╚═══██╗
	//███████╗░╚═██╔═╝░╚██████╔╝██║░░██║███████╗██████╔╝
	//╚══════╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝╚═════╝░

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VehicleAPI that)) return false;
		return Objects.equals(vehicles, that.vehicles) && Objects.equals(file, that.file);
	}

}
