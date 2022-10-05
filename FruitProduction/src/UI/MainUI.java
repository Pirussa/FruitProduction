package UI;

import Controllers.App;

import java.util.Scanner;

public class MainUI implements Runnable {
    @Override
    public void run() {
        boolean keepCycle;
        Scanner sc = new Scanner(System.in);

        //MAIN UI - TO make different UI's you should create UI Class on UI Package and make that it implements Runnable -
        //Same thing we did in LAPR2

        do {
            try {
                System.out.printf("Choose an option:%n%n1 - Load Data From CSV File.%n");

                if (!App.getInstance().getStore().isEmpty()) {
                    System.out.printf("2 - Get a List of countries (Production in year > Q). %n3 - Calculate the minimum amount of countries that produce over a certain quantity%n4 - Sort by Consecutive Years"); // Escrevam a vossa cena aí.

                }
                System.out.printf("%n0 - Exit%n%n");
                System.out.print("Option: ");
                int option = sc.nextInt();
                System.out.println();
                switch (option) { // BAD PRACTICE BUT IT SERVES THE PURPOSE
                    case 0 -> {
                        keepCycle = false;
                        System.out.println("Goodbye!");
                        Thread.sleep(500);
                        Runtime.getRuntime().exit(0);
                    }
                    case 1 -> {
                        LoadDataFromCsvFileUI loadDataFromCsvFileUI = new LoadDataFromCsvFileUI();
                        loadDataFromCsvFileUI.run();
                        keepCycle = true;
                    }
                    case 2 -> {
                        CountryListUI countryListUI = new CountryListUI();
                        countryListUI.run();
                        keepCycle = true;
                    }
                    case 3 -> {
                        CountriesMinimumSumUI countriesMinimumSumUI = new CountriesMinimumSumUI();
                        countriesMinimumSumUI.run();
                        keepCycle = true;
                    }
                    case 4 -> {
                        SortByConsecutiveYearUI sortByConsecutiveYearUI = new SortByConsecutiveYearUI();
                        sortByConsecutiveYearUI.run();
                        keepCycle = true;
                    }
                    //add more if you need
                    default -> keepCycle = true;
                }

            } catch (Exception e) {
                System.out.println("Invalid Input");
                keepCycle = false;
            }
        } while (keepCycle);

        SortByConsecutiveYearUI sortByConsecutiveYearUI = new SortByConsecutiveYearUI();
        sortByConsecutiveYearUI.run();

    }

}
