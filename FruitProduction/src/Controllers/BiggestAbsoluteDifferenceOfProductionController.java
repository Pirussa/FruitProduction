package Controllers;

import Domain.*;
import Stores.DataStore;
import Utils.BiggestProduction;

import java.util.*;

public class BiggestAbsoluteDifferenceOfProductionController {
    private final DataStore harvest = App.getInstance().getStore();

    public Set<BiggestProduction> getBiggestAbsoluteDifference(Country country) {

        int firstYear = 0, lastYear = 0, biggestDifference = 0;

        Set<BiggestProduction> biggestDifferenceSet = new LinkedHashSet<>();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = harvest.getFruitHarvest();

        for (Fruit fruit : fruitHarvest.keySet()) {
            int lowest = 999999999, highest = 0;
            for (Year year : fruitHarvest.get(fruit).get(country).keySet()) {
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() < lowest) {
                    lowest = fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    firstYear = year.getYear();
                }
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() > highest) {
                    highest = fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    lastYear = year.getYear();
                }
            }
            if (firstYear > lastYear) {
                int aux = lastYear;
                lastYear = firstYear;
                firstYear = aux;
            }
            biggestDifference = Math.abs(highest - lowest);
            biggestDifferenceSet.add(new BiggestProduction(firstYear + "/" + lastYear, fruit.getFruitName(), biggestDifference));
        }
        return biggestDifferenceSet;
    }

    public List<Country> getCountriesList(List<Country> countriesList) {
        for (Map<Country, Map<Year, Quantity>> subMap : harvest.getFruitHarvest().values()) {
            for (Country country : subMap.keySet()) {
                if (!countriesList.contains(country)) {
                    countriesList.add(country);
                }
            }
        }
        return countriesList;
    }
}
