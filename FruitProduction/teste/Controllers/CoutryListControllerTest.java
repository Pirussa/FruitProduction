package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CoutryListControllerTest {

    void setUpForNormalConditions() {
        DataStore fruitStore = App.getInstance().getStore();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

        Country c1 = new Country("Chile"), c2 = new Country("Etiopia");
        Country c3 = new Country("Portugal"), c4 = new Country("Japan");

        Year y1 = new Year(2000), y2 = new Year(2005);
        Year y3 = new Year(2012), y4 = new Year(2021);

        Quantity q1 = new Quantity(234312), q2 = new Quantity(654651);
        Quantity q3 = new Quantity(6505), q4 = new Quantity(68181);

        Map<Year, Quantity> yearQuantityMap1 = new LinkedHashMap<>(), yearQuantityMap2 = new LinkedHashMap<>();
        Map<Year, Quantity> yearQuantityMap3 = new LinkedHashMap<>(), yearQuantityMap4 = new LinkedHashMap<>();

        yearQuantityMap1.put(y1, q1);
        yearQuantityMap2.put(y2, q2);
        yearQuantityMap3.put(y3, q3);
        yearQuantityMap4.put(y4, q4);

        Map<Country, Map<Year, Quantity>> countryMap1 = new LinkedHashMap<>();
        countryMap1.put(c1, yearQuantityMap1);
        countryMap1.put(c2, yearQuantityMap2);
        countryMap1.put(c3, yearQuantityMap3);
        countryMap1.put(c4, yearQuantityMap4);

        Fruit f1 = new Fruit("Apples");

        fruitHarvest.put(f1, countryMap1);

        fruitStore.setFruitHarvest(fruitHarvest);
    }

    @Test
    public void sortByConsecutiveYearsForNormalConditions() {
        setUpForNormalConditions();

        CoutryListController controller = new CoutryListController();
        List<Country> actualMap = controller.sortByConsecutiveYears(new Fruit("Apples"));
        Map<Country, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put(new Country("Portugal"), 2);
        expectedMap.put(new Country("Greece"), 1);
        expectedMap.put(new Country("France"), 3);

        Assertions.assertEquals(actualMap, expectedMap);
    }

}