package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Domain.Stores.DataStore;
import Miscellaneous.BiggestProduction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class BiggestAbsoluteDifferenceOfProductionControllerTest {

    void setup() {
        DataStore fruitStore = App.getInstance().getStore();
        fruitStore.getFruitHarvest().clear();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Year y1 = new Year(2000), y2 = new Year(2001), y3 = new Year(2002), y4 = new Year(2003), y5 = new Year(2004);
        Year y6 = new Year(2005), y7 = new Year(2006), y8 = new Year(2007), y9 = new Year(2008);

        Quantity q1 = new Quantity(234312), q2 = new Quantity(654651), q3 = new Quantity(564165), q4 = new Quantity(16848), q5 = new Quantity(861451),  q6 = new Quantity(18135), q7 = new Quantity(189151);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>(), yearQuantityMap3 = new LinkedHashMap<>();
        yearQuantityMap1.put(y1, q6);
        yearQuantityMap1.put(y2, q4);
        yearQuantityMap1.put(y3, q3);
        yearQuantityMap2.put(y4, q1);
        yearQuantityMap2.put(y5, q2);
        yearQuantityMap2.put(y6, q3);
        yearQuantityMap3.put(y7, q7);
        yearQuantityMap3.put(y8, q4);
        yearQuantityMap3.put(y9, q5);

        Country c1 = new Country("Portugal");

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>(), countryMap2 = new LinkedHashMap<>(), countryMap3 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1);
        countryMap2.put(c1, yearQuantityMap2);
        countryMap3.put(c1, yearQuantityMap3);

        Fruit f1 = new Fruit("Banana"), f2 = new Fruit("Apples"), f3 = new Fruit("Orange");
        Fruit f4 = new Fruit("Strawberry"), f5 = new Fruit("Dragon Fruit"), f6 = new Fruit("Pear");

        fruitHarvest.put(f1, countryMap1);
        fruitHarvest.put(f2, countryMap2);
        fruitHarvest.put(f3, countryMap3);
        fruitHarvest.put(f4, countryMap1);
        fruitHarvest.put(f5, countryMap2);
        fruitHarvest.put(f6, countryMap3);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void getBiggestAbsoluteDifferenceEmpty(){
        App.getInstance().getStore().getFruitHarvest().clear();
        BiggestAbsoluteDifferenceOfProductionController controller = new BiggestAbsoluteDifferenceOfProductionController();
        Set<BiggestProduction> actualSet = controller.getBiggestAbsoluteDifference(new Country("Portugal"));
        Set<BiggestProduction> expectedSet = new LinkedHashSet<>();

        Assertions.assertEquals(expectedSet.toString(),actualSet.toString());

    }
    @Test
    public void getBiggestAbsoluteDifferenceNormal() {
        setup();
        BiggestAbsoluteDifferenceOfProductionController controller = new BiggestAbsoluteDifferenceOfProductionController();
        Set<BiggestProduction> actualSet = controller.getBiggestAbsoluteDifference(new Country("Portugal"));
        Set<BiggestProduction> expectedSet = new LinkedHashSet<>();

        BiggestProduction expected1 = new BiggestProduction("2001/2002", "Banana", 547317);
        BiggestProduction expected2 = new BiggestProduction("2003/2004", "Apples", 420339);
        BiggestProduction expected3 = new BiggestProduction("2007/2008", "Orange", 844603);
        BiggestProduction expected4 = new BiggestProduction("2001/2002", "Strawberry", 547317);
        BiggestProduction expected5 = new BiggestProduction("2003/2004", "Dragon Fruit", 420339);
        BiggestProduction expected6 = new BiggestProduction("2007/2008", "Pear", 844603);

        expectedSet.add(expected1);
        expectedSet.add(expected2);
        expectedSet.add(expected3);
        expectedSet.add(expected4);
        expectedSet.add(expected5);
        expectedSet.add(expected6);

        Assertions.assertEquals(expectedSet.toString(),actualSet.toString());
    }





}