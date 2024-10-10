package controllers;

import models.CarbonFuelCar;
import models.ElectricCar;
import models.Manufacturer;
import models.Scooter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleAPITest {

	private Scooter scooterBelowBoundary, scooterOnBoundary, scooterAboveBoundary, scooterInvalidData;
	private ElectricCar electricCarBelowBoundary, electricCarOnBoundary, electricCarAboveBoundary, electricCarInvalidData;
	private CarbonFuelCar carbonFuelBelowBoundary, carbonFuelOnBoundary, carbonFuelAboveBoundary, carbonFuelInvalidData;

	private Manufacturer ford = new Manufacturer("Ford", 1020);
	private Manufacturer kia = new Manufacturer("Kia", 1200);
	private Manufacturer audi = new Manufacturer("Audi", 1325);
	private Manufacturer tesla = new Manufacturer("Tesla", 3245);
	private Manufacturer mazda = new Manufacturer("Mazda", 2377);
	private Manufacturer hyundai = new Manufacturer("Hyundai", 2765);

	private VehicleAPI populatedVehicles = new VehicleAPI(new File("vehicles.xml"));
	private VehicleAPI emptyVehicles = new VehicleAPI(new File("vehiclesempty.xml"));

	@BeforeEach
	void setUp() {
		// Vehicle Validation Rules:
		//    regNumber (max 8 chars, default ""), model (max 15 chars, default ""),
		//    cost (>= 1000, default 1000), year (2000 - 2023, default 2000)

		// Scooter Validation Rules:
		//    power (250 -> 1000, default 250), weight (5 -> 100, default 5), topRiderWeight (100 -> 120, default 100)
		scooterBelowBoundary = new Scooter("SCOOT12", "Scootn1 Master", 999,
				ford, 1999, 119, 4, 99);

		scooterOnBoundary = new Scooter("SCOOT321", "MasterScooter-3", 1000,
				kia, 2000, 120, 5, 100);

		scooterAboveBoundary = new Scooter("SC 123456", "Speed Scooter X1", 1001,
				tesla, 2001, 121, 6, 101);

		scooterInvalidData = new Scooter(null, null, -1,
				null, -1, -1, -1, -1);


		//Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0),
		electricCarBelowBoundary = new ElectricCar("Elec987", "Electric 12111", 999, mazda, 2020, 120, 5, 100, 100, 50, 100);

		electricCarOnBoundary = new ElectricCar("Elec5678", "Electric 123456", 1000, kia, 2000, 120, 4, 50, 100, 40, 100);

		electricCarAboveBoundary = new ElectricCar("Elec12345", "Electric 1234567", 1001,
				tesla, 2001, 121, 5, 51, 101, 41, 101);

		electricCarInvalidData = new ElectricCar(null, null, -1,
				null, -1, -1, -1, -1, -1, -1, -1);


		// Carbon Fuel Car Validation Rules:
		//    fuelType (diesel or petrol, default petrol), fuelConsumption (5 -> 20, default 5),
		//    carbonEmission (>0, default 0.1), engineSize (800->2500, default 800)
		carbonFuelBelowBoundary = new CarbonFuelCar("Car1234", "CarbonCar 1234", 999,
				ford, 1999, 119, 3, 49, 99, 4,
				0, false, "diesel", 799);  //EDITED TO MATCH THE ORDER OF CONSTRUCTOR

		carbonFuelOnBoundary = new CarbonFuelCar("Car54321", "CarbonCar 12345", 1000,
				kia, 2000, 120, 4, 50, 100, 5,
				1, false, "diesel", 800); //EDITED TO MATCH THE ORDER OF CONSTRUCTOR

		carbonFuelAboveBoundary = new CarbonFuelCar("Car345678", "Carbon Car 12345", 1001,
				tesla, 2001, 121, 5, 51, 101, 6,
				2, false, "petrol", 801); //EDITED TO MATCH THE ORDER OF CONSTRUCTOR

		carbonFuelInvalidData = new CarbonFuelCar(null, null, -1,
				null, -1, -1, -1, -1, -1, -1,
				-1, false, null, -1); //EDITED TO MATCH THE ORDER OF CONSTRUCTOR

		//not included - scooterOnBoundary, scooterInvalidData, electricCarAboveBoundary,
		//               carbonFuelBelowBoundary, carbonFuelInvalidData.
		populatedVehicles.addVehicle(scooterBelowBoundary);     //SCOOT12
		populatedVehicles.addVehicle(electricCarOnBoundary);    //
		populatedVehicles.addVehicle(carbonFuelAboveBoundary);  //Car34567
		populatedVehicles.addVehicle(electricCarBelowBoundary); //MAZ123
		populatedVehicles.addVehicle(scooterAboveBoundary);     //SC 12345
		populatedVehicles.addVehicle(electricCarInvalidData);   //not added as the reg is null
		populatedVehicles.addVehicle(carbonFuelOnBoundary);     //Car54321
	}

	@AfterEach
	void tearDown() {
		scooterBelowBoundary = scooterOnBoundary = scooterAboveBoundary = scooterInvalidData = null;
		carbonFuelBelowBoundary = carbonFuelOnBoundary = carbonFuelAboveBoundary = carbonFuelInvalidData = null;
		electricCarBelowBoundary = electricCarOnBoundary = electricCarAboveBoundary = electricCarInvalidData = null;
		mazda = audi = tesla = ford = hyundai = null;
		populatedVehicles = emptyVehicles = null;
	}

	@Test
	void checkGetFileName() {
		assertEquals(populatedVehicles.fileName(), "vehicles.xml");
		assertNotEquals(populatedVehicles.fileName(), "hello");
		assertEquals(emptyVehicles.fileName(), "vehiclesempty.xml");

	}

	@Nested
	class ValidationMethods {
		@Test
		void validatingTheEqualsMethod() {
			assertTrue(!populatedVehicles.equals(emptyVehicles));
			assertTrue(populatedVehicles.equals(populatedVehicles));

		}

		@Test
		void checkIfRegNumberIsValid() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllScooters();
			assertTrue(populatedVehicles.isValidRegNumber("Elec987"));
			assertTrue(populatedVehicles.isValidRegNumber("Elec5678"));
			assertTrue(populatedVehicles.isValidRegNumber("Car34567"));
			assertTrue(populatedVehicles.isValidRegNumber("Car54321"));
			assertTrue(populatedVehicles.isValidRegNumber("SC 12345"));
			assertTrue(populatedVehicles.isValidRegNumber("SCOOT12"));
			assertTrue(!populatedVehicles.isValidRegNumber("HELLO"));
		}

		@Test
		void checkIfNewRegNumberIsValid() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllScooters();
			assertTrue(!populatedVehicles.isValidNewRegNumber("Elec987"));
			assertTrue(!populatedVehicles.isValidNewRegNumber("Elec5678"));
			assertTrue(!populatedVehicles.isValidNewRegNumber("Car34567"));
			assertTrue(!populatedVehicles.isValidNewRegNumber("Car54321"));
			assertTrue(!populatedVehicles.isValidNewRegNumber("SC 12345"));
			assertTrue(!populatedVehicles.isValidNewRegNumber("SCOOT12"));
			assertTrue(populatedVehicles.isValidNewRegNumber("HELLO"));
		}

	}

	@Nested
	class ListingMethods {

		@Test
		void listAllCarbonsByFuelTypeWhenIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			assertTrue(emptyVehicles.listAllCarbonFuelsByFuelType("petrol").toLowerCase().contains("no vehicles"));
		}

		@Test
		void listAllCarbonsByFuelTypeWhenIsPopulated() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllCarbonFuelsByFuelType("petrol");
			//checks for objects in the string
			assertTrue(!vehicles.contains("Car54321"));
			assertTrue(vehicles.contains("Car34567"));
			String vehicles2 = populatedVehicles.listAllCarbonFuelsByFuelType("diesel");
			//checks for objects in the string
			assertTrue(vehicles2.contains("Car54321"));
			assertTrue(!vehicles2.contains("Car34567"));
		}

		@Test
		void listAllReturnsNoVehiclesStoredWhenArrayListIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			assertTrue(emptyVehicles.listAllVehicles().toLowerCase().contains("no vehicles"));
			System.out.println(populatedVehicles.listAllVehicles());
		}

		@Test
		void listAllReturnsVehiclesStoredWhenArrayListHasVehiclesStored() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllVehicles();
			//checks for objects in the string
			assertTrue(vehicles.contains("SC 12345"));
			assertTrue(vehicles.contains("SCOOT12"));
			assertTrue(vehicles.contains("Elec987"));
			assertTrue(vehicles.contains("Elec5678"));
			assertTrue(vehicles.contains("Car54321"));
			assertTrue(vehicles.contains("Car34567"));
		}

		@Test
		void checkListAllVehicleByChosenManufacturer() {
			String vehicles = populatedVehicles.listAllVehicleByChosenManufacturer(tesla);
			//checks for objects in the string
			assertTrue(vehicles.contains("SC 12345"));
			assertTrue(!vehicles.contains("SCOOT12"));
			assertTrue(!vehicles.contains("Car54321"));
			assertTrue(vehicles.contains("Car34567"));
		}

		@Test
		void checkNumberOfVehicleByChosenManufacturer() {
			assertEquals(2, populatedVehicles.numberOfVehicleByChosenManufacturer(tesla));
			assertEquals(1, populatedVehicles.numberOfVehicleByChosenManufacturer(mazda));
			assertEquals(2, populatedVehicles.numberOfVehicleByChosenManufacturer(kia));
			assertEquals(1, populatedVehicles.numberOfVehicleByChosenManufacturer(ford));
		}

		@Test
		void listBySelectedYearReturnsNoVehiclesWhenNoneExistForEnteredYear() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2003);
			assertTrue(vehicles.contains("No vehicles"));
		}

		@Test
		void listBySelectedYearReturnsVehiclesWhenVehiclesExistForEnteredYear() {
			System.out.println(populatedVehicles.listAllVehicles());
			assertEquals(6, populatedVehicles.numberOfVehicles());

			String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2001);

			//checks for the objects in the string
			assertTrue(vehicles.contains("Car34567"));
			assertTrue(vehicles.contains("SC 12345"));
		}

		@Test
		void listAboveYearReturnsNoVehiclesWhenNoneExistForEnteredYear() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllVehiclesAfterAGivenYear(2023);
			assertTrue(vehicles.contains("No vehicles"));
		}

		@Test
		void listAboveYearReturnsVehiclesWhenVehiclesExistForEnteredYear() {
			System.out.println(populatedVehicles.listAllVehicles());
			assertEquals(6, populatedVehicles.numberOfVehicles());

			String vehicles = populatedVehicles.listAllVehiclesAfterAGivenYear(2001);

			//checks for the objects in the string
			assertTrue(vehicles.contains("Elec987"));
			assertTrue(!vehicles.contains("Car54321"));
		}

		@Test
		void ListAllScooters() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllScooters();
			assertTrue(!vehicles.contains("Elec987"));
			assertTrue(!vehicles.contains("Elec5678"));
			assertTrue(!vehicles.contains("Car34567"));
			assertTrue(!vehicles.contains("Car54321"));
			assertTrue(vehicles.contains("SC 12345"));
			assertTrue(vehicles.contains("SCOOT12"));
		}

		@Test
		void listAllCarbons() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			String vehicles = populatedVehicles.listAllCarbonFuelCars();
			assertTrue(!vehicles.contains("Elec987"));
			assertTrue(!vehicles.contains("Elec5678"));
			assertTrue(vehicles.contains("Car34567"));
			assertTrue(vehicles.contains("Car54321"));
			assertTrue(!vehicles.contains("SC 12345"));
			assertTrue(!vehicles.contains("SCOOT12"));
		}

		@Test
		void listAllElectrics() {
			System.out.println(populatedVehicles.listAllVehicles());
			assertEquals(6, populatedVehicles.numberOfVehicles());

			String vehicles = populatedVehicles.listAllElectricCars();

			//checks for the objects in the string
			assertTrue(vehicles.contains("Elec987"));
			assertTrue(vehicles.contains("Elec5678"));
			assertTrue(!vehicles.contains("Car34567"));
			assertTrue(!vehicles.contains("Car54321"));
			assertTrue(!vehicles.contains("SC 12345"));
			assertTrue(!vehicles.contains("SCOOT12"));
		}
	}

	@Nested
	class ReportingMethods {

		@Test
		void testNumberOfScooters() {
			System.out.println(populatedVehicles.listAllScooters());
			assertEquals(2, populatedVehicles.numberOfScooters());
			assertEquals(0, emptyVehicles.numberOfScooters());
		}

		@Test
		void testNumberOfElectrics() {
			System.out.println(populatedVehicles.listAllElectricCars());
			assertEquals(2, populatedVehicles.numberOfElectricCars());
			assertEquals(0, emptyVehicles.numberOfElectricCars());
		}

		@Test
		void testNumberOfCarbons() {
			System.out.println(populatedVehicles.listAllCarbonFuelCars());
			assertEquals(2, populatedVehicles.numberOfCarbonCars());
            assertEquals(0, emptyVehicles.numberOfCarbonCars());
		}

	}

	@Nested
	class SearchingMethods {  //none

	}

	@Nested
	class SortingMethods {

		@Test
		void sortByCostDescendingReOrdersList() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			//checks the order of the objects in the list
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(0));
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(2));
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3));
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(4));
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));

            /*populatedVehicles.sortByCostDescending();
            assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(0));
            assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(1));
            assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(2));
            assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(3));
            assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(4));
            assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));*/ //ORIGINAL TEST
			populatedVehicles.sortByCostDescending();
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(0));
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(1));
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(2));
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3)); //SWAPPED (cost value is same as index 4)
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(4));  //SWAPPED (cost value is same as index 3)
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));
		}

		@Test
		void sortByCostDescendingDoesntCrashWhenListIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			emptyVehicles.sortByCostDescending();
		}

		@Test
		void sortByAgeAscendingReOrdersList() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			//checks the order of the objects in the list
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(0));
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(2));
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3));
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(4));
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));

			populatedVehicles.sortByAgeAscending();
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(0)); //2020
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(1)); //2001
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(2)); //2001
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(3)); //2000
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(4)); //2000
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5)); //2000
		}

		@Test
		void sortByAgeAscendingDoesntCrashWhenListIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			emptyVehicles.sortByAgeAscending();
		}

		@Test
		void sortByCarbonFootprintAscendingReOrdersList() {
			assertEquals(6, populatedVehicles.numberOfVehicles());
			//checks the order of the objects in the list
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(0));
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(2));
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3));
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(4));
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));

			populatedVehicles.sortByCarbonFootprintAscending();
			assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(0));
			assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
			assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(2));
			assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(3));
			assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(4));
			assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(5));
		}

		@Test
		void sortByCarbonFootprintAscendingDoesntCrashWhenListIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			emptyVehicles.sortByCarbonFootprintAscending();
		}

		@Test
		void checkTopFiveCarbonVehiclesWhenPopulated() { //Sorting is already tested, and this method was done in VehicleAPI with sorting.
			assertEquals(6, populatedVehicles.numberOfVehicles());
			//checks the order of the objects in the list
			String vehicles = populatedVehicles.topFiveCarbonVehicles();
			assertTrue(vehicles.contains("Elec987"));
			assertTrue(vehicles.contains("Elec5678"));
			assertTrue(vehicles.contains("Car54321"));
			assertTrue(vehicles.contains("SC 12345"));
			assertTrue(vehicles.contains("SCOOT12"));
			assertTrue(!vehicles.contains("Car34567"));
		}

		@Test
		void checkTopFiveCarbonVehiclesWhenIsEmpty() {
			assertEquals(0, emptyVehicles.numberOfVehicles());
			String vehicles = emptyVehicles.topFiveCarbonVehicles();
			assertEquals(vehicles, "No vehicles");
		}
	}

	@Nested
	class CRUDMethods {

		@Test
		void checkAddVehicle() {
			populatedVehicles.addVehicle(electricCarAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec987"), electricCarBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec5678"), electricCarOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec1234"), electricCarAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car34567"), carbonFuelAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car54321"), carbonFuelOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SC 12345"), scooterAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SCOOT12"), scooterBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("HELLO"), null);
		}

		@Test
		void checkDeleteVehicleByRegNumber() {
			populatedVehicles.addVehicle(electricCarAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec1234"), electricCarAboveBoundary);
			populatedVehicles.deleteVehicleByRegNumber("Elec1234");
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec987"), electricCarBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec5678"), electricCarOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec1234"), null);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car34567"), carbonFuelAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car54321"), carbonFuelOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SC 12345"), scooterAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SCOOT12"), scooterBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("HELLO"), null);
		}

		@Test
		void checkDeleteVehicleByIndex() {
			populatedVehicles.addVehicle(electricCarAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec1234"), electricCarAboveBoundary);
			populatedVehicles.deleteVehicleByIndex(6);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec987"), electricCarBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec5678"), electricCarOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec1234"), null);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car34567"), carbonFuelAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car54321"), carbonFuelOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SC 12345"), scooterAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SCOOT12"), scooterBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("HELLO"), null);
		}

		@Test
		void checkUpdateScooter() {
			Scooter newScooter = new Scooter("SCOOT12", "BrandNewModel", 1000,
					kia, 2000, 120, 5, 100);
			populatedVehicles.upDateScooter("SCOOT12", newScooter);
			assertEquals(newScooter, populatedVehicles.getVehicleByRegNumber("SCOOT12"));
		}

		@Test
		void checkUpdateCarbonFuelCar() {
			CarbonFuelCar newCarbon = new CarbonFuelCar("Car34567", "Carbon Car 12345", 1001,
					tesla, 2001, 121, 5, 51, 101, 6,
					2, true, "diesel", 801);
			populatedVehicles.upDateCarbonFuelCar("Car34567", newCarbon);
			assertEquals(newCarbon, populatedVehicles.getVehicleByRegNumber("Car34567"));
		}

		@Test
		void checkUpdateElectricCar() {
			ElectricCar newElectric = new ElectricCar("Elec5678", "Electric 123456", 1000, kia, 2023, 120, 4, 50, 100, 40, 100);
			populatedVehicles.upDateElectricCar("Elec5678", newElectric);
			assertEquals(newElectric, populatedVehicles.getVehicleByRegNumber("Elec5678"));
		}

		@Test
		void checkGetVehicleByRegNumber() {
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec987"), electricCarBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Elec5678"), electricCarOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car34567"), carbonFuelAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("Car54321"), carbonFuelOnBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SC 12345"), scooterAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("SCOOT12"), scooterBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByRegNumber("HELLO"), null);
		}

		@Test
		void checkGetVehicleByIndex() {
			assertEquals(populatedVehicles.getVehicleByIndex(3), electricCarBelowBoundary);
			assertEquals(populatedVehicles.getVehicleByIndex(1), electricCarOnBoundary);
			assertEquals(populatedVehicles.getVehicleByIndex(2), carbonFuelAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByIndex(5), carbonFuelOnBoundary);
			assertEquals(populatedVehicles.getVehicleByIndex(4), scooterAboveBoundary);
			assertEquals(populatedVehicles.getVehicleByIndex(0), scooterBelowBoundary);
		}

		@Test
		void checkGetIndexByRegNumber() {
			assertEquals(populatedVehicles.getIndexByRegNumber("Elec987"), 3);
			assertEquals(populatedVehicles.getIndexByRegNumber("Elec5678"), 1);
			assertEquals(populatedVehicles.getIndexByRegNumber("Car34567"), 2);
			assertEquals(populatedVehicles.getIndexByRegNumber("Car54321"), 5);
			assertEquals(populatedVehicles.getIndexByRegNumber("SC 12345"), 4);
			assertEquals(populatedVehicles.getIndexByRegNumber("SCOOT12"), 0);
		}
	}

}

