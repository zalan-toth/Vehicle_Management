/**
 * This class handles Cars (abstract).
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package models;

import utils.lang.Colors;

import java.util.Objects;

public abstract class Car extends Vehicle {

    private int secs0To60 = 4;
    private int power = 120;
    private float torque = 100;
    private int topSpeed = 50;


    //░█████╗░░█████╗░███╗░░██╗░██████╗████████╗██████╗░██╗░░░██╗░█████╗░████████╗░█████╗░██████╗░
    //██╔══██╗██╔══██╗████╗░██║██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗
    //██║░░╚═╝██║░░██║██╔██╗██║╚█████╗░░░░██║░░░██████╔╝██║░░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝
    //██║░░██╗██║░░██║██║╚████║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗
    //╚█████╔╝╚█████╔╝██║░╚███║██████╔╝░░░██║░░░██║░░██║╚██████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║
    //░╚════╝░░╚════╝░╚═╝░░╚══╝╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░╚═════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝

    public Car(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed) {
        super(regNumber,model,cost,manufacturer,year);
        setSecs0To60(secs0To60);
        setPower(power);
        setTorque(torque);
        setTopSpeed(topSpeed);
    }


    //░██████╗░███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝░██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //██║░░██╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //██║░░╚██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //╚██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //░╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


    public int getSecs0To60() {
        return secs0To60;
    }

    public int getPower() {
        return power;
    }

    public float getTorque() {
        return torque;
    }

    public int getTopSpeed() {
        return topSpeed;
    }


    //░██████╗███████╗████████╗████████╗███████╗██████╗░░██████╗
    //██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    //╚█████╗░█████╗░░░░░██║░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    //░╚═══██╗██╔══╝░░░░░██║░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    //██████╔╝███████╗░░░██║░░░░░░██║░░░███████╗██║░░██║██████╔╝
    //╚═════╝░╚══════╝░░░╚═╝░░░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░


    public void setSecs0To60(int secs0To60) {
        if ((secs0To60>=4)&&(secs0To60<=25)){
            this.secs0To60 = secs0To60;
        }
    }

    public void setPower(int power) {
        if ((power>=120)&&(power<=300)){
            this.power = power;
        }
    }

    public void setTorque(float torque) {
        if ((torque>=100)&&(torque<=400)){
            this.torque = torque;
        }
    }

    public void setTopSpeed(int topSpeed) {
        if ((topSpeed>=50)&&(topSpeed<=3000)){
            this.topSpeed = topSpeed;
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
        return super.toString() + Colors.STARTUP + "\nCar{" +
                "secs0To60=" + secs0To60 +
                ", power=" + power +
                ", torque=" + torque +
                ", topSpeed=" + topSpeed +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;
        return getSecs0To60() == car.getSecs0To60() && getPower() == car.getPower() && Float.compare(car.getTorque(), getTorque()) == 0 && getTopSpeed() == car.getTopSpeed();
    }

}
