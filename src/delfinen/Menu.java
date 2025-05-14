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
                System.out.println("Indtast venligst et gyldigt nummer");
                sc.nextLine();
                continue;
            }

            //check if the users number is within the available options
            if (userInt > numOptions || userInt <= 0) {
                System.out.println("Indtast venligst et nummer mellem 1 og " + numOptions);
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
                System.out.println("Indtast venligst et gyldigt nummer");
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
                    === Velkommen til Delfinen ===
                    1: Formand
                    2: Kasser
                    3: Træner
                    4: Luk""");

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

        while (true) {
            System.out.println("=== Formand === ");
            System.out.println("""
                    1: Opret medlem  
                    2: Vis alle medlemmer
                    3: Opdater medlem
                    4: Tilbage""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    //Method to Add Member
                    break;
                case "2":
                    d.DatabaseOutput();
                    break;
                case "3":
                    //Method for updating existing members
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
            System.out.println("=== Kasser ===");
            System.out.println("""
                    1: Total Revenue
                    2: Total Revenue af medlemstype
                    3: Vis medlemmer i restance
                    4: Tilbage""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    s.totalRevenue();
                    break;
                case "2":
                    s.totalRevenueByMemberType();
                    break;
                case "3":
                    //Method for Restance
                    break;
                case "4":
                    return;

            }
        }
    }

    //Træner menu
    public static void showTræner() {
        while (true) {
            System.out.println("=== Træner ===");
            System.out.println("""
                    1:  
                    2: 
                    3: 
                    4: Tilbage""");

            userInput = Menu.getUserNumber(4);
            switch (userInput){
                case "1":
                    //add method
                    break;
                case "2":
                    //add method
                    break;
                case "3":
                    //add method
                    break;
                case "4":
                    return;
            }


        }
    }
}


