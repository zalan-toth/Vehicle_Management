/**
 * This class handles Electric Cars.
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package models;

import utils.lang.Colors;

import java.util.Objects;

public class ElectricCar extends Car {

    private int range = 100;
    private float engineKWatts = 40;
    private float factor = 20000;


    //░█████╗░░█████╗░███╗░░██╗░██████╗████████╗██████╗░██╗░░░██╗░█████╗░████████╗░█████╗░██████╗░
    //██╔══██╗██╔══██╗████╗░██║██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗
    //██║░░╚═╝██║░░██║██╔██╗██║╚█████╗░░░░██║░░░██████╔╝██║░░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝
    //██║░░██╗██║░░██║██║╚████║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗
    //╚█████╔╝╚█████╔╝██║░╚███║██████╔╝░░░██║░░░██║░░██║╚██████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║
    //░╚════╝░░╚════╝░╚═╝░░╚══╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝


    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed, int range, float engineKWatts) {
        super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
        setRange(range);
        setEngineKWatts(engineKWatts);
    }

    //░██████╗░███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝░██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //██║░░██╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //██║░░╚██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //╚██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //░╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


    public int getRange() {
        return range;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }


    public double getCarbonFootPrint() {
        return (getEngineKWatts() * getAge()) / factor;
    }

    //░██████╗███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //╚█████╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //░╚═══██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░

    public void setRange(int range) {
        if ((range >= 100) && (range <= 500)) {
            this.range = range;
        }
    }


    public void setEngineKWatts(float engineKWatts) {
        if ((engineKWatts >= 40) && (engineKWatts <= 60)) {
            this.engineKWatts = engineKWatts;
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
        return super.toString() + Colors.SORT + "ElectricCar{" +
                "range=" + range +
                ", engineKWatts=" + engineKWatts +
                ", factor=" + factor +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricCar that)) return false;
        if (!super.equals(o)) return false;
        return getRange() == that.getRange() && Float.compare(that.getEngineKWatts(), getEngineKWatts()) == 0 && Float.compare(that.factor, factor) == 0;
    }

}
