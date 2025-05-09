package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SwimmingClub {

    Scanner scanner = new Scanner(System.in);
    Database database = new Database();

    public void addNewMember() {
        //START
        System.out.println("REGISTER NEW MEMBER");
        System.out.println("-------------------");
        System.out.println();

        //REGISTER NAME
        String name = "";
        while (name.isBlank()) {
            System.out.println("Enter members name: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be empty");
            }

        }

        //REGISTER MAIL
        String mail = "";
        while (mail.isBlank()) {
            System.out.println("Enter members email: ");
            mail = scanner.nextLine();
            if(mail.isBlank()){
                System.out.println("Email cannot be empty");
            }
        }


        //REGISTER AGE AND MEMBERTYPE BY AGE
        LocalDate birthDate = null;
        while (birthDate == null) {
            System.out.println("Enter member's birth date (YYYY-MM-DD): ");
            String birthDateInput = scanner.nextLine();
            try {
                birthDate = LocalDate.parse(birthDateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        //Skal dette visse til formand når oprettelse dannes?
        MemberType memberType;
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            memberType = MemberType.JUNIOR;
        } else if (age >= 60) {
            memberType = MemberType.RETIREE;
        } else {
            memberType = MemberType.SENIOR;
        }


        //REGISTER GENDER
        String gender = null;
        while (gender == null) {
            System.out.println("Enter members gender: (F for Female, M for Male, O for Other");
            String genderInput = scanner.nextLine();

            if (genderInput.equalsIgnoreCase("F")) {
                gender = "Female";
            } else if (genderInput.equalsIgnoreCase("M")) {
                gender = "Male";
            } else if (genderInput.equalsIgnoreCase("O")) {
                gender = "Other";
            } else {
                System.out.println("Invalid input. Please enter F (female) or M (male) or O (other).");
            }
        }


        //REGISTER MEMBER ACTIVITY (ACTIVE OR PASSIVE)
        MemberActivity memberActivity = null;
        while (memberActivity == null) {
            System.out.println("Is member active or passive? Type A for Active or P for Passive.");
            String activityInput = scanner.nextLine();

            if (activityInput.equalsIgnoreCase("A")) {
                memberActivity = MemberActivity.ACTIVE;
            } else if (activityInput.equalsIgnoreCase("P")) {
                memberActivity = MemberActivity.PASSIVE;
            } else {
                System.out.println("Invalid input. Please enter A (active) or P (passive).");
            }
        }

        //REGISTER ACTIVITY TYPE
        TrainingType trainingType = null;
        while (trainingType == null) {
            System.out.println("Is member an Elite swimmer? Type Y for Yes or N for No.");
            String trainingInput = scanner.nextLine();
            if (trainingInput.equalsIgnoreCase("Y")) {
                trainingType = TrainingType.COMPETITION;
            } else if (trainingInput.equalsIgnoreCase("N")) {
                trainingType = TrainingType.CASUAL;
            } else {
                System.out.println("Invalid input. Please enter Y (yes) or N (no).");
            }
        }
        Member newMember = new Member(name, age, gender, mail, memberActivity, trainingType);
        database.inputNewMemberData(newMember);
        System.out.println("=======================================");
        System.out.println("   New Member Added to the Club");
        System.out.println("=======================================");
        System.out.printf ("%-20s: %s%n", "Name", name);
        System.out.printf ("%-20s: %d%n", "Age", age);
        System.out.printf ("%-20s: %s%n", "Gender", gender);
        System.out.printf ("%-20s: %s%n", "Mail", mail);
        System.out.printf ("%-20s: %s%n", "Member Type", memberType);
        System.out.printf ("%-20s: %s%n", "Member Activity", memberActivity);
        System.out.printf ("%-20s: %s%n", "Training Type", trainingType);
        System.out.println("=======================================");


        //Her skal vi også tilføje den til LISTER
        //Add to list
        // (if trainingType == Competition) add to ELITE LIST
    }


        //MARIA LAVER updateMEMBERs
    public void updateMember(){


    }

    public void totalMembers(){

    }

    public void totalMembersByMemberType(){

    }

    public void listOfTrainers(){

    }

    public void top5Swimmers(){

    }
}
