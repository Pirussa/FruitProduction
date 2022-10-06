package UI;

import Controllers.CoutryListController;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryListUI implements Runnable {
    private final CoutryListController controller = new CoutryListController();

    @Override
    public void run() {
        printMenu();

        List<Fruit> fruits = new ArrayList<>(controller.getHarvestMap().keySet());
        int index = indexFromList(fruits);

        validFruit(index, fruits);
    }

    private void printMenu() {
        System.out.printf("----------------%n|Countries List|%n----------------%n%n");
    }

    private void validFruit(int index, List<Fruit> fruits) {
        if (index >= 0) {
            Quantity quantity = newQuantity();

            List<Country> countries = controller.sort(fruits.get(index), quantity);
            printCountries(countries);
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

    private void printCountries(List<Country> countries) {
        if (!countries.isEmpty()) {
            System.out.printf("%n" + countries);
            System.out.printf("%n%n");
        }
        else System.out.printf("%nNo Country Has Produced a Higher Amount Than The Previous Quantity%n%n");
    }
}
