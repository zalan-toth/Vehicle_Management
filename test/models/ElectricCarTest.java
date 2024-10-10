package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElectricCarTest {

    ElectricCar electricCarValidAtBottomEdge, electricCarInValidBelow, electricCarValidAtTopEdge, electricCarValidOverTopEdge;


    @BeforeEach
    void setup() {
        electricCarValidAtBottomEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,100, 40);
        electricCarInValidBelow = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,99, 39);
        electricCarValidAtTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,500,60);
        electricCarValidOverTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,501, 61);

    }


    @Nested
    class constructorTests {
        @Test
        void validatingRange() {
            assertEquals(100, electricCarValidAtBottomEdge.getRange());
            assertEquals(100, electricCarInValidBelow.getRange());
            assertEquals(500, electricCarValidAtTopEdge.getRange());
            assertEquals(100, electricCarValidOverTopEdge.getRange());
        }

        @Test
        void validatingEngineKWatts() {
            assertEquals(40, electricCarValidAtBottomEdge.getEngineKWatts());
            assertEquals(40, electricCarInValidBelow.getEngineKWatts());
            assertEquals(60, electricCarValidAtTopEdge.getEngineKWatts());
            assertEquals(40, electricCarValidOverTopEdge.getEngineKWatts());
        }
    }
        @Nested
        class setterAndGettersTests {
            @Test
            void setGetRange(){
                assertEquals(100,electricCarInValidBelow.getRange());
                electricCarInValidBelow.setRange(99);
                assertEquals(100,electricCarInValidBelow.getRange());
                electricCarInValidBelow.setRange(101);
                assertEquals(101,electricCarInValidBelow.getRange());
                electricCarInValidBelow.setRange(499);
                assertEquals(499,electricCarInValidBelow.getRange());
                electricCarInValidBelow.setRange(500);
                assertEquals(500,electricCarInValidBelow.getRange());
                electricCarInValidBelow.setRange(501);
                assertEquals(500,electricCarInValidBelow.getRange());
            }
            @Test
            void setGetEngineKWatts(){

                assertEquals(40,electricCarInValidBelow.getEngineKWatts());
                electricCarInValidBelow.setEngineKWatts(39);
                assertEquals(40,electricCarInValidBelow.getEngineKWatts());
                electricCarInValidBelow.setEngineKWatts(41);
                assertEquals(41,electricCarInValidBelow.getEngineKWatts());
                electricCarInValidBelow.setEngineKWatts(59);
                assertEquals(59,electricCarInValidBelow.getEngineKWatts());
                electricCarInValidBelow.setEngineKWatts(60);
                assertEquals(60,electricCarInValidBelow.getEngineKWatts());
                electricCarInValidBelow.setEngineKWatts(61);
                assertEquals(60,electricCarInValidBelow.getEngineKWatts());
            }

        }

        @Test
        void validatingTheEqualsMethod() {
            ElectricCar electricCar1 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,355,44);
            ElectricCar electricCar2 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,355,44);

            assertEquals(electricCar1, electricCar2);

            electricCar2.setEngineKWatts(56);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setEngineKWatts(44);  // reset
            assertEquals(electricCar1, electricCar2);

            electricCar2.setRange(205);  // change value
            assertNotEquals(electricCar1, electricCar2);  // check that the equals picks up the difference
            electricCar2.setRange(355);  // reset
            assertEquals(electricCar1, electricCar2);

        }

        @Test
        void testToString() {
            ElectricCar electricCar = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,265,48);
            String strToTest = electricCar.toString();
            assertTrue(strToTest.contains("265")); //range
            assertTrue(strToTest.contains("48")); //engine


        }

    @Test
    void getCarbonFootPrint() {
        assertEquals(0.046, electricCarInValidBelow.getCarbonFootPrint(), 0.01);
        assertEquals(0.046, electricCarValidAtBottomEdge.getCarbonFootPrint(), 0.01);
        assertEquals(0.069, electricCarValidAtTopEdge.getCarbonFootPrint(), 0.01);
        assertEquals(0.046, electricCarValidOverTopEdge.getCarbonFootPrint(), 0.01);
    }
}