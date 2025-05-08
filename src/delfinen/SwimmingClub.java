package delfinen;
import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;

import java.time.LocalDate;
import java.util.Scanner;

public class SwimmingClub {

    Scanner scanner = new Scanner(System.in);


    public void addNewMember() {
        //REGISTER NAME
        System.out.println("REGISTER NEW MEMBER");
        System.out.println("-------------------");
        System.out.println();
        System.out.println("Enter members name: ");
        String name = scanner.nextLine();

        //REGISTER AGE
        System.out.println("Enter members birth date: ");
        // ENTER AGE BY LOCAL DATE???

        //REGISTER GENDER
        String gender = null;
        while (gender == null) {
        System.out.println("Enter members gender: (F for Female, M for Male, O for Other");
        String gender = scanner.nextLine();

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
    }




        //maria was here
    public void updateMember(){

    }

    public void listOfMembers(){
        String path = "";
        String line;
        try {
            
        }
        }
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
