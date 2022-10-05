package Controllers;

import Domain.Fruit;
import Domain.Country;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiggestAbsoluteDifferenceOfProductionController {
    DataStore harvest = new DataStore();

    public Map<String, Map<String, Integer>> getBiggestAbsoluteDifference(Country country) {
        int lowest = 999999999;
        int highest = 0;
        int biggestDifference = 0;
        String firstYear = null;
        String lastYear = null;
        Map<String, Map<String, Integer>> biggestDifferenceMap = new HashMap<>();
        Map<String, Integer> temp = new HashMap<>();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = harvest.getFruitHarvest();
        ArrayList<Quantity> productionDifferences = new ArrayList<>();
        for (Fruit fruit : fruitHarvest.keySet()) {
            Set<Year> years = fruitHarvest.get(fruit).get(country).keySet();
            for (Year year : years) {
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() < lowest) {
                    lowest += fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    firstYear = String.valueOf(year.getYear());
                }
                if (fruitHarvest.get(fruit).get(country).get(year).getQuantity() > highest) {
                    highest += fruitHarvest.get(fruit).get(country).get(year).getQuantity();
                    lastYear = String.valueOf(year.getYear());
                }
                biggestDifference = Math.abs(highest - lowest);
                productionDifferences.add(new Quantity(biggestDifference));
                for (Quantity productionDifference : productionDifferences) {
                    if (productionDifference.getQuantity() > biggestDifference) {
                        biggestDifference += productionDifference.getQuantity();
                    }
                }
                temp.put(fruit.getFruitName(), biggestDifference);
                biggestDifferenceMap.put(firstYear + "/" + lastYear, temp);
            }
            return biggestDifferenceMap;
        }


        return biggestDifferenceMap;
    }

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return harvest.getFruitHarvest();
    }
}
