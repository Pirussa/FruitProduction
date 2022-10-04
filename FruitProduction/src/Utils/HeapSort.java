package Utils;

import Controllers.App;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.util.Map;

public class HeapSort implements ISorter {

    private final DataStore dataStore = App.getInstance().getStore();
    @Override
    public void sort() {
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitCountryYearQuantity = dataStore.getFruitHarvest();

                for (Map<Year, Quantity> quantity : fruitCountryYearQuantity.get(0).values()) {

                }
            }
        }
