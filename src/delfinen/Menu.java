package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static String userInput;

    private static final Scanner sc = new Scanner(System.in);

    private static int userInt;

    //Method for getting userInput when user should not pick from a list
    public static String getUserString() {

        try {
            userInput = sc.next();
            userInput += sc.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e);
        }

        return userInput;
    }

    //method for getting userInput when user should pick from a list
    public static String getUserNumber(int numOptions) {

        while (true) {

            //get user input as integer, making sure they enter a number
            try {
                userInt = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter a valid number");
                sc.nextLine();
                continue;
            }

            //check if the users number is within the available options
            if (userInt > numOptions || userInt <= 0) {
                System.out.println("Enter a number between 1 and " + numOptions);
                continue;
            }

            //convert userInt to String and return it
            return String.valueOf(userInt);
        }

    }

    //method for getting any number from user when not picking from a list
    public static String getUserNumber() {

        while (true) {

            //get user input as integer, making sure they enter a number
            try {
                userInt = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter a valid number");
                sc.nextLine();
                continue;
            }

            //convert userInt to String and return it
            return String.valueOf(userInt);
        }
    }

    public static void mainMenu() {

        //Main menu
        while (true) {
            System.out.println("""
                    === Welcome to Delfinen ===
                    1: Chairman
                    2: Financier
                    3: Trainer
                    4: Back""");

            //handle userInput
            userInput = Menu.getUserNumber(4);

            //switch statement that calls other menu methods
            switch (userInput) {
                case "1":
                    //call Formand
                    showFormand();
                    break;
                case "2":
                    //call Kasser
                    showKasser();
                    break;
                case "3":
                    //call Træner
                    showTræner();
                    break;
                //close menu
                //line for method
                case "4":
                    return;
            }
        }
    }

    //Formand Menu
    public static void showFormand() {
        //line for calling classes
        Database d = new Database();
        SwimmingClub x = new SwimmingClub();

        while (true) {
            System.out.println("=== Chairman === ");
            System.out.println("""
                    1: Add member 
                    2: Show all members
                    3: Update members
                    4: Back""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    x.addNewMember();
                    break;
                case "2":
                    d.databaseOutput();
                    break;
                case "3":
                    x.updateMember();
                    break;
                case "4":
                    return;

            }

        }


    }

    //Kasser Menu
    public static void showKasser() {
        Subcription s = new Subcription();
        while (true) {
            System.out.println("=== Financier ===");
            System.out.println("""
                    1: Total Revenue
                    2: Total Revenue by membertype
                    3: Show members missing payments
                    4: Back""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    s.totalRevenue();
                    break;
                case "2":
                    s.totalRevenueByMemberType();
                    break;
                case "3":
                    s.showRestance();
                    //Method for Restance
                    break;
                case "4":
                    return;

            }
        }
    }

    //Træner menu //Ændre til engelsk til sidst.
    public static void showTræner() {
        SwimmingClub trainer = new SwimmingClub();
        while (true) {
            System.out.println("=== Trainer ===");
            System.out.println("""
                    1: Add trainer
                    2: Show top 5 swimmers
                    3: Show list of trainers
                    4: Back""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    trainer.addTrainer();
                    break;
                case "2":
                    trainer.top5Swimmers();
                    break;
                case "3":
                    trainer.listOfTrainers();
                    break;
                case "4":
                    return;
            }


        }
    }
}


