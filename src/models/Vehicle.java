/**
 * This class handles the Vehicle model (abstract).
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package models;

import utils.lang.Colors;

import java.util.Objects;

public abstract class Vehicle {

    private String regNumber = "No reg ";
    private int year = 2000;
    private float cost = 1000;
    private Manufacturer manufacturer;
    private String model = "No model";
    private int currentYear = 2023;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return year == vehicle.year && Float.compare(vehicle.cost, cost) == 0 && Objects.equals(regNumber, vehicle.regNumber) && Objects.equals(manufacturer, vehicle.manufacturer) && Objects.equals(model, vehicle.model);
    }

    //████████╗░█████╗░░██████╗████████╗██████╗░██╗███╗░░██╗░██████╗░
    //╚══██╔══╝██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║████╗░██║██╔════╝░
    //░░░██║░░░██║░░██║╚█████╗░░░░██║░░░██████╔╝██║██╔██╗██║██║░░██╗░
    //░░░██║░░░██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║██║╚████║██║░░╚██╗
    //░░░██║░░░╚█████╔╝██████╔╝░░░██║░░░██║░░██║██║██║░╚███║╚██████╔╝
    //░░░╚═╝░░░░╚════╝░╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░
    @Override
    public String toString() {
        return  Colors.SUCCESS + "--- " + Colors.SORT +"Vehicle[" +
                "regNumber='" + regNumber  +
                ", year=" + year +
                ", cost=" + cost +
                ", carbon footprint=" + getCarbonFootPrint() +
                ", manufacturer=" + manufacturer +
                ", model='" + model +  ", " + getAgeState(year) +
                ']';
    }


   //░█████╗░░█████╗░███╗░░██╗░██████╗████████╗██████╗░██╗░░░██╗░█████╗░████████╗░█████╗░██████╗░
   //██╔══██╗██╔══██╗████╗░██║██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗
   //██║░░╚═╝██║░░██║██╔██╗██║╚█████╗░░░░██║░░░██████╔╝██║░░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝
   //██║░░██╗██║░░██║██║╚████║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗
   //╚█████╔╝╚█████╔╝██║░╚███║██████╔╝░░░██║░░░██║░░██║╚██████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║
   //░╚════╝░░╚════╝░╚═╝░░╚══╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝
    public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
        if (regNumber==null){
            this.regNumber="No reg";
        } else if (regNumber.length()<=8){
            this.regNumber=regNumber;
        } else {
            this.regNumber=regNumber.substring(0,8);
        }
        setYear(year);
        setCost(cost);
        setManufacturer(manufacturer);
        if (regNumber==null){
            this.regNumber="No reg";
        } else if (model.length()<=15){
            this.model=model;
        } else {
            this.model=model.substring(0,15);
        }
    }

    //░██████╗░███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝░██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //██║░░██╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //██║░░╚██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //╚██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //░╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░
    public String getRegNumber() {
        return regNumber;
    }

    public int getYear() {
        return year;
    }

    public float getCost() {
        return cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }
    public abstract double getCarbonFootPrint();{}
    public int getAge() {
        return currentYear-year;
    }
    public String getAgeState(int year) {
        if (year==currentYear){
            return "Brand New!";
        } else if (year==currentYear-1){
            return "1 year old";
        } else {
            return getAge() + " years old";
        }
    }

    //░██████╗███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //╚█████╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //░╚═══██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░



    public void setRegNumber(String regNumber) {
        if (regNumber==null){
            this.regNumber="No reg";
        } else if ((regNumber.length()<=8)){
            this.regNumber=regNumber;
        }
    }

    public void setYear(int year) {
        if ((year>=2000)&&(year<=2023)){
            this.year=year;
        }
    }

    public void setCost(float cost) {
        if (cost>=1000){
            this.cost=cost;
        }
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        if (model==null){
            this.model="No model";
        } else if (model.length()<=15){
            this.model=model;
        }
    }
}
