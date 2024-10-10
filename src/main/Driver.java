/**
 * This class handles I/O.
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package main;

import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.*;
import utils.ScannerInput;
import utils.lang.Colors;
import utils.lang.LangData;
import utils.lang.Settings;

import java.io.File;

public class Driver {

	private VehicleAPI vehicleAPI;
	private ManufacturerAPI manufacturerAPI;

	private Settings settings = new Settings();

	public static void main(String[] args) throws Exception {
		new main.Driver().start();
	}

	public void start() {


		setup();
		vehicleAPI = new VehicleAPI(new File("vehicles.xml"));
		manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));

		loadAllData();  //load all data once the serializers are set up
		runMainMenu();
	}

	//----------------------------------------------------------------------------
	// Setup of the program (Language detection and loading)
	//----------------------------------------------------------------------------

	private void setup() {


		System.out.println(Colors.STARTUP + "Starting program...\nLoading settings into memory..." + Colors.RESET);
		settings.loadLanguage("settings");
		settings.keepSettingsFromFile();
		System.out.println(Colors.SUCCESS + "Loaded settings! ✓" + Colors.RESET);
		System.out.println(Colors.STARTUP + "The following languages are present: " + Colors.DEFAULT_COLOR + settings.getLoadedLanguages());
		System.out.println(Colors.STARTUP + "Detected the following language from settings.xml: " + Colors.DEFAULT_COLOR + settings.getSelected_language() + Colors.RESET);
		System.out.println(Colors.SORT + "[ ! ] Please read the README.md file before using the program." + Colors.RESET);
		String resetValue = ScannerInput.readNextLine(Colors.STARTUP + "Press the ENTER key to continue or type in \"RESET\" to load in the default language which is " + Colors.DEFAULT_COLOR + settings.getDefault_language() + "\n>>");
		if (resetValue.equalsIgnoreCase("RESET")) {
			settings.setSelected_language(settings.getDefault_language());
			settings.saveSettingsToFile();
		}
		settings.loadLanguage(settings.getSelected_language());
		System.out.println(translate("language_loaded"));
	}

	//Additional menu for additional function > language selection
	private int languageMenuShow() {
		int option = ScannerInput.readNextInt(Colors.DEFAULT_COLOR + "|---------------------------------" +
				"\n|      " + translate("lang_menu_title") + Colors.DEFAULT_COLOR +
				"\n|" + translate("lang_get_selected") + Colors.SORT + settings.getSelected_language() + Colors.DEFAULT_COLOR +
				"\n|" + translate("lang_get_default") + Colors.SORT + settings.getDefault_language() + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("change_language") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("list_languages") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "3) " + translate("add_language") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "4) " + translate("change_default_language") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "5) " + translate("add_translation") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("back_to_main_menu") + Colors.STARTUP + "\n>>");
		return option;
	}

	private void languageMenu() {
		int option = languageMenuShow();

		while (option != 0) {

			switch (option) {
				case 1 -> changeLanguage();
				case 2 -> listLanguages();
				case 3 -> addLanguage();
				case 4 -> changeDefaultLanguage();
				case 5 -> addTranslationToSelectedLanguage();
				default -> System.out.println(translate("invalid_option") + option);
			}

			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));

			option = languageMenuShow();
		}

		System.out.println(translate("returning_to_main_menu"));
	}

	private int mainMenu() {
		System.out.println(Colors.DEFAULT_COLOR + "|---------------------------------------------------------" +
				"\n|      " + translate("menu_title") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("menu_manufacturer_crud") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("menu_vehicle_store_crud") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "3) " + translate("menu_reports") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------------------------------" + Colors.DEFAULT_COLOR +
				//"\n|  "+Colors.SORT+"4) " + translate("menu_search_manufacturers") + Colors.DEFAULT_COLOR +
				//"\n|  "+Colors.SORT+"5) " + translate("menu_search_vehicles") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "6) " + translate("menu_sort_vehicles") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "10) " + translate("menu_save") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "11) " + translate("menu_load") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "19) " + Colors.RESET + "Disable/Enable white default color in console" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "20) " + translate("menu_language_settings") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("menu_exit"));
		return ScannerInput.readNextInt(Colors.STARTUP + ">> " + Colors.RESET);
	}

	private void runMainMenu() {
		int option = mainMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> runManufacturerMenu();
				case 2 ->
						runVehicleStoreMenu();        //TODO run the Vehicle Store Menu and the associated methods (your design here)
				case 3 ->
						runReportsMenu();        //TODO run the Reports Menu and the associated methods (your design here)case 4 -> //TODO run the search Manufacturers menu and associated methods (your design here)
				//case 5 -> runSearchVehiclesMenu();        //TODO run the search Vehicles menu and associated methods (your design here)
				case 6 -> runSortVehiclesMenu();        //TODO sorting menu and associated (your design here)
				case 10 -> saveAllData();
				case 11 -> loadAllData();
				case 19 -> settings.switchColor();
				case 20 -> languageMenu();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = mainMenu();
		}
		exitApp();
	}

	private void exitApp() {
		saveAllData();
		System.out.println(translate("exiting"));
		System.exit(0);
	}


	//░██████╗░█████╗░██████╗░████████╗██╗███╗░░██╗░██████╗░
	//██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██║████╗░██║██╔════╝░
	//╚█████╗░██║░░██║██████╔╝░░░██║░░░██║██╔██╗██║██║░░██╗░
	//░╚═══██╗██║░░██║██╔══██╗░░░██║░░░██║██║╚████║██║░░╚██╗
	//██████╔╝╚█████╔╝██║░░██║░░░██║░░░██║██║░╚███║╚██████╔╝
	//╚═════╝░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚═════╝░
	private int sortVehiclesMenu() {
		System.out.println(Colors.DEFAULT_COLOR + "|---------------------------------" +
				"\n|      " + translate("menu_sorting_title") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("menu_sorting_by_cost") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("menu_sorting_by_age") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "3) " + translate("menu_sorting_by_carbon_footprint") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("menu_exit"));
		return ScannerInput.readNextInt(Colors.STARTUP + ">>" + Colors.RESET);
	}

	private void runSortVehiclesMenu() {
		int option = sortVehiclesMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> sortVehiclesByCostDescending();
				case 2 -> sortVehiclesByAgeAscending();
				case 3 -> sortVehiclesByCarbonFootprintAscending();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = sortVehiclesMenu();
		}
	}

	private void sortVehiclesByCostDescending() {
		vehicleAPI.sortByCostDescending();
		System.out.println(vehicleAPI.listAllVehicles());

	}

	private void sortVehiclesByAgeAscending() {
		vehicleAPI.sortByAgeAscending();
		System.out.println(vehicleAPI.listAllVehicles());
	}

	private void sortVehiclesByCarbonFootprintAscending() {
		vehicleAPI.sortByCarbonFootprintAscending();
		System.out.println(vehicleAPI.listAllVehicles());
	}

	//██╗░░░██╗███████╗██╗░░██╗██╗░█████╗░██╗░░░░░███████╗     ░██████╗████████╗░█████╗░██████╗░███████╗
	//██║░░░██║██╔════╝██║░░██║██║██╔══██╗██║░░░░░██╔════╝     ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝
	//╚██╗░██╔╝█████╗░░███████║██║██║░░╚═╝██║░░░░░█████╗░░     ╚█████╗░░░░██║░░░██║░░██║██████╔╝█████╗░░
	//░╚████╔╝░██╔══╝░░██╔══██║██║██║░░██╗██║░░░░░██╔══╝░░     ░╚═══██╗░░░██║░░░██║░░██║██╔══██╗██╔══╝░░
	//░░╚██╔╝░░███████╗██║░░██║██║╚█████╔╝███████╗███████╗     ██████╔╝░░░██║░░░╚█████╔╝██║░░██║███████╗
	//░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░╚════╝░╚══════╝╚══════╝     ╚═════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝╚══════╝
	private int vehicleStoreMenu() {
		System.out.println(Colors.DEFAULT_COLOR + "|---------------------------------" +
				"\n|      " + translate("menu_vehicle_store_title") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("menu_add_vehicle") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("menu_delete_vehicle") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "3) " + translate("menu_list_vehicles") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "4) " + translate("menu_update_vehicle") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("menu_exit"));
		return ScannerInput.readNextInt(Colors.STARTUP + ">>" + Colors.RESET);
	}

	private void runVehicleStoreMenu() {
		int option = vehicleStoreMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> addVehicle();
				case 2 -> deleteVehicle();
				case 3 -> listAllVehicles();
				case 4 -> updateVehicle();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = vehicleStoreMenu();
		}
	}


	private void deleteVehicle() {

		String regNumber = ScannerInput.readNextLine("Please enter the registration number of the vehicle to delete it: ");
		Vehicle vehicleToRemove = vehicleAPI.deleteVehicleByRegNumber(regNumber);

		if (vehicleToRemove != null) {
			System.out.println("Delete successful for" + vehicleToRemove.toString());
		} else {
			System.out.println("Delete not successful");
		}

	}

	private void updateVehicle() {
		String regNumber = ScannerInput.readNextLine("Please enter the registration number of the vehicle to update it: ");
		if (vehicleAPI.isValidRegNumber(regNumber)) {
			boolean isAdded;
			Manufacturer manufacturer = getManufacturerByName();
			String model = ScannerInput.readNextLine("\tmodel : ");
			float cost = ScannerInput.readNextFloat("\tcost : ");
			int year = ScannerInput.readNextInt("\tYear of registration :");

			if (vehicleAPI.getVehicleByRegNumber(regNumber) instanceof Scooter) {

				int power = ScannerInput.readNextInt("\tpower : ");
				float weight = ScannerInput.readNextFloat("\tweight : ");
				int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight : ");

				Scooter newScooter = new Scooter(regNumber, model, cost, manufacturer, year, power, weight, topRiderWeight);
				isAdded = vehicleAPI.upDateScooter(regNumber, newScooter);
				if (isAdded) {
					System.out.println("Updated");
				} else {
					System.out.println("Something went wrong.");
				}
			} else if (vehicleAPI.getVehicleByRegNumber(regNumber) instanceof Car) {
				int power = ScannerInput.readNextInt("\tpower : ");
				int secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
				int topSpeed = ScannerInput.readNextInt("\ttop speed : ");
				float torque = ScannerInput.readNextFloat("\tpower: ");
				if (vehicleAPI.getVehicleByRegNumber(regNumber) instanceof CarbonFuelCar) {

					float fuelConsumption = ScannerInput.readNextFloat("fuel consumption: ");
					float carbonEmission = ScannerInput.readNextFloat("carbon emission: ");
					boolean isAutomatic = utils.Utilities.YNtoBoolean(ScannerInput.readNextChar("is automatic(y/n): "));
					String fuelType = ScannerInput.readNextLine("fuel type: ");
					int engineSize = ScannerInput.readNextInt("engine size: ");
					CarbonFuelCar newCarbonFuelCar = new CarbonFuelCar(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed, fuelConsumption, carbonEmission, isAutomatic, fuelType, engineSize);
					isAdded = vehicleAPI.upDateCarbonFuelCar(regNumber, newCarbonFuelCar);
					if (isAdded) {
						System.out.println("Updated");
					} else {
						System.out.println("Something went wrong.");
					}
				} else if (vehicleAPI.getVehicleByRegNumber(regNumber) instanceof ElectricCar) {

					int range = ScannerInput.readNextInt("range: ");
					float engineKWatts = ScannerInput.readNextFloat("engine kW: ");
					ElectricCar newElectricCar = new ElectricCar(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed, range, engineKWatts);
					isAdded = vehicleAPI.upDateElectricCar(regNumber, newElectricCar);
					if (isAdded) {
						System.out.println("Updatet");
					} else {
						System.out.println("Something went wrong.");
					}
				}

			}

		} else {
			System.out.println("Vehicle is not found.");
		}
	}

	// public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
	private void addVehicle() {
		int vehicleType = ScannerInput.readNextInt(Colors.DEFAULT_COLOR + """
				Which type of vehicle do you wish to add? 
				1) Carbon Fuel Car
				2) Electric Car
				3) Scooter 
				>>""");

		Manufacturer manufacturer = getManufacturerByName();
		if (manufacturer != null) {
			String regNumber = ScannerInput.readNextLine("Please enter Reg number of new Vehicle: ");


			if (vehicleAPI.isValidNewRegNumber(regNumber)) {
				String model = ScannerInput.readNextLine("\tmodel : ");
				float cost = ScannerInput.readNextFloat("\tcost : ");
				int year = ScannerInput.readNextInt("\tYear of registration : ");
				switch (vehicleType) {
					case 1, 2 -> {
						int power = ScannerInput.readNextInt("\tpower : ");
						int secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
						int topSpeed = ScannerInput.readNextInt("\ttop speed : ");
						float torque = ScannerInput.readNextFloat("\tpower: ");
						switch (vehicleType) {
							case 1 -> {
								//TODO Carbon Fuel Car -
								float fuelConsumption = ScannerInput.readNextFloat("fuel consumption : ");
								float carbonEmission = ScannerInput.readNextFloat("carbon emission : ");
								boolean isAutomatic = utils.Utilities.YNtoBoolean(ScannerInput.readNextChar("is automatic(y/n) : "));
								String fuelType = ScannerInput.readNextLine("fuel type : ");
								int engineSize = ScannerInput.readNextInt("engine size : ");
								CarbonFuelCar newCarbonFuelCar = new CarbonFuelCar(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed, fuelConsumption, carbonEmission, isAutomatic, fuelType, engineSize);
								boolean isAdded = vehicleAPI.addVehicle(newCarbonFuelCar);
								if (isAdded) {
									System.out.println("Added");
								} else {
									System.out.println("Something went wrong.");
								}
							}
							case 2 -> {
								//TODO Electric Car   -
								int range = ScannerInput.readNextInt("range: ");
								float engineKWatts = ScannerInput.readNextFloat("engine kW: ");
								ElectricCar newElectricCar = new ElectricCar(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed, range, engineKWatts);
								boolean isAdded = vehicleAPI.addVehicle(newElectricCar);
								if (isAdded) {
									System.out.println("Added");
								} else {
									System.out.println("Something went wrong.");
								}
							}
						}
					}
					case 3 -> {
						//Scooter

						int power = ScannerInput.readNextInt("\tpower : ");
						float weight = ScannerInput.readNextFloat("\tweight : ");
						int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight");

						Scooter newScooter = new Scooter(regNumber, model, cost, manufacturer, year, power, weight, topRiderWeight);
						boolean isAdded = vehicleAPI.addVehicle(newScooter);
						if (isAdded) {
							System.out.println("Added");
						} else {
							System.out.println("Something went wrong.");
						}
						// todo - write addVehicle(.)                  vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight,topRiderWeight));

					}
				}
			} else {
				System.out.println("Vehicle reg number  already exists.");
			}
		} else {
			System.out.println("Manufacturer name is NOT valid");
		}
	}


	private void listAllVehicles() {
		System.out.println(vehicleAPI.listAllVehicles());
	}

	//██████╗░███████╗██████╗░░█████╗░██████╗░████████╗░██████╗
	//██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝
	//██████╔╝█████╗░░██████╔╝██║░░██║██████╔╝░░░██║░░░╚█████╗░
	//██╔══██╗██╔══╝░░██╔═══╝░██║░░██║██╔══██╗░░░██║░░░░╚═══██╗
	//██║░░██║███████╗██║░░░░░╚█████╔╝██║░░██║░░░██║░░░██████╔╝
	//╚═╝░░╚═╝╚══════╝╚═╝░░░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░
	private int reportsMenu() {
		System.out.println(Colors.DEFAULT_COLOR + "|---------------------------------" +
				"\n|      " + translate("menu_reports_title") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("menu_reports_manufacturers") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("menu_reports_vehicles") + Colors.DEFAULT_COLOR +
				"\n|---------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("menu_exit"));
		return ScannerInput.readNextInt(Colors.STARTUP + ">>" + Colors.RESET);
	}

	private void runReportsMenu() {
		int option = reportsMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> runManufacturerReports();
				case 2 -> runVehicleReportsMenu();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = reportsMenu();
		}
	}

	private int vehicleReportsMenu() {
		System.out.println(Colors.DEFAULT_COLOR + "|------------------------------------------------------------------" +
				"\n|      " + translate("menu_vehicle_reports_title") + Colors.DEFAULT_COLOR +
				"\n|------------------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "1) " + translate("menu_vehicle_reports_list_all") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "2) " + translate("menu_vehicle_reports_list_electrics") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "3) " + translate("menu_vehicle_reports_list_carbons") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "4) " + translate("menu_vehicle_reports_list_scooters") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "5) " + translate("menu_vehicle_reports_list_in_given_year") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "6) " + translate("menu_vehicle_reports_list_after_given_year") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "7) " + translate("menu_vehicle_reports_list_carbons_by_fueltype") + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "8) " + translate("menu_vehicle_reports_top_carbon_vehicles") + Colors.DEFAULT_COLOR +
				"\n|------------------------------------------------------------------" + Colors.DEFAULT_COLOR +
				"\n|  " + Colors.SORT + "0) " + translate("menu_exit"));

		return ScannerInput.readNextInt(Colors.STARTUP + ">>" + Colors.RESET);
	}

	private void runVehicleReportsMenu() {
		int option = vehicleReportsMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> listAllVehicles();
				case 2 -> listAllElectrics();
				case 3 -> listAllCarbons();
				case 4 -> listAllScooters();
				case 5 -> listAllInGivenYear();
				case 6 -> listAllAfterGivenYear();
				case 7 -> listAllCarbonsByFuelType();
				case 8 -> reportTopCarbonVehicles();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = vehicleReportsMenu();
		}
	}

	private void listAllInGivenYear() {
		int year = ScannerInput.readNextInt("Provide the year: ");
		System.out.println(vehicleAPI.listAllVehiclesEqualToAGivenYear(year));
	}

	private void listAllAfterGivenYear() {
		int year = ScannerInput.readNextInt("Provide the year: ");
		System.out.println(vehicleAPI.listAllVehiclesAfterAGivenYear(year));
	}

	private void listAllCarbonsByFuelType() {
		String fuel = ScannerInput.readNextLine("Provide the fuel type: ");
		System.out.println(vehicleAPI.listAllCarbonFuelsByFuelType(fuel));
	}

	private void reportTopCarbonVehicles() {
		System.out.println(vehicleAPI.topFiveCarbonVehicles());
	}

	private void listAllElectrics() {
		System.out.println(vehicleAPI.listAllElectricCars());
	}

	private void listAllCarbons() {
		System.out.println(vehicleAPI.listAllCarbonFuelCars());
	}

	private void listAllScooters() {
		System.out.println(vehicleAPI.listAllCarbonFuelCars());
	}

	//███╗░░░███╗░█████╗░███╗░░██╗██╗░░░██╗███████╗░█████╗░░█████╗░████████╗██╗░░░██╗██████╗░███████╗██████╗░
	//████╗░████║██╔══██╗████╗░██║██║░░░██║██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██║░░░██║██╔══██╗██╔════╝██╔══██╗
	//██╔████╔██║███████║██╔██╗██║██║░░░██║█████╗░░███████║██║░░╚═╝░░░██║░░░██║░░░██║██████╔╝█████╗░░██████╔╝
	//██║╚██╔╝██║██╔══██║██║╚████║██║░░░██║██╔══╝░░██╔══██║██║░░██╗░░░██║░░░██║░░░██║██╔══██╗██╔══╝░░██╔══██╗
	//██║░╚═╝░██║██║░░██║██║░╚███║╚██████╔╝██║░░░░░██║░░██║╚█████╔╝░░░██║░░░╚██████╔╝██║░░██║███████╗██║░░██║
	//╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚═════╝░╚═╝░░░░░╚═╝░░╚═╝░╚════╝░░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝
	//----------------------
	//  Developer Menu Items
	//----------------------
	private int manufacturerMenu() {
		System.out.println(Colors.DEFAULT_COLOR + """
				 --------Manufacturer Menu---------
				|  1) Add a manufacturer           |
				|  2) Delete a manufacturer        |
				|  3) Update manufacturer details  |
				|  4) List all manufacturers       |
				|  5) Find a manufacturer          |
				|  0) Return to main menu          |
				 ----------------------------------""" + Colors.RESET);
		return ScannerInput.readNextInt(Colors.STARTUP + ">>" + Colors.RESET);
	}

	private void runManufacturerMenu() {
		int option = manufacturerMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> addManufacturer();
				case 2 -> deleteManufacturer();
				case 3 -> updateManufacturer();
				case 4 -> System.out.println(manufacturerAPI.listManufacturers());
				case 5 -> findManufacturer();
				case 6 -> listVehiclesByManufacturerName();
				default -> System.out.println(translate("invalid_option") + option);
			}
			ScannerInput.readNextLine("\n" + translate("enter_to_continue"));
			option = manufacturerMenu();
		}
	}

	private void addManufacturer() {
		String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
		int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

		if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))) {
			System.out.println("Add successful");
		} else {
			System.out.println("Add not successful");
		}
	}

	private void deleteManufacturer() {
		String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
		if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null) {
			System.out.println("Delete successful");
		} else {
			System.out.println("Delete not successful");
		}
	}

	private void updateManufacturer() {
		Manufacturer manufacturer = getManufacturerByName();
		if (manufacturer != null) {
			int numEmployees = ScannerInput.readNextInt("Please enter number of Employees: ");
			if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
				System.out.println("Number of Employees Updated");
			else
				System.out.println("Number of Employees NOT Updated");
		} else
			System.out.println("Manufacturer name is NOT valid");
	}

	private void findManufacturer() {
		Manufacturer manufacturer = getManufacturerByName();
		if (manufacturer == null) {
			System.out.println("No such manufacturer exists");
		} else {
			System.out.println(manufacturer);
		}
	}

	private void listVehiclesByManufacturerName() {
		String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

		System.out.println(manufacturerAPI.listAllVehiclesByManufacturerName(manufacturer));
	}


	private int manufacturerReportsMenu() {
		System.out.println(Colors.DEFAULT_COLOR + """ 
				 ---------- Manufacturers Reports Menu  -------------
				| 1) List Manufacturers                              | 
				| 2) List Vehicles from a given manufacturer         |
				| 3) List Manufacturers by a given name              |
				| 0) Return to main menu                             | 
				  ---------------------------------------------------  """ + Colors.RESET);
		return ScannerInput.readNextInt("==>>");
	}

	public void runManufacturerReports() {
		int option = manufacturerReportsMenu();
		while (option != 0) {
			switch (option) {
				case 1 -> System.out.println(manufacturerAPI.listManufacturers());
				case 2 -> listAllVehiclesFromAGivenManufacturer();
				case 3 -> findManufacturer();
				default -> System.out.println("Invalid option entered" + option);
			}
			ScannerInput.readNextLine("\n Press the enter key to continue");
			option = manufacturerReportsMenu();
		}
	}


	public void listAllVehiclesFromAGivenManufacturer() {
		String manu = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
		Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
		if (!(m == null))
			System.out.println(vehicleAPI.listAllVehicleByChosenManufacturer(m));
		else
			System.out.println("No manufacturer with tha name exists");
	}


	//--------------------------------------------------
	//  Persistence Menu Items
	//--------------------------------------------------

	private void saveAllData() {

		try {
			vehicleAPI.save();
		} catch (Exception e) {
			System.err.println("Error writing to file (vehicleAPI): " + e);
		}
		try {
			manufacturerAPI.save();
		} catch (Exception e) {
			System.err.println("Error writing to file (manufacturerAPI): " + e);
		}
		// TODO try-catch to save the developers to XML file
		// TODO try-catch to save the apps in the store to XML file
	}

	private void loadAllData() {
		// TODO try-catch to load the developers from XML file
		// TODO try-catch to load the apps in the store from XML file
		try {
			vehicleAPI.load();
		} catch (Exception e) {
			System.err.println("Error reading from file (vehicleAPI): " + e);
		}
		try {
			manufacturerAPI.load();
		} catch (Exception e) {
			System.err.println("Error reading from file (manufacturerAPI): " + e);
		}
	}

	private String getValidRegNumber() {
		String vehicleRegNumber = ScannerInput.readNextLine("\tVehicle Reg Number (must be unique): ");
		if (vehicleAPI.isValidNewRegNumber(vehicleRegNumber)) {
			return vehicleRegNumber;
		} else {
			System.err.println("\tApp name already exists / is not valid.");
			return "";
		}
	}

	private Manufacturer getManufacturerByName() {
		String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
		if (manufacturerAPI.isValidManufacturer(manufacturerName)) {
			return manufacturerAPI.getManufacturerByName(manufacturerName);
		} else {
			return null;
		}
	}


	//---------------------------------
	//  Private methods for languages
	// --------------------------------

	private String translate(String id) {
		return settings.getTranslation(id);
	}

	private void changeLanguage() {
		System.out.println(Colors.DEFAULT_COLOR + settings.getLoadedLanguages());
		String lan = ScannerInput.readNextLine(translate("change_language_message"));
		if (settings.validLanguage(lan)) {
			settings.loadLanguage("settings");
			settings.keepSettingsFromFile();
			settings.setSelected_language(lan);
			settings.saveSettingsToFile();
		} else {
			System.out.println(translate("language_not_found"));
		}
		settings.loadLanguage(settings.getSelected_language());
	}

	private void listLanguages() {
		System.out.println(Colors.STARTUP + settings.getLoadedLanguages() + Colors.DEFAULT_COLOR);
	}

	private void addLanguage() {
		String newLanguage = ScannerInput.readNextLine(translate("new_language"));
		if ((newLanguage != null) || (!(settings.validLanguage(newLanguage)))) {
			settings.loadLanguage("settings");
			settings.keepSettingsFromFile();
			settings.addLanguage(newLanguage);
			settings.saveLanguage(newLanguage);
			settings.saveSettingsToFile();
		}
	}

	private void changeDefaultLanguage() {
		System.out.println(Colors.DEFAULT_COLOR + settings.getLoadedLanguages());
		System.out.println(Colors.STARTUP + translate("selected_default_language") + settings.getDefault_language());
		String lan = ScannerInput.readNextLine(translate("change_selected_default_language"));
		if (settings.validLanguage(lan)) {
			settings.loadLanguage("settings");
			settings.keepSettingsFromFile();
			settings.setDefault_language(lan);
			settings.saveSettingsToFile();
		} else {
			ScannerInput.readNextLine(translate("language_not_found"));
		}
	}

	private void addTranslationToSelectedLanguage() {
		String str = ScannerInput.readNextLine(translate("translation_ID"));
		int ID = ScannerInput.readNextInt(translate("translation_coloring_ID"));
		String title = str;
		String details = title;
		String translation = ScannerInput.readNextLine(translate("translation"));
		LangData langData = new LangData(title, details, translation, ID);
		settings.addTranslation(str, langData);
		settings.saveLanguage(settings.getSelected_language());
	}
}

