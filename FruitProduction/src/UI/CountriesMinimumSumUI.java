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
        int num = controller.getMinimumSumCountries(quantity);
        if (num>0) {
            System.out.printf("%nOperation Success!%n");
            System.out.printf("%nThe minimum number of countries which excedes the given quantity is: %d",num);
            System.out.println();

        }
        if(num == -1){
            System.out.printf("%nERROR:The quantity given is greater than the total sum of production%n%n");
        }else {
            System.out.printf("%n Something went wrong%n");
            System.out.println();
        }

    }
}
