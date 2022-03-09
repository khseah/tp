package seedu.mindmymoney;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static final String PROMPT = "> ";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints logo and welcome message at startup.
     */
    public void printIntro() {
        String logo = " __  __ _         _ __  __      __  __\n"
                + "|  \\/  (_)_ _  __| |  \\/  |_  _|  \\/  |___ _ _  ___ _  _\n"
                + "| |\\/| | | ' \\/ _` | |\\/| | || | |\\/| / _ \\ ' \\/ -_) || |\n"
                + "|_|  |_|_|_||_\\__,_|_|  |_|\\_, |_|  |_\\___/_||_\\___|\\_, |\n"
                + "                           |__/                     |__/";

        System.out.print(System.lineSeparator());
        System.out.println(logo);
        System.out.println("Welcome to MindMyMoney");
        System.out.println("What can I do for you?" + System.lineSeparator());
    }

    /**
     * Prints the prompt and reads user's input.
     *
     * @return User's input.
     */
    public String readInput() {
        System.out.print(PROMPT);
        String input = in.nextLine();
        return input;
    }
}
