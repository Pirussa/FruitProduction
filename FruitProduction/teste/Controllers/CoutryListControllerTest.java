package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class CoutryListControllerTest {

    void setUpForNormalConditions() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Country c1 = new Country("Chile"), c2 = new Country("Etiopia");Country c3 = new Country("Portugal"), c4 = new Country("Japan");

        Year y1 = new Year(2003), y2 = new Year(2005);Year y3 = new Year(2006), y4 = new Year(2000);

        Quantity q1 = new Quantity(125000), q2 = new Quantity(654651);Quantity q3 = new Quantity(200000), q4 = new Quantity(700000);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>(), yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(y1, q1);yearQuantityMap2.put(y2, q2);yearQuantityMap3.put(y3, q3);yearQuantityMap4.put(y4, q4);

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1);countryMap1.put(c2, yearQuantityMap2);countryMap1.put(c3, yearQuantityMap3);countryMap1.put(c4, yearQuantityMap4);

        Fruit f1 = new Fruit("Apples");

        fruitHarvest.put(f1, countryMap1);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsForNormalConditions() {
        setUpForNormalConditions();
        CoutryListController controller = new CoutryListController();
        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        Quantity quantity = new Quantity(124000);

        List<Map.Entry<Country, Map<Year, Quantity>>> actualMap = controller.sort(fruits.get(0), quantity);

        Map<Country, Map<Year, Quantity>> expectedMap = new LinkedHashMap<>();

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(new Year(2000), new Quantity(700000));yearQuantityMap2.put(new Year(2003), new Quantity(125000));yearQuantityMap3.put(new Year(2005), new Quantity(654651));yearQuantityMap4.put(new Year(2006), new Quantity(200000));

        expectedMap.put(new Country("Japan"), yearQuantityMap1);expectedMap.put(new Country("Chile"), yearQuantityMap2);expectedMap.put(new Country("Etiopia"), yearQuantityMap3);expectedMap.put(new Country("Portugal"), yearQuantityMap4);

        List<Map.Entry<Country, Map<Year, Quantity>>> expectedMap2 = new ArrayList<>(expectedMap.entrySet());
        List<Country>expectedCountries = controller.addCountriesToList(expectedMap2);

        List<Country> actualCountries = controller.addCountriesToList(actualMap);
        Assertions.assertEquals(expectedCountries, actualCountries);
    }

    void setUpForEqualYears() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Country c1 = new Country("Chile"), c2 = new Country("Etiopia");Country c3 = new Country("Portugal"), c4 = new Country("Japan");

        Year y1 = new Year(2000), y2 = new Year(2000);Year y3 = new Year(2000), y4 = new Year(2000);

        Quantity q1 = new Quantity(125000), q2 = new Quantity(654651);Quantity q3 = new Quantity(200000), q4 = new Quantity(700000);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>(), yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(y1, q1);yearQuantityMap2.put(y2, q2);yearQuantityMap3.put(y3, q3);yearQuantityMap4.put(y4, q4);

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1);countryMap1.put(c2, yearQuantityMap2);countryMap1.put(c3, yearQuantityMap3);countryMap1.put(c4, yearQuantityMap4);

        Fruit f1 = new Fruit("Apples");

        fruitHarvest.put(f1, countryMap1);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsForEqualYears() {
        setUpForEqualYears();
        CoutryListController controller = new CoutryListController();
        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        Quantity quantity = new Quantity(124000);

        List<Map.Entry<Country, Map<Year, Quantity>>> actualMap = controller.sort(fruits.get(0), quantity);

        Map<Country, Map<Year, Quantity>> expectedMap = new LinkedHashMap<>();

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(new Year(2000), new Quantity(700000));yearQuantityMap4.put(new Year(2000), new Quantity(125000));yearQuantityMap2.put(new Year(2000), new Quantity(654651));yearQuantityMap3.put(new Year(2000), new Quantity(200000));

        expectedMap.put(new Country("Japan"), yearQuantityMap1);expectedMap.put(new Country("Etiopia"), yearQuantityMap2);expectedMap.put(new Country("Portugal"), yearQuantityMap3);expectedMap.put(new Country("Chile"), yearQuantityMap4);

        List<Map.Entry<Country, Map<Year, Quantity>>> expectedMap2 = new ArrayList<>(expectedMap.entrySet());
        List<Country>expectedCountries = controller.addCountriesToList(expectedMap2);

        List<Country> actualCountries = controller.addCountriesToList(actualMap);
        Assertions.assertEquals(expectedCountries, actualCountries);
    }

    void setUpForRandomConditions() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Country c1 = new Country("Chile"), c2 = new Country("Etiopia");Country c3 = new Country("Portugal"), c4 = new Country("Japan");

        Year y1 = new Year(2000), y2 = new Year(2000);Year y3 = new Year(2012), y4 = new Year(2012);

        Quantity q1 = new Quantity(125000), q2 = new Quantity(654651);Quantity q3 = new Quantity(200000), q4 = new Quantity(700000);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>(), yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(y1, q1);yearQuantityMap2.put(y2, q2);yearQuantityMap3.put(y3, q3);yearQuantityMap4.put(y4, q4);

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1);countryMap1.put(c2, yearQuantityMap2);countryMap1.put(c3, yearQuantityMap3);countryMap1.put(c4, yearQuantityMap4);

        Fruit f1 = new Fruit("Apples");

        fruitHarvest.put(f1, countryMap1);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByRandomConditions() {
        setUpForRandomConditions();
        CoutryListController controller = new CoutryListController();
        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        Quantity quantity = new Quantity(124000);

        List<Map.Entry<Country, Map<Year, Quantity>>> actualMap = controller.sort(fruits.get(0), quantity);

        Map<Country, Map<Year, Quantity>> expectedMap = new LinkedHashMap<>();

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap2 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>();Map<Year, Quantity> yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(new Year(2012), new Quantity(700000));yearQuantityMap4.put(new Year(2000), new Quantity(125000));yearQuantityMap2.put(new Year(2012), new Quantity(654651));yearQuantityMap3.put(new Year(2012), new Quantity(200000));

        expectedMap.put(new Country("Etiopia"), yearQuantityMap2);expectedMap.put(new Country("Chile"), yearQuantityMap4);expectedMap.put(new Country("Japan"), yearQuantityMap1);expectedMap.put(new Country("Portugal"), yearQuantityMap3);

        List<Map.Entry<Country, Map<Year, Quantity>>> expectedMap2 = new ArrayList<>(expectedMap.entrySet());
        List<Country>expectedCountries = controller.addCountriesToList(expectedMap2);

        List<Country> actualCountries = controller.addCountriesToList(actualMap);
        Assertions.assertEquals(expectedCountries, actualCountries);
    }

    @Test
    public void sortNotPossible() {
        setUpForRandomConditions();
        CoutryListController controller = new CoutryListController();
        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        Quantity quantity = new Quantity(10000000);

        List<Map.Entry<Country, Map<Year, Quantity>>> actualMap = controller.sort(fruits.get(0), quantity);

        Map<Country, Map<Year, Quantity>> expectedMap = new LinkedHashMap<>();

        //Expected to be empty.

        List<Map.Entry<Country, Map<Year, Quantity>>> expectedMap2 = new ArrayList<>(expectedMap.entrySet());
        List<Country>expectedCountries = controller.addCountriesToList(expectedMap2);

        List<Country> actualCountries = controller.addCountriesToList(actualMap);
        Assertions.assertEquals(expectedCountries, actualCountries);
    }
}