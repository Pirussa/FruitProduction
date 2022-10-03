package Utils;

import Controllers.App;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadDataFromCsvFile implements IReadFromFile {

    private DataStore dataStore = App.getInstance().getStore();
    private final File file;

    public ReadDataFromCsvFile(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void readDataFromFile() {
        try {
            Scanner sc = new Scanner(file);
            Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();


            while (sc.hasNext()){


                String line = sc.nextLine();
                String [] lineElements = line.split(",");
                Country country = new Country(lineElements[3]);
                Fruit fruit =  new Fruit(lineElements[7]);
                Year year = new Year(Integer.parseInt(lineElements[9]));
                Quantity quantity = new Quantity(Integer.parseInt(lineElements[11])); // try catch e por 0
                Map<Country, Map<Year, Quantity>> countryLinkedHashMapLinkedHashMap = new LinkedHashMap<>();
                Map<Year, Quantity> yearQuantityLinkedHashMap = new LinkedHashMap<>();

                yearQuantityLinkedHashMap.put(year,quantity);
                countryLinkedHashMapLinkedHashMap.put(country,yearQuantityLinkedHashMap);
                fruitHarvest.put(fruit,countryLinkedHashMapLinkedHashMap);
                // Juntar Brazilian Nuts com a virgual +  if pela length e resolver conflito

            }

            dataStore.setFruitHarvest(fruitHarvest);




        } catch (FileNotFoundException e) {
            throw new RuntimeException("404 - File Not Found.");
        }



    }

    private static String[] removeQuotationMarks(String info[]) {
        String[] withoutQuotationMarks = new String[info.length];
        for (int i = 0; i < info.length; i++)
            withoutQuotationMarks[i] = info[i].replace(""", "");

        return withoutQuotationMarks;
    }

}
