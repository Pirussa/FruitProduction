package UI;

import Controllers.CountriesMinimumSumController;
import Domain.Quantity;

import java.util.Scanner;

public class CountriesMinimumSumUI implements Runnable {
    private final CountriesMinimumSumController controller = new CountriesMinimumSumController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Choose the quantity of production");
        int amount = sc.nextInt();
        Quantity quantity = new Quantity(amount);
        System.out.printf("%nThe minimum number of countries which excedes the given quantity is: ");
        if (controller.getMinimumSumCountries(quantity)) {
            System.out.printf("%nOperation Success!%n");

        } else {
            System.out.println("%n Something went wrong%n");
        }

    }
}
