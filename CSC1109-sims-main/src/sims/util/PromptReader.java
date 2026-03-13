package sims.util;

import java.io.InputStream;
import java.util.Scanner;

public class PromptReader {

    private static Scanner scanner;
    private static InputStream lastIn;   //

    //method that takes a custom prompt string and returns user input
    public static String readWithPrompt(String prompt) {
        if (scanner == null || System.in != lastIn) {    //if scanner dont exist or System.in != lastIn
            lastIn = System.in;   // to remember current System.in
            scanner = new Scanner(System.in);
        }

        //print the custom prompt
        System.out.print(prompt + " ");

        // Don't close scanner if you plan to reuse System.in
        //scanner.close();

        return scanner.nextLine();
    }
}
