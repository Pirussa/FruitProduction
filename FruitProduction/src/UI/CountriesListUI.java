package UI;

import Controllers.CountriesListController;
import Domain.Quantity;

import java.util.Scanner;

public class CountriesListUI implements Runnable {
    private final CountriesListController controller = new CountriesListController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Choose the quantity of production");
        int amount = sc.nextInt();
        Quantity quantity = new Quantity(amount);
        if (controller.getMinimumSumCountries(quantity)) {
            System.out.printf("Success!%nHere are the countries:%n");

        } else {
            System.out.println("There was a problem executing this function");
        }

    }
}
