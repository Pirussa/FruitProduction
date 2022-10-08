package Controllers;

import Domain.Country;
import Domain.Fruit;
import Stores.DataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReadDataFromCsvFileControllerTest {


    @Test
    public void GetDataFromFile_NormalFile_DataStoreGetsProperlyFilled() {
        //ARRANGE
        String filePath = "DataToLoad/teste1.csv" ;
        DataStore dataStore = App.getInstance().getStore();
        ReadDataFromCsvFileController controller = new ReadDataFromCsvFileController();

        //ACT
        controller.getDataFromFile(filePath);
        int expectedNumberOfFruits = 2;
        int resultNumberOfFruits = dataStore.getFruitHarvest().size();


        //ASSERT
        Assertions.assertEquals(expectedNumberOfFruits,resultNumberOfFruits);


    }

    @Test
    public void GetDataFromFile_BigFile_DataStoreGetsProperlyFilled() {
        //ARRANGE
        String filePath = "DataToLoad/FAOSTAT_data_en_9-7-2022_BIG.csv" ;
        DataStore dataStore = App.getInstance().getStore();
        ReadDataFromCsvFileController controller = new ReadDataFromCsvFileController();

        //ACT
        controller.getDataFromFile(filePath);
        int expectedNumberOfFruits = 5;
        int resultNumberOfFruits = dataStore.getFruitHarvest().size();


        //ASSERT
        Assertions.assertEquals(expectedNumberOfFruits,resultNumberOfFruits);
    }

    @Test
    public void GetDataFromFile_CheckNumberOfCountries_DataStoreGetsProperlyFilled() {
        //ARRANGE
        String filePath = "DataToLoad/NumeroPaisesTeste.csv" ;
        DataStore dataStore = App.getInstance().getStore();

        ReadDataFromCsvFileController controller = new ReadDataFromCsvFileController();

        //ACT
        controller.getDataFromFile(filePath);
        int expectedNumberOfCountries = 8;
        List<Country> countriesFound = new ArrayList<>();

        for (Fruit f: dataStore.getFruitHarvest().keySet()) {
            for (Country country: dataStore.getFruitHarvest().get(f).keySet() ) {
                if (!countriesFound.contains(country)){
                    countriesFound.add(country);
                }
            }
        }
        int resultNumberOfCountries = countriesFound.size();

        //ASSERT
        Assertions.assertEquals(expectedNumberOfCountries,resultNumberOfCountries);
    }
}
