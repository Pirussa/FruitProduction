package Controllers;

import Domain.*;
import Stores.*;
import org.junit.jupiter.api.*;
import java.util.*;


class SortByConsecutiveYearControllerTest {

    void setUpForNormalConditions() {
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
        Country c4 = new Country("Germamy"), c5 = new Country("Greece");

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>(),  countryMap2 = new LinkedHashMap<>(),  countryMap3 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1); countryMap1.put(c2, yearQuantityMap2); countryMap1.put(c3, yearQuantityMap3); countryMap1.put(c4, yearQuantityMap3);
        countryMap2.put(c1, yearQuantityMap5); countryMap2.put(c5, yearQuantityMap1); countryMap2.put(c3, yearQuantityMap4);
        countryMap3.put(c1, yearQuantityMap6); countryMap3.put(c2, yearQuantityMap3);

        Fruit f1 = new Fruit("Banana"), f2 = new Fruit("Apples"), f3 = new Fruit("Orange");
        Fruit f4 = new Fruit("Straberry"), f5 = new Fruit("Dragon Fruit"), f6 = new Fruit("Peer");

        fruitHarvest.put(f1, countryMap1); fruitHarvest.put(f2, countryMap2); fruitHarvest.put(f3, countryMap3);
        fruitHarvest.put(f4, countryMap1); fruitHarvest.put(f5, countryMap2); fruitHarvest.put(f6, countryMap3);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsForNormalConditions() {
        setUpForNormalConditions();
        SortByConsecutiveYearController controller = new SortByConsecutiveYearController();
        Map<Country, Integer> actualMap = controller.sortByConsecutiveYears(new Fruit("Apples"));
        Map<Country, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put(new Country("Portugal"), 2);
        expectedMap.put(new Country("Greece"), 1);
        expectedMap.put(new Country("France"), 3);

        Assertions.assertEquals(actualMap, expectedMap);
    }

    void setUpForNoIncrements() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Year y1 = new Year(2000), y2 = new Year(2001), y3 = new Year(2002), y4 = new Year(2003), y5 = new Year(2004);
        Year y6 = new Year(2005), y7 = new Year(2006), y8 = new Year(2007), y9 = new Year(2008), y10 = new Year(2009);

        Quantity q1 = new Quantity(234312), q2 = new Quantity(654651), q3 = new Quantity(564165), q4 = new Quantity(16848), q5 = new Quantity(861451);
        Quantity q6 = new Quantity(6505), q7 = new Quantity(68181), q8 = new Quantity(31584), q9 = new Quantity(18135), q10 = new Quantity(189151);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>(), yearQuantityMap3 = new LinkedHashMap<>();
        Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>(), yearQuantityMap5 = new LinkedHashMap<>(), yearQuantityMap6 = new LinkedHashMap<>();
        yearQuantityMap1.put(y1, q1); yearQuantityMap1.put(y2, q9); yearQuantityMap1.put(y9, q6);
        yearQuantityMap2.put(y2, q5); yearQuantityMap2.put(y1, q3); yearQuantityMap2.put(y4, q1);
        yearQuantityMap3.put(y5, q10); yearQuantityMap3.put(y2, q7); yearQuantityMap3.put(y1, q8);
        yearQuantityMap4.put(y7, q2); yearQuantityMap4.put(y10, q3); yearQuantityMap4.put(y6, q1);
        yearQuantityMap5.put(y1, q5); yearQuantityMap5.put(y4, q2); yearQuantityMap5.put(y6, q3);
        yearQuantityMap6.put(y8, q10); yearQuantityMap6.put(y3, q4); yearQuantityMap6.put(y10, q9);

        Country c1 = new Country("Portugal"), c2 = new Country("Spain"), c3 = new Country("France");
        Country c4 = new Country("Germamy"), c5 = new Country("Greece");

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>(),  countryMap2 = new LinkedHashMap<>(),  countryMap3 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1); countryMap1.put(c2, yearQuantityMap2); countryMap1.put(c3, yearQuantityMap3); countryMap1.put(c4, yearQuantityMap3);
        countryMap2.put(c1, yearQuantityMap5); countryMap2.put(c5, yearQuantityMap1); countryMap2.put(c3, yearQuantityMap4);
        countryMap3.put(c1, yearQuantityMap6); countryMap3.put(c2, yearQuantityMap3);

        Fruit f1 = new Fruit("Banana"), f2 = new Fruit("Apples"), f3 = new Fruit("Orange");
        Fruit f4 = new Fruit("Straberry"), f5 = new Fruit("Dragon Fruit"), f6 = new Fruit("Peer");

        fruitHarvest.put(f1, countryMap1); fruitHarvest.put(f2, countryMap2); fruitHarvest.put(f3, countryMap3);
        fruitHarvest.put(f4, countryMap1); fruitHarvest.put(f5, countryMap2); fruitHarvest.put(f6, countryMap3);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsWithZeroIncrements() {
        setUpForNoIncrements();
        SortByConsecutiveYearController controller = new SortByConsecutiveYearController();
        Map<Country, Integer> actualMap = controller.sortByConsecutiveYears(new Fruit("Apples"));
        Map<Country, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put(new Country("Portugal"), 1);
        expectedMap.put(new Country("Greece"), 1);
        expectedMap.put(new Country("France"), 1);

        Assertions.assertEquals(actualMap, expectedMap);
    }



    void setUpForEmptyYearQtMap() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>(), yearQuantityMap3 = new LinkedHashMap<>();
        Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>(), yearQuantityMap5 = new LinkedHashMap<>(), yearQuantityMap6 = new LinkedHashMap<>();

        Country c1 = new Country("Portugal"), c2 = new Country("Spain"), c3 = new Country("France");
        Country c4 = new Country("Germamy"), c5 = new Country("Greece");

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>(),  countryMap2 = new LinkedHashMap<>(),  countryMap3 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1); countryMap1.put(c2, yearQuantityMap2); countryMap1.put(c3, yearQuantityMap3); countryMap1.put(c4, yearQuantityMap3);
        countryMap2.put(c1, yearQuantityMap5); countryMap2.put(c5, yearQuantityMap1); countryMap2.put(c3, yearQuantityMap4);
        countryMap3.put(c1, yearQuantityMap6); countryMap3.put(c2, yearQuantityMap3);

        Fruit f1 = new Fruit("Banana"), f2 = new Fruit("Apples"), f3 = new Fruit("Orange");
        Fruit f4 = new Fruit("Straberry"), f5 = new Fruit("Dragon Fruit"), f6 = new Fruit("Peer");

        fruitHarvest.put(f1, countryMap1); fruitHarvest.put(f2, countryMap2); fruitHarvest.put(f3, countryMap3);
        fruitHarvest.put(f4, countryMap1); fruitHarvest.put(f5, countryMap2); fruitHarvest.put(f6, countryMap3);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsWithEmptyYearQtMap() {
        setUpForEmptyYearQtMap();
        SortByConsecutiveYearController controller = new SortByConsecutiveYearController();
        Map<Country, Integer> actualMap = controller.sortByConsecutiveYears(new Fruit("Apples"));
        Map<Country, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put(new Country("Portugal"), 0);
        expectedMap.put(new Country("Greece"), 0);
        expectedMap.put(new Country("France"), 0);

        Assertions.assertEquals(actualMap, expectedMap);
    }

}