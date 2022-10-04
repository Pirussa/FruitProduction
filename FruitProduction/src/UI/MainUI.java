package UI;

import Controllers.App;

import java.util.Scanner;

public class MainUI implements Runnable {
    @Override
    public void run() {
        boolean isValidOption ;
        Scanner sc = new Scanner(System.in);

        //MAIN UI - TO make different UI's you should create UI Class on UI Package and make that it implements Runnable -
        //Same thing we did in LAPR2

        do {
            try {
                System.out.printf("Choose an option:%n1 - Load Data From CSV File.%n3 - Calculate the minimum amount of countries that produce over a certain quantity%n4 - Sort by Consecutive Years.%n");

                if (!App.getInstance().getStore().isEmpty()) {
                    System.out.printf("2 - Do X function%n 3 - Calculate the minimum amount of countries that produce over a certain quantity%n4 - Sort by Consecutive Years"); // Escrevam a vossa cena aí.

                }
                System.out.print("Option: ");
                int option = sc.nextInt();
                System.out.println();
                switch (option){ // BAD PRACTICE BUT IT SERVES THE PURPOSE
                    case 1:
                        LoadDataFromCsvFileUI loadDataFromCsvFileUI = new LoadDataFromCsvFileUI();
                        loadDataFromCsvFileUI.run();
                        isValidOption = true;
                        break;
                    case 2:
                        System.out.println("NIY");
                        isValidOption = true;
                        break;
                    case 3:
                        System.out.println("TBI");
                        isValidOption = true;
                        break;
                        //add more if you need
                    default:
                        isValidOption = false;
                }

            } catch (Exception e) {
                System.out.println("Invalid Input");
                isValidOption = false;
            }
        } while (!isValidOption);


    }

}
