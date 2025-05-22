package delfinen;

import delfinen.Enums.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SwimmingClub {

    private static Scanner scanner = new Scanner(System.in);

    public static void addNewMember() {
        //START
        System.out.println("===================================");
        System.out.println("         REGISTER NEW MEMBER");
        System.out.println("===================================");
        System.out.println();

        //REGISTER PHONENUMBER
        String phoneNumber = "";
        while (phoneNumber.isBlank()) {
            System.out.println("Enter members phone number: ");

            phoneNumber = scanner.nextLine();

            while (phoneNumber == null && (!phoneNumber.matches("\\d{8}") || !phoneNumber.matches("(\\d{2})(\\d{2})(\\d{2})(\\d{2})"))) {
                System.out.println("Error: Invalid phone number. Must be 8 digits.");
                System.out.print("Please enter a valid phone number: ");
                phoneNumber = scanner.nextLine();
            }

            phoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");

        }
        //REGISTER NAME
        String name = "";
        while (name.isBlank()) {
            System.out.println("Enter members name: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be empty.");
            }

        }

        //REGISTER MAIL
        String mail = "";
        while (mail.isBlank()) {
            System.out.println("Enter members e-mail: ");
            mail = scanner.nextLine();
            if (mail.isBlank()) {
                System.out.println("E-mail cannot be empty.");
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

        //ADD MEMBER
        Member newMember = new Member(phoneNumber, name, age, gender, mail, memberActivity, trainingType);

        //Tjekker om member kan tilføjes
        if(!Database.addNewMember(newMember)){
            System.out.println("Member could not be added");
        }

        //ADD MEMBER IF ELITE
        if (trainingType == TrainingType.COMPETITION) {

            //AGE ASSIGNS TEAM WITH TERNARY

            Team team = (age < 18) ? Team.JUNIOR : Team.SENIOR;

            //ASSIGN DISCIPLINES TO ELITE SWIMMER

            HashMap<Discipline, Discipline> disciplines = assignDisciplines();

            //ASSIGN TRAINER TO ELITE SWIMMER

            if (Database.getTrainerList().isEmpty()) {
                System.out.println("No trainers available. Please add trainers first.");
                return;
            }

            System.out.println("Select a trainer from the list:");
            for (int i = 0; i < Database.getTrainerList().size(); i++) {
                System.out.printf("%d - %s%n", i + 1, Database.getTrainerList().get(i));
            }
            int trainerChoice = -1;
            while (trainerChoice < 1 || trainerChoice > Database.getTrainerList().size()) {
                System.out.print("Enter the number of the trainer to assign: ");
                String input = scanner.nextLine();
                try {
                    trainerChoice = Integer.parseInt(input);
                    if (trainerChoice < 1 || trainerChoice > Database.getTrainerList().size()) {
                        System.out.println("Invalid choice. Please select a number from the list.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            Trainer trainer = Database.getTrainerList().get(trainerChoice - 1);

            EliteSwimmer eliteSwimmer = new EliteSwimmer(phoneNumber, name, age, gender, mail, memberActivity, trainingType, team, disciplines, trainer);

            //Tjekker om member kan tilføjes
            if(!Database.addNewEliteSwimmer(eliteSwimmer)){
                System.out.println("Elite swimmer could not be added");
            } else{
                System.out.println("=======================================");
                System.out.println("   New Member Added to the Club");
                System.out.println("=======================================");
                System.out.printf("%-20s: %s%n", "Phone number", phoneNumber);
                System.out.printf("%-20s: %s%n", "Name", name);
                System.out.printf("%-20s: %d%n", "Age", age);
                System.out.printf("%-20s: %s%n", "Gender", gender);
                System.out.printf("%-20s: %s%n", "Mail", mail);
                System.out.printf("%-20s: %s%n", "Member Activity", memberActivity);
                System.out.printf("%-20s: %s%n", "Member Type", memberType);
                System.out.printf("%-20s: %s%n", "Training Type", trainingType);
                System.out.println("=======================================");
            }
        }
    }





    public static void changeMemberData() {
        Scanner nextIntScanner = new Scanner(System.in);
        Scanner nextLineScanner = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("         UPDATE MEMBER             ");
        System.out.println("===================================");
        System.out.print("Enter the phone number of the member you want to update: ");

        String inputPhoneNumber;

        Member member = null;


        //looper indtil man får et tlf-nummer
        while(member == null){
            inputPhoneNumber = nextLineScanner.nextLine();


            for (Member memberToUpdate : Database.getMemberList()) {
                if (memberToUpdate.getPhoneNumber().equals(inputPhoneNumber)) {
                    member = memberToUpdate;
                }
            }

            if (member == null) {
                System.out.println("No member found with phone number: " + inputPhoneNumber);
            }
        }

        //Vælg hvad der skal opdateres
        //Menu for både member og elite svømmer
        //CHOOSE UPDATE ACTION

        System.out.println(member);
        if(member.getTrainingType().equals(TrainingType.CASUAL)){
            System.out.println("CASUAL");
            memberUpdateActions(member);
        } else if(member.getTrainingType().equals(TrainingType.COMPETITION)){
            System.out.println("COMPETITION");
            eliteSwimmerUpdateActions(member);
        }
    }

    private static void memberUpdateActions(Member member){
        Scanner nextLineScanner = new Scanner(System.in);
        Scanner nextIntScanner = new Scanner(System.in);

        String rowIdentificer = member.getPhoneNumber();

        while(true){
            System.out.println();
            System.out.println("===================================");
            System.out.println("       SELECT WHAT TO UPDATE       ");
            System.out.println("===================================");
            System.out.println("1: Phone number");
            System.out.println("2: Name");
            System.out.println("3: Age");
            System.out.println("4: Gender");
            System.out.println("5: Mail");
            System.out.println("6: Member Activity");
            System.out.println("7: Training Type");
            System.out.println("8: Remove Member");
            System.out.println("9: Back to Menu");
            System.out.print("Enter choice (1–9): ");

            int updateChoice = nextIntScanner.nextInt();

            switch (updateChoice) {
                //Nyt tlf-nummer
                case 1 -> {
                    String phoneNumber = "";

                    phoneNumber = nextLineScanner.nextLine();

                    //Køre så længe at phoneNumber ikke indeholder en String af 8 cifre
                    while (!phoneNumber.matches("\\d{8}")) {
                        System.out.println("Members current phone number: " + member.getPhoneNumber());
                        System.out.println("Enter members new phone number (8 digits): ");

                        phoneNumber = nextLineScanner.nextLine();

                        if (!phoneNumber.matches("\\d{8}")) {
                            System.out.println("Invalid phone number. It must contain exactly 8 digits.");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Telephone", phoneNumber);
                    System.out.println("Phone number has been updated to: " + member.getPhoneNumber());
                }
                //Nyt navn
                case 2 -> {
                    System.out.println("Members name: " + member.getName());
                    System.out.println("Enter new name: ");

                    String newName = nextLineScanner.nextLine();

                    member.setName(newName);

                    Database.changeMemberData(rowIdentificer, "Name", newName);

                    System.out.println("Name has been updated!");
                    System.out.println("Members name: " + newName);
                }
                //Ny fødselsdag
                case 3 -> {
                    System.out.println("Enter new birth date (YYYY-MM-DD): ");

                    String birthDateInput = nextLineScanner.nextLine();

                    try {
                        LocalDate birthDate = LocalDate.parse(birthDateInput);

                        int age = Period.between(birthDate, LocalDate.now()).getYears();

                        MemberType memberType;

                        if (age < 18) {
                            memberType = MemberType.JUNIOR;
                        } else if (age >= 60) {
                            memberType = MemberType.RETIREE;
                        } else {
                            memberType = MemberType.SENIOR;
                        }

                        //Ændrer member data i database klassen
                        Database.changeMemberData(rowIdentificer, "Age", String.valueOf(age));
                        Database.changeMemberData(rowIdentificer, "Birth Date", birthDate.toString());

                        System.out.println("Birth date has been updated!");

                        System.out.println("Members birthday: " + birthDate);
                        System.out.println("Members age: " + age);
                        System.out.println("Member type: " + memberType);

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
                //Nyt køn
                case 4 -> {
                    while (true) {
                        System.out.println(member.getName() + " current gender: " + member.getGender());

                        System.out.println("Enter members new gender: (F for Female, M for Male, O for Other");

                        String genderInput = nextLineScanner.nextLine();

                        //Ændrer member data i database klassen, baseret på genderInput
                        switch (genderInput.toUpperCase()) {
                            case "F" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Female");
                                System.out.println("Gender updated to Female.");
                            }
                            case "M" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Male");
                                System.out.println("Gender updated to Male.");
                            }
                            case "O" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Other");
                                System.out.println("Gender updated to Other.");
                            }
                            default -> {
                                System.out.println("Invalid input. Use 'F' for Female, 'M' for Male, 'O' for Other");
                                continue;
                            }
                        }
                        break;
                    }
                }
                //Ny mail
                case 5 -> {
                    String mail = "";

                    //Køre så længe at mail er en tom String
                    while (mail.isBlank()) {
                        System.out.println("Members current email: " + member.getMail());
                        System.out.println("Enter member's new e-mail: ");

                        mail = nextLineScanner.nextLine();

                        if (mail.isBlank()) {
                            System.out.println("E-mail cannot be empty.");
                        } else {
                            //Ændrer member data
                            Database.changeMemberData(rowIdentificer, "Mail", mail);
                            System.out.println("Mail has been updated to: " + member.getMail());
                        }
                    }
                }

                //Ny member activity
                case 6 -> {
                    MemberActivity memberActivity = null;

                    while (memberActivity == null) {
                        System.out.println("Is member active or passive? Type A for Active or P for Passive.");

                        String activityInput = nextLineScanner.nextLine();

                        if (activityInput.equalsIgnoreCase("A")) {
                            memberActivity = MemberActivity.ACTIVE;
                        } else if (activityInput.equalsIgnoreCase("P")) {
                            memberActivity = MemberActivity.PASSIVE;
                        } else {
                            System.out.println("Invalid input. Please enter A (active) or P (passive).");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Member Activity", memberActivity.toString());
                }
                //Ny training type
                case 7 -> {
                    TrainingType trainingType = null;

                    while (trainingType == null) {
                        System.out.println("Is member an Elite swimmer? Type Y for Yes or N for No.");

                        String trainingInput = nextLineScanner.nextLine();

                        if (trainingInput.equalsIgnoreCase("Y")) {
                            trainingType = TrainingType.COMPETITION;
                        } else if (trainingInput.equalsIgnoreCase("N")) {
                            trainingType = TrainingType.CASUAL;
                        } else {
                            System.out.println("Invalid input. Please enter Y (yes) or N (no).");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Training Type",trainingType.toString());
                }
                //Fjern medlem
                case 8 -> {
                    System.out.println("??? DO YOU WANT TO REMOVE " + member.getName() + " ???");
                    System.out.println("If yes, pres Y and if no, pres N");

                    String input = nextLineScanner.nextLine();

                    if (input.equalsIgnoreCase("y")) {
                        Database.changeMemberData(rowIdentificer, "Remove Member" ,"");
                        System.out.println(member.getName() + " has now been removed");

                        return;
                    } else if (input.equalsIgnoreCase("n")) {
                        System.out.println("Member has not been removed from the list");
                    }
                }
                //Gå tilbage/stop metoden
                case 9 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void eliteSwimmerUpdateActions(Member member){
        Scanner nextLineScanner = new Scanner(System.in);
        Scanner nextIntScanner = new Scanner(System.in);

        String rowIdentificer = member.getPhoneNumber();

        while(true){
            System.out.println();
            System.out.println("===================================");
            System.out.println("       SELECT WHAT TO UPDATE       ");
            System.out.println("===================================");
            System.out.println("1: Phone number");
            System.out.println("2: Name");
            System.out.println("3: Age");
            System.out.println("4: Gender");
            System.out.println("5: Mail");
            System.out.println("6: Member Activity");
            System.out.println("7: Training Type");
            System.out.println("8: Trainer");
            System.out.println("9: Team");
            System.out.println("10: Discipline");
            System.out.println("11: Remove Member");
            System.out.println("12: Back to Menu");
            System.out.print("Enter choice (1–12): ");

            int updateChoice = nextIntScanner.nextInt();

            switch(updateChoice){
                //Nyt tlf-nummer
                case 1 -> {
                    String phoneNumber = "";

                    //Køre så længe at phoneNumber ikke indeholder en String af 8 cifre
                    while (!phoneNumber.matches("\\d{8}")) {
                        System.out.println("Members current phone number: " + member.getPhoneNumber());
                        System.out.println("Enter members new phone number (8 digits): ");

                        phoneNumber = nextLineScanner.nextLine();

                        if (!phoneNumber.matches("\\d{8}")) {
                            System.out.println("Invalid phone number. It must contain exactly 8 digits.");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Telephone", phoneNumber);
                    System.out.println("Phone number has been updated to: " + member.getPhoneNumber());
                }
                //Nyt navn
                case 2 -> {
                    System.out.println("Members name: " + member.getName());
                    System.out.println("Enter new name: ");

                    String newName = nextLineScanner.nextLine();

                    member.setName(newName);

                    Database.changeMemberData(rowIdentificer, "Name", newName);

                    System.out.println("Name has been updated!");
                    System.out.println("Members name: " + newName);
                }
                //Ny fødselsdag
                case 3 -> {
                    System.out.println("Enter new birth date (YYYY-MM-DD): ");

                    String birthDateInput = nextLineScanner.nextLine();

                    try {
                        LocalDate birthDate = LocalDate.parse(birthDateInput);

                        int age = Period.between(birthDate, LocalDate.now()).getYears();

                        MemberType memberType;

                        if (age < 18) {
                            memberType = MemberType.JUNIOR;
                        } else if (age >= 60) {
                            memberType = MemberType.RETIREE;
                        } else {
                            memberType = MemberType.SENIOR;
                        }

                        //Ændrer member data i database klassen
                        Database.changeMemberData(rowIdentificer, "Age", String.valueOf(age));
                        Database.changeMemberData(rowIdentificer, "Birth Date", birthDate.toString());

                        System.out.println("Birth date has been updated!");

                        System.out.println("Members birthday: " + birthDate);
                        System.out.println("Members age: " + age);
                        System.out.println("Member type: " + memberType);

                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
                //Nyt køn
                case 4 -> {
                    while (true) {
                        System.out.println(member.getName() + " current gender: " + member.getGender());

                        System.out.println("Enter members new gender: (F for Female, M for Male, O for Other");

                        String genderInput = nextLineScanner.nextLine();

                        //Ændrer member data i database klassen, baseret på genderInput
                        switch (genderInput.toUpperCase()) {
                            case "F" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Female");
                                System.out.println("Gender updated to Female.");
                            }
                            case "M" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Male");
                                System.out.println("Gender updated to Male.");
                            }
                            case "O" -> {
                                Database.changeMemberData(rowIdentificer, "Gender", "Other");
                                System.out.println("Gender updated to Other.");
                            }
                            default -> {
                                System.out.println("Invalid input. Use 'F' for Female, 'M' for Male, 'O' for Other");
                                continue;
                            }
                        }
                        break;
                    }
                }
                //Ny mail
                case 5 -> {
                    String mail = "";

                    //Køre så længe at mail er en tom String
                    while (mail.isBlank()) {
                        System.out.println("Members current email: " + member.getMail());
                        System.out.println("Enter member's new e-mail: ");

                        mail = nextLineScanner.nextLine();

                        if (mail.isBlank()) {
                            System.out.println("E-mail cannot be empty.");
                        } else {
                            //Ændrer member data
                            Database.changeMemberData(rowIdentificer, "Mail", mail);
                            System.out.println("Mail has been updated to: " + member.getMail());
                        }
                    }
                }

                //Ny member activity
                case 6 -> {
                    MemberActivity memberActivity = null;

                    while (memberActivity == null) {
                        System.out.println("Is member active or passive? Type A for Active or P for Passive.");

                        String activityInput = nextLineScanner.nextLine();

                        if (activityInput.equalsIgnoreCase("A")) {
                            memberActivity = MemberActivity.ACTIVE;
                        } else if (activityInput.equalsIgnoreCase("P")) {
                            memberActivity = MemberActivity.PASSIVE;
                        } else {
                            System.out.println("Invalid input. Please enter A (active) or P (passive).");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Member Activity", memberActivity.toString());
                }
                //Ny training type
                case 7 -> {
                    TrainingType trainingType = null;

                    while (trainingType == null) {
                        System.out.println("Is member an Elite swimmer? Type Y for Yes or N for No.");

                        String trainingInput = nextLineScanner.nextLine();

                        if (trainingInput.equalsIgnoreCase("Y")) {
                            trainingType = TrainingType.COMPETITION;
                        } else if (trainingInput.equalsIgnoreCase("N")) {
                            trainingType = TrainingType.CASUAL;
                        } else {
                            System.out.println("Invalid input. Please enter Y (yes) or N (no).");
                        }
                    }

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Training Type",trainingType.toString());
                }
                //Ny Trainer
                case 8 -> {
                    if (Database.getTrainerList().isEmpty()) {
                        System.out.println("No trainers available. Please add trainers first.");
                        return;
                    }

                    System.out.println("Select a trainer from the list:");
                    for (int i = 0; i < Database.getTrainerList().size(); i++) {
                        System.out.printf("%d - %s%n", i + 1, Database.getTrainerList().get(i));
                    }
                    int trainerChoice = -1;
                    while (trainerChoice < 1 || trainerChoice > Database.getTrainerList().size()) {
                        System.out.print("Enter the number of the trainer to assign: ");
                        String input = scanner.nextLine();
                        try {
                            trainerChoice = Integer.parseInt(input);
                            if (trainerChoice < 1 || trainerChoice > Database.getTrainerList().size()) {
                                System.out.println("Invalid choice. Please select a number from the list.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }

                    Trainer trainer = Database.getTrainerList().get(trainerChoice - 1);

                    //Ændrer member data
                    Database.changeMemberData(rowIdentificer, "Trainer",trainer.getName());
                }
                //Nyt team
                case 9 -> {
                    System.out.println("Choose team for the elite swimmer");

                    System.out.println();
                    System.out.println("1: SENIOR");
                    System.out.println("2: JUNIOR");

                    switch(nextIntScanner.nextInt()){
                        case 1 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Team", "SENIOR");
                        }
                        case 2 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Team", "JUNIOR");
                        }
                        default -> {
                            System.out.println("ERROR: no team was chosen");
                        }
                    }
                }
                //Ny discipline
                case 10 -> {
                    System.out.println("Choose new discipline for the elite swimmer");

                    System.out.println();
                    System.out.println("1: FREESTYLE");
                    System.out.println("2: BACKSTROKE");
                    System.out.println("3: BREASTSTROKE");
                    System.out.println("4: BUTTERFLY");

                    switch(nextIntScanner.nextInt()){
                        case 1 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Discipline", "FREESTYLE");
                        }
                        case 2 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Discipline", "BACKSTROKE");
                        }
                        case 3 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Discipline", "BREASTSTROKE");
                        }
                        case 4 -> {
                            Database.changeMemberData(member.getPhoneNumber(), "Discipline", "BUTTERFLY");
                        }
                        default -> {
                            System.out.println("ERROR: no team was chosen");
                        }
                    }
                }
                //Fjern medlem
                case 11 -> {
                    System.out.println("??? DO YOU WANT TO REMOVE " + member.getName() + " ???");
                    System.out.println("If yes, pres Y and if no, pres N");

                    String input = nextLineScanner.nextLine();

                    if (input.equalsIgnoreCase("y")) {
                        Database.changeMemberData(rowIdentificer, "Remove Member" ,"");
                        System.out.println(member.getName() + " has now been removed");
                        return;
                    } else if (input.equalsIgnoreCase("n")) {
                        System.out.println("Member has not been removed from the list");
                    }
                }
                //Gå tilbage/stop metoden
                case 12 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addTrainer() {
        System.out.println("===================================");
        System.out.println("         REGISTER NEW TRAINER");
        System.out.println("===================================");
        System.out.println();

        String name = "";
        while (name.isBlank()) {
            System.out.println("Enter trainers name: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Name cannot be empty.");
            }

        }
        Trainer trainer = new Trainer(name);
        Database.inputNewTrainer(trainer);
        System.out.println("Trainer " + name + " has been added to the list of trainers.");


    }

    public ArrayList<String> getTrainersFromFile() {
        ArrayList<String> trainers = new ArrayList<>();
        File file = new File("src/Files/trainers.csv");
        try {
            Scanner scanner = new Scanner(file);

            System.out.println("List of trainers in Delfinen: ");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                trainers.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found.");
        }
        return trainers;


    }

    public static void listOfTrainers() {
        System.out.println("Trainers in Delfinen: ");

        for (Trainer trainer : Database.getTrainerList()) {
            System.out.println(trainer);
        }
    }

    public static HashMap<Discipline, Discipline> assignDisciplines() {
        HashMap<Discipline, Discipline> disciplines = new HashMap<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Assign discipline BACKSTROKE to swimmer (Y/N):");
        if (input.nextLine().equalsIgnoreCase("Y")) {
            disciplines.put(Discipline.BACKSTROKE, Discipline.BACKSTROKE);
        } else {
            disciplines.put(Discipline.BACKSTROKE, null);
        }

        System.out.println("Assign discipline BUTTERFLY to swimmer (Y/N):");
        if (input.nextLine().equalsIgnoreCase("Y")) {
            disciplines.put(Discipline.BUTTERFLY, Discipline.BUTTERFLY);
        } else {
            disciplines.put(Discipline.BUTTERFLY, null);
        }

        System.out.println("Assign discipline FREESTYLE to swimmer (Y/N):");
        if (input.nextLine().equalsIgnoreCase("Y")) {
            disciplines.put(Discipline.FREESTYLE, Discipline.FREESTYLE);
        } else {
            disciplines.put(Discipline.FREESTYLE, null);
        }

        System.out.println("Assign discipline BREASTSTROKE to swimmer (Y/N):");
        if (input.nextLine().equalsIgnoreCase("Y")) {
            disciplines.put(Discipline.BREASTSTROKE, Discipline.BREASTSTROKE);
        } else {
            disciplines.put(Discipline.BREASTSTROKE, null);
        }

        return disciplines;
    }
}


