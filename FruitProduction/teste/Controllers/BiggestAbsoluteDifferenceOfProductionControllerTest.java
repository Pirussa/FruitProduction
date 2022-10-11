package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import Utils.BiggestProduction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BiggestAbsoluteDifferenceOfProductionControllerTest {
    void setup() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Year y1 = new Year(2000), y2 = new Year(2001), y3 = new Year(2002), y4 = new Year(2003), y5 = new Year(2004);
        Year y6 = new Year(2005), y7 = new Year(2006), y8 = new Year(2007), y9 = new Year(2008), y10 = new Year(2009);

        Quantity q1 = new Quantity(234312), q2 = new Quantity(654651), q3 = new Quantity(564165), q4 = new Quantity(16848), q5 = new Quantity(861451);
        Quantity q6 = new Quantity(6505), q7 = new Quantity(68181), q8 = new Quantity(31584), q9 = new Quantity(18135), q10 = new Quantity(189151);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>(), yearQuantityMap3 = new LinkedHashMap<>();
        Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>(), yearQuantityMap5 = new LinkedHashMap<>(), yearQuantityMap6 = new LinkedHashMap<>();
        yearQuantityMap1.put(y1, q9); yearQuantityMap1.put(y2, q4); yearQuantityMap1.put(y9, q3);
        yearQuantityMap2.put(y2, q7); yearQuantityMap2.put(y1, q5); yearQuantityMap2.put(y4, q9);
        yearQuantityMap3.put(y5, q1); yearQuantityMap3.put(y2, q2); yearQuantityMap3.put(y1, q8);
        yearQuantityMap4.put(y7, q6); yearQuantityMap4.put(y10, q1); yearQuantityMap4.put(y6, q3);
        yearQuantityMap5.put(y1, q1); yearQuantityMap5.put(y4, q2); yearQuantityMap5.put(y6, q3);
        yearQuantityMap6.put(y8, q10); yearQuantityMap6.put(y3, q4); yearQuantityMap6.put(y10, q5);

        Country c1 = new Country("Portugal"), c2 = new Country("Spain"), c3 = new Country("France");
        Country c4 = new Country("Germany"), c5 = new Country("Greece");

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>(),  countryMap2 = new LinkedHashMap<>(),  countryMap3 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1); countryMap1.put(c2, yearQuantityMap2); countryMap1.put(c3, yearQuantityMap3); countryMap1.put(c4, yearQuantityMap3);
        countryMap2.put(c1, yearQuantityMap5); countryMap2.put(c5, yearQuantityMap1); countryMap2.put(c3, yearQuantityMap4);
        countryMap3.put(c1, yearQuantityMap6); countryMap3.put(c2, yearQuantityMap3);

        Fruit f1 = new Fruit("Banana"), f2 = new Fruit("Apples"), f3 = new Fruit("Orange");
        Fruit f4 = new Fruit("Strawberry"), f5 = new Fruit("Dragon Fruit"), f6 = new Fruit("Pear");

        fruitHarvest.put(f1, countryMap1); fruitHarvest.put(f2, countryMap2); fruitHarvest.put(f3, countryMap3);
        fruitHarvest.put(f4, countryMap1); fruitHarvest.put(f5, countryMap2); fruitHarvest.put(f6, countryMap3);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void getBiggestAbsoluteDifferenceNormal(){
        setup();
        BiggestAbsoluteDifferenceOfProductionController controller = new BiggestAbsoluteDifferenceOfProductionController();
        Set<BiggestProduction> actualSet = controller.getBiggestAbsoluteDifference(new Country("Portugal"));
        Set<BiggestProduction> expectedSet = new LinkedHashSet<>();

        BiggestProduction expected1 = new BiggestProduction("2001/2008", "Banana", 547317);
        BiggestProduction expected2 = new BiggestProduction("2000/2003", "Apples", 420339);
        BiggestProduction expected3 = new BiggestProduction("2002/2009", "Orange", 844603);
        BiggestProduction expected4 = new BiggestProduction("2001/2008", "Strawberry", 547317);
        BiggestProduction expected5 = new BiggestProduction("2000/2003", "Dragon Fruit", 420339);
        BiggestProduction expected6 = new BiggestProduction("2002/2009", "Pear", 844603);

        expectedSet.add(expected1);
        expectedSet.add(expected2);
        expectedSet.add(expected3);
        expectedSet.add(expected4);
        expectedSet.add(expected5);
        expectedSet.add(expected6);

     // assertEquals(expectedSet.size(),actualSet.size());
     // assertEquals(true,actualSet.contains(expected1));
     // assertEquals(true,actualSet.contains(expected2));
     // assertEquals(true,actualSet.contains(expected3));
     // assertEquals(true,actualSet.contains(expected4));
     // assertEquals(true,actualSet.contains(expected5));
     // assertEquals(true,actualSet.contains(expected6));

    }


}