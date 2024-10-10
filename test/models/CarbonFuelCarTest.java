package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CarbonFuelCarTest {

    CarbonFuelCar carbonFuelCarValidAtBottomEdge, carbonFuelCarInValidBelow, carbonFuelCarValidAtTopEdge, carbonFuelCarValidOverTopEdge;


    @BeforeEach
    void setup() {
        carbonFuelCarValidAtBottomEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,5, 0,true,"petrol",800);
        carbonFuelCarInValidBelow = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,4, 1,false,"appleJuice",799);
        carbonFuelCarValidAtTopEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,20, Integer.MAX_VALUE,false,"diesel",2500);
        carbonFuelCarValidOverTopEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0,21, Integer.MAX_VALUE,false,"orangeJuice",2501);

    }

    @Nested
    class constructorTests {
        @Test
        void validatingFuelConsumption() {
            assertEquals(5, carbonFuelCarValidAtBottomEdge.getFuelConsumption());
            assertEquals(5, carbonFuelCarInValidBelow.getFuelConsumption());
            assertEquals(20, carbonFuelCarValidAtTopEdge.getFuelConsumption());
            assertEquals(5, carbonFuelCarValidOverTopEdge.getFuelConsumption());
        }
        @Test
        void validatingCarbonEmission() {
            assertEquals(1, carbonFuelCarValidAtBottomEdge.getCarbonEmission());
            assertEquals(1, carbonFuelCarInValidBelow.getCarbonEmission());
            assertEquals(Integer.MAX_VALUE, carbonFuelCarValidAtTopEdge.getCarbonEmission());
            assertEquals(Integer.MAX_VALUE, carbonFuelCarValidOverTopEdge.getCarbonEmission());
        }
        @Test
        void validatingAutomatic() {
            assertEquals(true, carbonFuelCarValidAtBottomEdge.isAutomatic());
            assertEquals(false, carbonFuelCarInValidBelow.isAutomatic());
        }
        @Test
        void validatingFuelType() {
            assertEquals("petrol", carbonFuelCarValidAtBottomEdge.getFuelType());
            assertEquals("petrol", carbonFuelCarInValidBelow.getFuelType());
            assertEquals("diesel", carbonFuelCarValidAtTopEdge.getFuelType());
            assertEquals("petrol", carbonFuelCarValidOverTopEdge.getFuelType());
        }
        @Test
        void validatingEngineSize() {
            assertEquals(800, carbonFuelCarValidAtBottomEdge.getEngineSize());
            assertEquals(800, carbonFuelCarInValidBelow.getEngineSize());
            assertEquals(2500, carbonFuelCarValidAtTopEdge.getEngineSize());
            assertEquals(800, carbonFuelCarValidOverTopEdge.getEngineSize());
        }

    }
    @Nested
    class setterAndGettersTests {
        @Test
        void setGetFuelConsumption(){
            assertEquals(5,carbonFuelCarInValidBelow.getFuelConsumption());
            carbonFuelCarInValidBelow.setFuelConsumption(4);
            assertEquals(5,carbonFuelCarInValidBelow.getFuelConsumption());
            carbonFuelCarInValidBelow.setFuelConsumption(6);
            assertEquals(6,carbonFuelCarInValidBelow.getFuelConsumption());
            carbonFuelCarInValidBelow.setFuelConsumption(19);
            assertEquals(19,carbonFuelCarInValidBelow.getFuelConsumption());
            carbonFuelCarInValidBelow.setFuelConsumption(20);
            assertEquals(20,carbonFuelCarInValidBelow.getFuelConsumption());
            carbonFuelCarInValidBelow.setFuelConsumption(21);
            assertEquals(20,carbonFuelCarInValidBelow.getFuelConsumption());
        }
        @Test
        void setGetCarbonEmission(){
            assertEquals(1,carbonFuelCarInValidBelow.getCarbonEmission());
            carbonFuelCarInValidBelow.setCarbonEmission(0);
            assertEquals(1,carbonFuelCarInValidBelow.getCarbonEmission());
            carbonFuelCarInValidBelow.setCarbonEmission(2);
            assertEquals(2,carbonFuelCarInValidBelow.getCarbonEmission());
            carbonFuelCarInValidBelow.setCarbonEmission(-1);
            assertEquals(2,carbonFuelCarInValidBelow.getCarbonEmission());
            carbonFuelCarInValidBelow.setCarbonEmission(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE,carbonFuelCarInValidBelow.getCarbonEmission());
        }
        @Test
        void setGetAutomatic(){
            assertEquals(false,carbonFuelCarInValidBelow.isAutomatic());
            carbonFuelCarInValidBelow.setAutomatic(true);
            assertEquals(true,carbonFuelCarInValidBelow.isAutomatic());
            carbonFuelCarInValidBelow.setAutomatic(false);
            assertEquals(false,carbonFuelCarInValidBelow.isAutomatic());
        }

        @Test
        void setGetFuelType(){
            assertEquals("petrol",carbonFuelCarInValidBelow.getFuelType());
            carbonFuelCarInValidBelow.setFuelType("diesel");
            assertEquals("diesel",carbonFuelCarInValidBelow.getFuelType());
            carbonFuelCarInValidBelow.setFuelType("applejuice");
            assertEquals("diesel",carbonFuelCarInValidBelow.getFuelType());
            carbonFuelCarInValidBelow.setFuelType("petrol");
            assertEquals("petrol",carbonFuelCarInValidBelow.getFuelType());
            carbonFuelCarInValidBelow.setFuelType(null);
            assertEquals("petrol",carbonFuelCarInValidBelow.getFuelType());
        }
        @Test
        void setGetEngineSize(){
            assertEquals(800,carbonFuelCarInValidBelow.getEngineSize());
            carbonFuelCarInValidBelow.setEngineSize(799);
            assertEquals(800,carbonFuelCarInValidBelow.getEngineSize());
            carbonFuelCarInValidBelow.setEngineSize(801);
            assertEquals(801,carbonFuelCarInValidBelow.getEngineSize());
            carbonFuelCarInValidBelow.setEngineSize(2499);
            assertEquals(2499,carbonFuelCarInValidBelow.getEngineSize());
            carbonFuelCarInValidBelow.setEngineSize(2500);
            assertEquals(2500,carbonFuelCarInValidBelow.getEngineSize());
            carbonFuelCarInValidBelow.setEngineSize(2501);
            assertEquals(2500,carbonFuelCarInValidBelow.getEngineSize());
        }
    }
    @Test
    void testToString() {
        CarbonFuelCar carbonFuelCar = new CarbonFuelCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,17,33,true,"diesel",1150);
        String strToTest = carbonFuelCar.toString();
        assertTrue(strToTest.contains("17")); //fuelConsumption
        assertTrue(strToTest.contains("33")); //carbonEmission
        assertTrue(strToTest.contains("true")); //automatic
        assertTrue(strToTest.contains("diesel")); //fuelType
        assertTrue(strToTest.contains("1150")); //engineSize


    }
    @Test
    void getCarbonFootPrint() {
        assertEquals(46, carbonFuelCarInValidBelow.getCarbonFootPrint(), 0.01);
        assertEquals(46, carbonFuelCarValidAtBottomEdge.getCarbonFootPrint(), 0.01);
    }
    @Test
    void validatingTheEqualsMethod() {
        CarbonFuelCar carbonFuelCar1 = new CarbonFuelCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,17,33,true,"petrol",1000);
        CarbonFuelCar carbonFuelCar2 = new CarbonFuelCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 21, 200, 201,300,17,33,true,"petrol",1000);

        assertEquals(carbonFuelCar1, carbonFuelCar2);

        carbonFuelCar2.setEngineSize(1600);  // change value
        assertNotEquals(carbonFuelCar1, carbonFuelCar2);  // check that the equals picks up the difference
        carbonFuelCar2.setEngineSize(1000);  // reset
        assertEquals(carbonFuelCar1, carbonFuelCar2);

        carbonFuelCar2.setFuelType("diesel");  // change value
        assertNotEquals(carbonFuelCar1, carbonFuelCar2);  // check that the equals picks up the difference
        carbonFuelCar2.setFuelType("petrol");  // reset
        assertEquals(carbonFuelCar1, carbonFuelCar2);

        carbonFuelCar2.setFuelConsumption(20);  // change value
        assertNotEquals(carbonFuelCar1, carbonFuelCar2);  // check that the equals picks up the difference
        carbonFuelCar2.setFuelConsumption(17);  // reset
        assertEquals(carbonFuelCar1, carbonFuelCar2);

        carbonFuelCar2.setAutomatic(false);  // change value
        assertNotEquals(carbonFuelCar1, carbonFuelCar2);  // check that the equals picks up the difference
        carbonFuelCar2.setAutomatic(true);  // reset
        assertEquals(carbonFuelCar1, carbonFuelCar2);

        carbonFuelCar2.setCarbonEmission(44);  // change value
        assertNotEquals(carbonFuelCar1, carbonFuelCar2);  // check that the equals picks up the difference
        carbonFuelCar2.setCarbonEmission(33);  // reset
        assertEquals(carbonFuelCar1, carbonFuelCar2);

    }
}
