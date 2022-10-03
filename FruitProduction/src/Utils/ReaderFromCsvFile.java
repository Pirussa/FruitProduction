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
                String[] lineElements = line.split(",");

                if (flag) {
                    lineElements = removeQuotationMarks(lineElements);
                }

                Country country;
                Fruit fruit;
                Year year;
                Quantity quantity;

                if (lineElements.length == 14) {
                    country = new Country(lineElements[3]);
                    fruit = new Fruit(lineElements[7]);
                    year = new Year(Integer.parseInt(lineElements[9]));
                    try {
                        quantity = new Quantity(Integer.parseInt(lineElements[11]));
                    } catch (Exception e) {
                        quantity = new Quantity(0);
                    }

                } else {
                    country = new Country(lineElements[3]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(lineElements[7]).append(",").append(lineElements[8]);
                    fruit = new Fruit(sb.toString());
                    year = new Year(Integer.parseInt(lineElements[10]));

                    try {
                        quantity = new Quantity(Integer.parseInt(lineElements[12]));
                    } catch (Exception e) {
                        quantity = new Quantity(0);
                    }
                }


                Map<Country, Map<Year, Quantity>> countryLinkedHashMapLinkedHashMap = new LinkedHashMap<>();
                Map<Year, Quantity> yearQuantityLinkedHashMap = new LinkedHashMap<>();

                yearQuantityLinkedHashMap.put(year, quantity);
                countryLinkedHashMapLinkedHashMap.put(country, yearQuantityLinkedHashMap);
                fruitHarvest.put(fruit, countryLinkedHashMapLinkedHashMap);


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
        String[] lineElements = testLine.split(",");
        try {
            String[] teste = removeQuotationMarks(lineElements);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static String[] removeQuotationMarks(String[] info) {
        String[] withoutQuotationMarks = new String[info.length];
        for (int i = 0; i < info.length; i++)
            withoutQuotationMarks[i] = info[i].replace("\"", "");

        return withoutQuotationMarks;
    }

}
