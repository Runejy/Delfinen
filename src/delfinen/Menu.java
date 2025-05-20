package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;
import delfinen.Results.ResultCompetition;
import delfinen.Results.ResultTraining;

import java.io.IOException;
import java.util.ArrayList;
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
                    showTrainer();
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

    //Træner menu
    public static void showTrainer() {
        SwimmingClub swimmingClub = new SwimmingClub();
        Trainer trainer = new Trainer();
        while (true) {
            System.out.println("=== Trainer ===");
            System.out.println("""
                    1: Add trainer
                    2: Show top 5 swimmers
                    3: Show list of trainers
                    4: Show EliteSwimmers
                    5: Back""");

            userInput = Menu.getUserNumber(4);
            switch (userInput) {
                case "1":
                    swimmingClub.addTrainer();
                    break;
                case "2":
                    swimmingClub.top5Swimmers();
                    break;
                case "3":
                    swimmingClub.listOfTrainers();
                    break;
                case "4":
                    showEliteSwimmer();
                    break;
                case "5":
                    return;
            }


        }
    }

    public static void showEliteSwimmer(){
        Database database = new Database();
        ArrayList<EliteSwimmer> eliteSwimmerArrayList =  database.getEliteMemberArrayList();

        for(EliteSwimmer eliteSwimmer : eliteSwimmerArrayList){
            System.out.println(eliteSwimmer);
        }

        while (true) {
            System.out.println("=== Elite Swimmers ===");
            System.out.println("""
                    1: Add training result to swimmer
                    2: Add competition result to swimmer
                    3: Back""");

            userInput = Menu.getUserNumber(3);
            switch (userInput) {
                case "1":
                    System.out.println("Enter the phone number of the swimmer you want to update results for:");
                    String phoneTraining = sc.nextLine().trim();

                    EliteSwimmer swimmerTraining = database.findEliteMemberByPhone(phoneTraining, eliteSwimmerArrayList);

                    if (swimmerTraining == null) {
                        System.out.println("No member found with that phone number");
                    } else {
                        ResultTraining resultTraining = swimmerTraining.createTrainingResult();
                        swimmerTraining.addTrainingResult(resultTraining);
                        System.out.println("Result has been added to " + swimmerTraining.getName() + ".");
                    }
                    break;
                case "2":
                    System.out.println("Enter the phone number of the swimmer you want to update results for:");
                    String phoneCompetetion = sc.nextLine().trim();

                    EliteSwimmer swimmerCompetetion = database.findEliteMemberByPhone(phoneCompetetion, eliteSwimmerArrayList);

                    if (swimmerCompetetion == null) {
                        System.out.println("No member found with that phone number");
                    } else {
                        ResultCompetition resultCompetition = swimmerCompetetion.createCompetitionResult();
                        swimmerCompetetion.addCompetitionResult(resultCompetition);
                        System.out.println("Result has been added to " + swimmerCompetetion.getName() + ".");
                    }
                    break;
                case "3":
                    return;
            }
        }

    }
}


