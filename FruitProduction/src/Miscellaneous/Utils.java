package Miscellaneous;

import java.io.*;
import java.util.*;

public class Utils {

    static public int selectsIndex(List list) {
        String input;
        int value = -1;
        do {
            try {
                input = Utils.readLineFromConsole("Type your option: ");
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Shows the chosen list (showList) and let the user choose an option
     *
     * @param list   A chosen list
     * @param header A chosen header
     * @return Integer - the option chosen by the user
     */
    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);
        System.out.println();
        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }
}
