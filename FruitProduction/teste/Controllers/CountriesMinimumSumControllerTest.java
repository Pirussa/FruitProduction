package Controllers;

import Domain.*;
import Stores.*;
import Utils.CountriesWithMinimumQuantitySum;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.*;
public class CountriesMinimumSumControllerTest {
    @BeforeEach
    void setUp() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Year y1 = new Year(2000), y2 = new Year(2001), y3 = new Year(2002), y4 = new Year(2003), y5 = new Year(2004);
        Year y6 = new Year(2005), y7 = new Year(2006), y8 = new Year(2007), y9 = new Year(2008), y10 = new Year(2009);

        Quantity q1 = new Quantity(10), q2 = new Quantity(20), q3 = new Quantity(30), q4 = new Quantity(40), q5 = new Quantity(50);
        Quantity q6 = new Quantity(60), q7 = new Quantity(70), q8 = new Quantity(80), q9 = new Quantity(90), q10 = new Quantity(100);

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
    public void countriesMinimumSum() {
        setUp();
        CountriesWithMinimumQuantitySum c = new CountriesWithMinimumQuantitySum();
        int actual = c.getMinimumSumCountries(new Quantity(810));
        int expected = 5;
        Assertions.assertEquals(expected,actual);
    }
}
