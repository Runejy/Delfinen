package delfinen;

import delfinen.Enums.Discipline;
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
                    SwimmingClub.addNewMember();
                    break;
                case "2":
                    Database.databaseOutput();
                    break;
                case "3":
                    Database.updateDatabaseFile();
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
        while (true) {
            System.out.println("=== Trainer ===");
            System.out.println("""
                    1: Add trainer
                    2: Show top 5 training results
                    3: Show top 5 competition results
                    4: Show list of trainers
                    5: Add elite-swimmer results
                    6: Back""");

            userInput = Menu.getUserNumber(6);
            switch (userInput) {
                case "1":
                    SwimmingClub.addTrainer();
                    break;
                case "2":
                    showTrainingResuts();
                    break;
                case "3":
                    showCompetitionResults();
                    break;
                case "4":
                    Database.getTrainerList();
                    break;
                case "5":
                    showEliteSwimmer();
                    break;
                case "6":
                    return;
            }


        }
    }

    public static void showTrainingResuts(){
        while (true) {
            System.out.println("=== Show top 5 training results ===");
            System.out.println("""
                    1: FREESTYLE
                    2: BACKSTROKE
                    3: BREASTSTROKE
                    4: BUTTERFLY
                    5: Back""");

            userInput = Menu.getUserNumber(5);
            switch (userInput) {
                case "1":
                    Trainer.getTop5TrainingResults(Discipline.FREESTYLE);
                    break;
                case "2":
                    Trainer.getTop5TrainingResults(Discipline.BACKSTROKE);
                    break;
                case "3":
                    Trainer.getTop5TrainingResults(Discipline.BREASTSTROKE);
                    break;
                case "4":
                    Trainer.getTop5TrainingResults(Discipline.BUTTERFLY);
                    break;
                case "5":
                    return;
            }
        }
    }

    public static void showCompetitionResults(){
        while (true) {
            System.out.println("=== Show top 5 competition results ===");
            System.out.println("""
                    1: FREESTYLE
                    2: BACKSTROKE
                    3: BREASTSTROKE
                    4: BUTTERFLY
                    5: Back""");

            userInput = Menu.getUserNumber(5);
            switch (userInput) {
                case "1":
                    Trainer.getTop5CompetitionResults(Discipline.FREESTYLE);
                    break;
                case "2":
                    Trainer.getTop5CompetitionResults(Discipline.BACKSTROKE);
                    break;
                case "3":
                    Trainer.getTop5CompetitionResults(Discipline.BREASTSTROKE);
                    break;
                case "4":
                    Trainer.getTop5CompetitionResults(Discipline.BUTTERFLY);
                    break;
                case "5":
                    return;
            }
        }
    }

    public static void showEliteSwimmer(){
        System.out.println(String.format("%-15s %-25s %-10s %-10s %-15s %-25s %-10s %-40s ", "Phonenumber", "Name", "Gender","Status", "Training Type", "Trainer", "Team", "Discplines"));
        for(EliteSwimmer eliteSwimmer : Database.getEliteSwimmerList()){
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

                    break;
                case "2":
                    System.out.println("Enter the phone number of the swimmer you want to update results for:");
                    String phoneCompetetion = sc.nextLine().trim();

                    EliteSwimmer swimmerCompetetion = Database.findEliteMemberByPhone(phoneCompetetion);

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

    public static void addTrainingResults(){
        System.out.println("Enter the phone number of the swimmer you want to update results for:");
        String phoneTraining = sc.nextLine().trim();

        EliteSwimmer swimmerTraining = Database.findEliteMemberByPhone(phoneTraining);

        if (swimmerTraining == null) {
            System.out.println("No member found with that phone number");
        } else {
            ResultTraining resultTraining = swimmerTraining.createTrainingResult();
            swimmerTraining.addTrainingResult(resultTraining);
            System.out.println("Result has been added to " + swimmerTraining.getName() + ".");
        }
    }
}


