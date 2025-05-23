package delfinen;

import delfinen.Enums.*;
import delfinen.Results.Result;
import delfinen.Results.ResultCompetition;
import delfinen.Results.ResultTraining;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
    private static FileWriter fileWriter;
    private static String databaseFilePath = "src/Files/database.csv";
    private static String eliteSwimmerFilePath = "src/Files/EliteSwimmer.csv";
    private static String trainerFilePath = "src/Files/trainers.csv";
    private static String resultsFilePath = "src/Files/Results/";
    private static ArrayList<Member> memberList = getMemberArrayList();
    private static ArrayList<EliteSwimmer> eliteSwimmerList = getEliteSwimmersArrayList();
    private static ArrayList<Trainer> trainerList = getTrainerArrayList();
    private static ArrayList<Result> butterflyList = getResultArrayList(Discipline.BUTTERFLY);
    private static ArrayList<Result> backstrokeList = getResultArrayList(Discipline.BACKSTROKE);
    private static ArrayList<Result> breaststrokeList = getResultArrayList(Discipline.BREASTSTROKE);
    private static ArrayList<Result> freestyleList = getResultArrayList(Discipline.FREESTYLE);

    public static void print() {
        for (Member member : memberList) {
            System.out.println(member);
        }
    }

    public static void inputNewMemberData(Member newMember) {
        if (newMember == null) {
            System.out.println("Error: Member data is missing.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String phoneNumber = newMember.getPhoneNumber();

        while (phoneNumber == null || !phoneNumber.matches("\\d{8}")) {
            System.out.println("Error: Invalid phone number. Must be 8 digits.");
            System.out.print("Please enter a valid phone number: ");
            phoneNumber = scanner.nextLine();
        }


        phoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");


        try (FileWriter fileWriter = new FileWriter(databaseFilePath, true)) {
            fileWriter.write(

                    System.lineSeparator() + phoneNumber +
                            "," + newMember.getName() +
                            "," + newMember.getAge() +
                            "," + newMember.getGender() +
                            "," + newMember.getMail() +
                            "," + newMember.getMemberActivity() +
                            "," + newMember.getMemberType() +
                            "," + newMember.getTrainingType()
            );
            System.out.println("Member data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing member data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean addNewMember(Member member) {
        if (member == null) {
            System.out.println("Error: Member data is missing.");
            return false;
        }

        String phoneNumber = member.getPhoneNumber();

        if (phoneNumber == null && (!phoneNumber.matches("\\d{8}") || !phoneNumber.matches("(\\d{2})(\\d{2})(\\d{2})(\\d{2})"))) {
            System.out.println("Database ERROR: Invalid phone number format. Use: XX XX XX XX.");
            return false;
        }

        phoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
        member.setPhoneNumber(phoneNumber);

        memberList.add(member);
        updateDatabaseFile();

        return true;
    }

    public static boolean addNewEliteSwimmer(EliteSwimmer eliteSwimmer) {
        if (eliteSwimmer == null) {
            System.out.println("Error: Member data is missing.");
            return false;
        }

        String phoneNumber = eliteSwimmer.getPhoneNumber();

        if (phoneNumber == null && (!phoneNumber.matches("\\d{8}") || !phoneNumber.matches("(\\d{2})(\\d{2})(\\d{2})(\\d{2})"))) {
            System.out.println("Database ERROR: Invalid phone number format. Use: XX XX XX XX.");
            return false;
        }

        phoneNumber = phoneNumber.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
        eliteSwimmer.setPhoneNumber(phoneNumber);

        eliteSwimmerList.add(eliteSwimmer);
        updateEliteSwimmerFile();

        return true;
    }

    public static void addNewResult(Discipline discipline, Result result) {
        if (discipline.equals(Discipline.BACKSTROKE)) {
            backstrokeList.add(result);
            updateResultFiles(Discipline.BACKSTROKE);
        } else if (discipline.equals(Discipline.BREASTSTROKE)) {
            breaststrokeList.add(result);
            updateResultFiles(Discipline.BREASTSTROKE);
        } else if (discipline.equals(Discipline.BUTTERFLY)) {
            butterflyList.add(result);
            updateResultFiles(Discipline.BUTTERFLY);
        } else if (discipline.equals(Discipline.FREESTYLE)) {
            freestyleList.add(result);
            updateResultFiles(Discipline.FREESTYLE);
        }
    }

    private static Member findMember(String phoneNumber){
        Member foundMember = null;

        for(Member member: memberList){
            if(member.getPhoneNumber().equals(phoneNumber)){
                foundMember = member;
                break;
            }
        }

        return foundMember;
    }

    private static EliteSwimmer findEliteSwimmer(String phoneNumber){
        EliteSwimmer foundEliteSwimmer = null;

        for(EliteSwimmer eliteSwimmer: eliteSwimmerList){
            if(eliteSwimmer.getPhoneNumber().equals(phoneNumber)){
                foundEliteSwimmer = eliteSwimmer;
                break;
            }
        }

        return foundEliteSwimmer;
    }

    private static Trainer findTrainer(String name){
        Trainer foundTrainer = null;

        for(Trainer trainer: trainerList){
            if(trainer.getName().equals(name)){
                foundTrainer = trainer;
            }
        }

        return foundTrainer;
    }

    public static void changeMemberData(String searchedPhoneNumber, String dataKey, String dataValue) {
        Member member = findMember(searchedPhoneNumber);

        if(member.getTrainingType().equals(TrainingType.CASUAL)){
            changeMember(member, dataKey, dataValue);
        } else if(member.getTrainingType().equals(TrainingType.COMPETITION)){
            EliteSwimmer eliteSwimmer = findEliteSwimmer(searchedPhoneNumber);

            changeMember(member, dataKey, dataValue);
            changeEliteSwimmer(eliteSwimmer, dataKey, dataValue);
        }
    }

    private static void changeMember(Member member, String dataKey, String dataValue){
        switch (dataKey) {
            case "Telephone":
                if (!dataValue.matches("\\d{8}")) {
                    System.out.println("Database ERROR: Invalid phone number format. Use: XX XX XX XX.");
                    break;
                }

                dataValue = dataValue.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");

                member.setPhoneNumber(dataValue);
                updateDatabaseFile();
                break;
            case "Name":
                member.setName(dataValue);
                updateDatabaseFile();
                break;
            case "Age":
                member.setAge(Integer.parseInt(dataValue));
                updateDatabaseFile();
                break;
            case "Gender":
                member.setGender(dataValue);
                updateDatabaseFile();
                break;
            case "Mail":
                member.setMail(dataValue);
                updateDatabaseFile();
                break;
            case "Member Activity":
                member.setMemberActivity(MemberActivity.valueOf(dataValue));
                updateDatabaseFile();
                break;
            case "Member Type":
                member.setMemberType(MemberType.valueOf(dataValue));
                updateDatabaseFile();
                break;
            case "Training Type":
                member.setTrainingType(TrainingType.valueOf(dataValue));
                updateDatabaseFile();
                break;
            case "Remove Member":
                memberList.remove(member);
                updateDatabaseFile();
                break;
        }
    }

    private static void changeEliteSwimmer(EliteSwimmer eliteSwimmer, String dataKey, String dataValue){
        switch (dataKey) {
            case "Telephone":
                if (!dataValue.matches("\\d{8}")) {
                    System.out.println("Database ERROR: Invalid phone number format. Use: XX XX XX XX.");
                    break;
                }

                dataValue = dataValue.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");

                eliteSwimmer.setPhoneNumber(dataValue);
                updateEliteSwimmerFile();
                break;
            case "Name":
                eliteSwimmer.setName(dataValue);
                updateEliteSwimmerFile();
                break;
            case "Trainer":
                eliteSwimmer.setTrainer(findTrainer(dataValue));
                updateEliteSwimmerFile();
                break;
            case "Team":
                eliteSwimmer.setTeam(Team.valueOf(dataValue));
                updateEliteSwimmerFile();
                break;
            case "Discipline":
                eliteSwimmer.setDisciplines(Discipline.valueOf(dataValue));
                updateEliteSwimmerFile();
                break;
            case "Remove Member":
                eliteSwimmerList.remove(eliteSwimmer);
                updateEliteSwimmerFile();
                break;
        }
    }

    public static boolean memberUpdated(String phoneNumber) {
        for (Member member : memberList) {
            if (member.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    public static void updateDatabaseFile() {
        try {
            fileWriter = new FileWriter(databaseFilePath);

            String data = "Telephone,Name,Age,Gender,Mail,Member Activity,Member Type,Training Type";
            for (Member member : memberList) {
                data += "\n" + member.getPhoneNumber() +
                        "," + member.getName() +
                        "," + member.getAge() +
                        "," + member.getGender() +
                        "," + member.getMail() +
                        "," + member.getMemberActivity() +
                        "," + member.getMemberType() +
                        "," + member.getTrainingType();
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateEliteSwimmerFile() {
        try {
            fileWriter = new FileWriter(eliteSwimmerFilePath);

            String data = "Telephone,SwimmerName,Trainer,Team,Discipline";


            for (EliteSwimmer eliteSwimmer : eliteSwimmerList) {
                String FREESTYLE = "null";
                String BACKSTROKE = "null";
                String BREASTSTROKE = "null";
                String BUTTERFLY = "null";

                if (eliteSwimmer.getDisciplines().get(Discipline.FREESTYLE) != null) {
                    FREESTYLE = "FREESTYLE";
                }
                if (eliteSwimmer.getDisciplines().get(Discipline.BACKSTROKE) != null) {
                    BACKSTROKE = "BACKSTROKE";
                }
                if (eliteSwimmer.getDisciplines().get(Discipline.BREASTSTROKE) != null) {
                    BREASTSTROKE = "BREASTSTROKE";
                }
                if (eliteSwimmer.getDisciplines().get(Discipline.BUTTERFLY) != null) {
                    BUTTERFLY = "BUTTERFLY";
                }

                String disciplineLine = FREESTYLE
                        + ";" + BACKSTROKE
                        + ";" + BREASTSTROKE
                        + ";" + BUTTERFLY;

                data += "\n" + eliteSwimmer.getPhoneNumber() +
                        "," + eliteSwimmer.getName() +
                        "," + eliteSwimmer.getTrainer() +
                        "," + eliteSwimmer.getTeam() +
                        "," + disciplineLine;
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateResultFiles(Discipline discipline) {
        try {
            fileWriter = new FileWriter(resultsFilePath + discipline + ".csv");

            ArrayList<Result> resultList = null;


            if (discipline == Discipline.BACKSTROKE) {
                resultList = backstrokeList;
            } else if (discipline == Discipline.BREASTSTROKE) {
                resultList = breaststrokeList;
            } else if (discipline == Discipline.BUTTERFLY) {
                resultList = butterflyList;
            } else if (discipline == Discipline.FREESTYLE) {
                resultList = freestyleList;
            } else {
                System.out.println("Error!");
            }

            String data = "Phone number,Swimmer name,Team,Date,Time,Competion name,Placement";
            for (Result result : resultList) {
                data += "\n" + result.toCSVfile();
            }

            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //INPUT ELITE DATA TO CSV FILE
    public static void inputNewEliteData(EliteSwimmer newEliteSwimmer) {
        try {
            fileWriter = new FileWriter(eliteSwimmerFilePath, true);

            // Fordi disciplinerne er enums, konverteres de til Strings med .name() først
            String disciplineString = makeDisciplineText(newEliteSwimmer.getDisciplines());

            fileWriter.write(
                    System.lineSeparator() +
                            newEliteSwimmer.getPhoneNumber() + ";" +
                            newEliteSwimmer.getName() + ";" +
                            newEliteSwimmer.getTrainer() + ";" +
                            newEliteSwimmer.getTeam() + ";" +
                            disciplineString
            );
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String makeDisciplineText(HashMap<Discipline, Discipline> disciplines) {
        String result = "";

        for (int i = 0; i < disciplines.size(); i++) {
            result += disciplines.get(i); // ADDS DISCIPLINE AS TEXT
            if (i < disciplines.size() - 1) {
                result += ", "; // ADD COMMA AND SPACE IF IT'S NOT THE LAST DISCIPLINE IN LIST
            }
        }

        return result;
    }

    // ARRAYLIST OVER MEMBERS IN DATABASE
    private static ArrayList<Member> getMemberArrayList() {
        try {
            ArrayList<Member> memberList = new ArrayList<>();
            Scanner fileReader = new Scanner(new File(databaseFilePath));

            if (fileReader.hasNextLine()) {
                fileReader.nextLine(); // skip header
            }

            while (fileReader.hasNextLine()) {
                String[] rowData = fileReader.nextLine().split(",");

                Member newMember = new Member(
                        rowData[0], //Telephone
                        rowData[1], //Name
                        Integer.parseInt(rowData[2]), //Age
                        rowData[3], //Gender
                        rowData[4], //Mail
                        MemberActivity.valueOf(rowData[5].toUpperCase()), //Active or Passive
                        TrainingType.valueOf(rowData[7].toUpperCase())); //Casual og Competition

                memberList.add(newMember);
            }
            return memberList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ARRAYLIST OVER ELITE MEMBERS IN DATABASE
    private static ArrayList<EliteSwimmer> getEliteSwimmersArrayList() {
        ArrayList<EliteSwimmer> eliteMemberList = new ArrayList<>();

        try {
            Scanner eliteSwimmerReader = new Scanner(new File(eliteSwimmerFilePath));
            eliteSwimmerReader.nextLine();

            while (eliteSwimmerReader.hasNextLine()) {
                String[] eliteSwimmerRowData = eliteSwimmerReader.nextLine().split(",");

                Scanner databaseReader = new Scanner(new File(databaseFilePath));
                databaseReader.nextLine();

                while (databaseReader.hasNextLine()) {
                    String[] rowData = databaseReader.nextLine().split(",");

                    if (eliteSwimmerRowData[0].equals(rowData[0])) {
                        HashMap<Discipline, Discipline> disciplineHashMap = new HashMap<>();
                        String[] disciplinesSplit = eliteSwimmerRowData[4].split(";");

                        for (String stringDiscipline : disciplinesSplit) {
                            if (!stringDiscipline.equalsIgnoreCase("null")) {
                                disciplineHashMap.put(Discipline.valueOf(stringDiscipline), Discipline.valueOf(stringDiscipline));
                            }
                        }

                        Trainer trainer = new Trainer(eliteSwimmerRowData[2]);

                        EliteSwimmer eliteSwimmer = new EliteSwimmer(rowData[0], rowData[1], Integer.parseInt(rowData[2]), rowData[3], rowData[4], MemberActivity.valueOf(rowData[5].toUpperCase()), TrainingType.valueOf(rowData[7].toUpperCase()), Team.valueOf(eliteSwimmerRowData[3]), disciplineHashMap, trainer);
                        eliteMemberList.add(eliteSwimmer);

                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return eliteMemberList;
    }

    private static ArrayList<Trainer> getTrainerArrayList() {
        try {
            ArrayList<Trainer> trainerArrayList = new ArrayList<>();
            Scanner fileReader = new Scanner(new File(trainerFilePath));

            fileReader.nextLine();

            while (fileReader.hasNextLine()) {
                Trainer trainer = new Trainer(fileReader.nextLine()); //Name

                trainerArrayList.add(trainer);
            }
            return trainerArrayList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Result> getResultArrayList(Discipline discipline) {
        try {
            ArrayList<Result> resultList = new ArrayList<>();


            Scanner fileReader = new Scanner(new File(resultsFilePath + discipline + ".csv"));

            if (fileReader.hasNextLine()) {
                fileReader.nextLine(); // skip header
            }

            while (fileReader.hasNextLine()) {
                String[] rowData = fileReader.nextLine().split(",");

                if (discipline.equals(Discipline.FREESTYLE)) {
                    if (rowData.length == 5) {
                        ResultTraining resultTraining = new ResultTraining(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline);
                        resultList.add(resultTraining);
                    } else if (rowData.length == 7) {
                        ResultCompetition resultCompetition = new ResultCompetition(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline, rowData[5], Integer.parseInt(rowData[6]));
                        resultList.add(resultCompetition);
                    }
                }
                if (discipline.equals(Discipline.BACKSTROKE)) {
                    if (rowData.length == 5) {
                        ResultTraining resultTraining = new ResultTraining(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline);
                        resultList.add(resultTraining);
                    } else if (rowData.length == 7) {
                        ResultCompetition resultCompetition = new ResultCompetition(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline, rowData[5], Integer.parseInt(rowData[6]));
                        resultList.add(resultCompetition);
                    }
                }
                if (discipline.equals(Discipline.BREASTSTROKE)) {
                    if (rowData.length == 5) {
                        ResultTraining resultTraining = new ResultTraining(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline);
                        resultList.add(resultTraining);
                    } else if (rowData.length == 7) {
                        ResultCompetition resultCompetition = new ResultCompetition(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline, rowData[5], Integer.parseInt(rowData[6]));
                        resultList.add(resultCompetition);
                    }
                }
                if (discipline.equals(Discipline.BUTTERFLY)) {
                    if (rowData.length == 5) {
                        ResultTraining resultTraining = new ResultTraining(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline);
                        resultList.add(resultTraining);
                    } else if (rowData.length == 7) {
                        ResultCompetition resultCompetition = new ResultCompetition(rowData[0], rowData[1], Team.valueOf(rowData[2]), rowData[3], Double.parseDouble(rowData[4]), discipline, rowData[5], Integer.parseInt(rowData[6]));
                        resultList.add(resultCompetition);
                    }
                }
            }
            return resultList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//ADD TRAINER TO trainers.csv

    public static void inputNewTrainer(Trainer newTrainer) {
        try {
            fileWriter = new FileWriter(trainerFilePath, true);
            fileWriter.write(
                    "\n" + newTrainer.getName()
            );
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method to print out the userInputted members from database
    public static void databaseOutput() {
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(databaseFilePath));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                System.out.printf("%-20s", row[0]);
                System.out.printf("%-20s", row[1]);
                System.out.printf("%-20s", row[2]);
                System.out.printf("%-40s", row[3]);
                System.out.printf("%-30s", row[4]);
                System.out.printf("%-30s", row[5]);
                System.out.printf("%-30s", row[6]);
                System.out.printf("%-30s", row[7]);

                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static EliteSwimmer findEliteMemberByPhone(String phoneNumber) {
        for (EliteSwimmer eliteSwimmer : eliteSwimmerList) {
            if (eliteSwimmer.getPhoneNumber().trim().contains(phoneNumber.trim())) {
                return eliteSwimmer;
            }
        }
        return null;
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
    }

    public static ArrayList<EliteSwimmer> getEliteSwimmerList() {
        return eliteSwimmerList;
    }

    public static ArrayList<Trainer> getTrainerList() {
        return trainerList;
    }

    public static ArrayList<Result> getBackstrokeList() {
        return backstrokeList;
    }

    public static ArrayList<Result> getBreastStrokeList() {
        return breaststrokeList;
    }

    public static ArrayList<Result> getButterflyList() {
        return butterflyList;
    }

    public static ArrayList<Result> getFreestyleList() {
        return freestyleList;
    }


}