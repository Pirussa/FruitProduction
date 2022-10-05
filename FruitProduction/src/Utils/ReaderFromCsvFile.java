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

public class ReaderFromCsvFile implements IReadFromFile {

    private final DataStore dataStore = App.getInstance().getStore();
    private final File file;

    public ReaderFromCsvFile(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public boolean readDataFromFile() {
        try {
            Scanner sc = new Scanner(file);
            Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();
            boolean flag = false;
            sc.nextLine();
            String line = sc.nextLine();


            if (sc.hasNext()) {
                if (hasQuotationMarks(line)) {
                    flag = true;
                }
            }

            while (sc.hasNext()) {

                String[] lineElements;
                if (flag) {
                    lineElements = line.split("\",\"");
                } else {
                    lineElements = line.split(",");
                }

                Country country;
                Fruit fruit;
                Year year;
                Quantity quantity;


                country = new Country(lineElements[3]);
                fruit = new Fruit(lineElements[7]);
                year = new Year(Integer.parseInt(lineElements[9]));
                try {
                    quantity = new Quantity(Integer.parseInt(lineElements[11]));
                } catch (Exception e) {
                    quantity = new Quantity(0);
                }


                Map<Country, Map<Year, Quantity>> countryYearsLinkedHashMap;
                Map<Year, Quantity> yearQuantityLinkedHashMap;

                if (fruitHarvest.containsKey(fruit)) {
                    if (fruitHarvest.get(fruit).containsKey(country)) {
                        fruitHarvest.get(fruit).get(country).put(year, quantity);
                    } else {
                        yearQuantityLinkedHashMap = new LinkedHashMap<>();
                        yearQuantityLinkedHashMap.put(year, quantity);
                        fruitHarvest.get(fruit).put(country, yearQuantityLinkedHashMap);
                    }
                } else {
                    countryYearsLinkedHashMap = new LinkedHashMap<>();
                    yearQuantityLinkedHashMap = new LinkedHashMap<>();
                    yearQuantityLinkedHashMap.put(year, quantity);
                    countryYearsLinkedHashMap.put(country, yearQuantityLinkedHashMap);
                    fruitHarvest.put(fruit, countryYearsLinkedHashMap);
                }
                line = sc.nextLine();
            }

            dataStore.setFruitHarvest(fruitHarvest);


        } catch (FileNotFoundException e) {
            System.out.println("404 - File Not Found.");
            return false;
        }
        return true;

    }

    private boolean hasQuotationMarks(String testLine) {
        return testLine.contains("\"");
    }


}
