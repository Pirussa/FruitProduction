package Controllers;

import Domain.*;
import Stores.DataStore;
import Utils.BiggestProduction;

import java.util.*;

public class BiggestAbsoluteDifferenceOfProductionController {
    private final DataStore harvest = App.getInstance().getStore();

    public Set<BiggestProduction> getBiggestAbsoluteDifference(Country country) {
        int lowest = 999999999, highest = 0, biggestDifference = 0;
        String firstYear = "", lastYear = "";

        Set<BiggestProduction> biggestDifferenceSet = new HashSet<>();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = harvest.getFruitHarvest();
        ArrayList<Quantity> productionDifferences = new ArrayList<>();

        for (Fruit fruit : fruitHarvest.keySet()) {
            Set<Year> years = fruitHarvest.get(fruit).get(country).keySet();
            for (Year year : years) {
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() < lowest) {
                    lowest += fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    lastYear = String.valueOf(year.getYear());
                }
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() > highest) {
                    highest += fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    firstYear = String.valueOf(year.getYear());
                }

            }
            biggestDifference = Math.abs(highest - lowest);
            productionDifferences.add(new Quantity(biggestDifference));
            biggestDifferenceSet.add(new BiggestProduction(firstYear + "/" + lastYear, fruit.getFruitName(), getBiggestDifference(biggestDifference, productionDifferences)));
        }
        return biggestDifferenceSet;
    }

    public int getBiggestDifference(int biggestDifferenceValues, ArrayList<Quantity> quantities) {
        for (Quantity productionDifference : quantities) {
            if (productionDifference.getQuantity() > biggestDifferenceValues) {
                biggestDifferenceValues += productionDifference.getQuantity();
            }
        }
        return biggestDifferenceValues;
    }

    public List<Country> getCountriesList(List<Country> countriesList) {
        for ( Map<Country,Map<Year,Quantity>> subMap : harvest.getFruitHarvest().values()) {
            for (Country country: subMap.keySet()) {
                if(!countriesList.contains(country)) {
                    countriesList.add(country);
                }
            }
        }
       return countriesList;
    }
}
