package UI;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Miscellaneous.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CountryListUI implements Runnable {
    private final LoadDataFromCsvFileUI.CountryListController controller = new LoadDataFromCsvFileUI.CountryListController();

    @Override
    public void run() {
        printMenu();

        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        int index = indexFromList(fruits);

        validFruit(index, fruits);
    }

    private void printMenu() {
        System.out.printf("------------------%n| Countries List |%n------------------%n%n");
    }

    private void validFruit(int index, List<Fruit> fruits) {
        if (index >= 0) {
            Quantity quantity = newQuantity();

            List<Map.Entry<Country, Map<Year, Quantity>>> countries = controller.sort(fruits.get(index), quantity);
            printCountries(countries, fruits, index);
        }
    }

    private int indexFromList(List<Fruit> fruits) {
        return Utils.showAndSelectIndex(fruits, "Select a Fruit: ");
    }

    private Quantity newQuantity() {
        Scanner read = new Scanner(System.in);
        System.out.printf("%nIntroduce a Quantity: ");
        int quantity = read.nextInt();
        return controller.newQuantity(quantity);
    }

    private void printCountries(List<Map.Entry<Country, Map<Year, Quantity>>> countries, List<Fruit> fruits, int index) {
        if (!countries.isEmpty()) {
            int count = 1;
            System.out.println();
            System.out.printf("[%s]%n%n", fruits.get(index));
            for (Map.Entry<Country, Map<Year, Quantity>> country : countries) {
                for (Map.Entry<Year, Quantity> yearQuantity : country.getValue().entrySet()) {
                    System.out.printf("%d: %s in %s, %s tonnes%n", count, country.getKey(), yearQuantity.getKey(), yearQuantity.getValue());
                    System.out.println();
                    count++;
                }
            }
            System.out.println();
        }
        else System.out.printf("%nNo Country Has Produced a Higher Amount Than The Previous Quantity.%n%n");
    }
}
