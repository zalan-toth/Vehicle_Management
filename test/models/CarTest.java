package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    ElectricCar electricCarValidAtBottomEdge, electricCarInValidBelow, electricCarValidAtTopEdge, electricCarValidOverTopEdge;



    @BeforeEach
    void setup() {
        electricCarValidAtBottomEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 4, 120, 100,50,0,0);
        electricCarInValidBelow = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 3, 119, 99,49,0,0);
        electricCarValidAtTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 25, 300, 400,3000,0,0);
        electricCarValidOverTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 26, 301, 401,3001,0,0);
    }

    @Nested
    class constructorTests {
        @Test
        void validatingSecs0To60() {
            assertEquals(4, electricCarValidAtBottomEdge.getSecs0To60());
            assertEquals(4, electricCarInValidBelow.getSecs0To60());
            assertEquals(25, electricCarValidAtTopEdge.getSecs0To60());
            assertEquals(4, electricCarValidOverTopEdge.getSecs0To60());
        }

        @Test
        void validatingPower() {
            assertEquals(120, electricCarValidAtBottomEdge.getPower());
            assertEquals(120, electricCarInValidBelow.getPower());
            assertEquals(300, electricCarValidAtTopEdge.getPower());
            assertEquals(120, electricCarValidOverTopEdge.getPower());
        }

        @Test
        void validatingTorque() {
            assertEquals(100, electricCarValidAtBottomEdge.getTorque());
            assertEquals(100, electricCarInValidBelow.getTorque());
            assertEquals(400, electricCarValidAtTopEdge.getTorque());
            assertEquals(100, electricCarValidOverTopEdge.getTorque());
        }

        @Test
        void validatingTopSpeed() {
            assertEquals(50, electricCarValidAtBottomEdge.getTopSpeed());
            assertEquals(50, electricCarInValidBelow.getTopSpeed());
            assertEquals(3000, electricCarValidAtTopEdge.getTopSpeed());
            assertEquals(50, electricCarValidOverTopEdge.getTopSpeed());
        }
    }
        @Nested
        class setterAndGettersTests {
            @Test
            void setGetSecs0To60(){
                assertEquals(4,electricCarInValidBelow.getSecs0To60());
                electricCarInValidBelow.setSecs0To60(3);
                assertEquals(4,electricCarInValidBelow.getSecs0To60());
                electricCarInValidBelow.setSecs0To60(5);
                assertEquals(5,electricCarInValidBelow.getSecs0To60());
                electricCarInValidBelow.setSecs0To60(24);
                assertEquals(24,electricCarInValidBelow.getSecs0To60());
                electricCarInValidBelow.setSecs0To60(25);
                assertEquals(25,electricCarInValidBelow.getSecs0To60());
                electricCarInValidBelow.setSecs0To60(26);
                assertEquals(25,electricCarInValidBelow.getSecs0To60());
            }
            @Test
            void setGetPower(){

                assertEquals(120,electricCarInValidBelow.getPower());
                electricCarInValidBelow.setPower(119);
                assertEquals(120,electricCarInValidBelow.getPower());
                electricCarInValidBelow.setPower(121);
                assertEquals(121,electricCarInValidBelow.getPower());
                electricCarInValidBelow.setPower(299);
                assertEquals(299,electricCarInValidBelow.getPower());
                electricCarInValidBelow.setPower(300);
                assertEquals(300,electricCarInValidBelow.getPower());
                electricCarInValidBelow.setPower(301);
                assertEquals(300,electricCarInValidBelow.getPower());
            }
            @Test
            void setGetTorque(){
                assertEquals(100,electricCarInValidBelow.getTorque());
                electricCarInValidBelow.setTorque(99);
                assertEquals(100,electricCarInValidBelow.getTorque());
                electricCarInValidBelow.setTorque(101);
                assertEquals(101,electricCarInValidBelow.getTorque());
                electricCarInValidBelow.setTorque(399);
                assertEquals(399,electricCarInValidBelow.getTorque());
                electricCarInValidBelow.setTorque(400);
                assertEquals(400,electricCarInValidBelow.getTorque());
                electricCarInValidBelow.setTorque(401);
                assertEquals(400,electricCarInValidBelow.getTorque());
            }
            @Test
            void setGetTopSpeed(){
                assertEquals(50,electricCarInValidBelow.getTopSpeed());
                electricCarInValidBelow.setTopSpeed(49);
                assertEquals(50,electricCarInValidBelow.getTopSpeed());
                electricCarInValidBelow.setTopSpeed(51);
                assertEquals(51,electricCarInValidBelow.getTopSpeed());
                electricCarInValidBelow.setTopSpeed(2999);
                assertEquals(2999,electricCarInValidBelow.getTopSpeed());
                electricCarInValidBelow.setTopSpeed(3000);
                assertEquals(3000,electricCarInValidBelow.getTopSpeed());
                electricCarInValidBelow.setTopSpeed(3001);
                assertEquals(3000,electricCarInValidBelow.getTopSpeed());
            }

        }

        @Test
        void validatingTheEqualsMethod() {
            ElectricCar electricCar1 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,50,0);
            ElectricCar electricCar2 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,50,0);

            assertEquals(electricCar1, electricCar2);

            electricCar2.setPower(140);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setPower(200);  // reset
            assertEquals(electricCar1, electricCar2);

            electricCar2.setTorque(205);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setTorque(201);  // reset
            assertEquals(electricCar1, electricCar2);

            electricCar2.setSecs0To60(25);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setSecs0To60(21);  // reset
            assertEquals(electricCar1, electricCar2);

            electricCar2.setTopSpeed(350);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setTopSpeed(300);  // reset
            assertEquals(electricCar1, electricCar2);
    }

        @Test
        void testToString() {
            ElectricCar electricCar = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,0,0);
            String strToTest = electricCar.toString();
            assertTrue(strToTest.contains("21")); //secs0To60
            assertTrue(strToTest.contains("200")); //power
            assertTrue(strToTest.contains("201")); //torque
            assertTrue(strToTest.contains("300")); //topSpeed


        }
    }
