package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.MemberActivity;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;
import delfinen.Results.Result;
import delfinen.Results.ResultCompetition;
import delfinen.Results.ResultTraining;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class EliteSwimmer extends Member {
    private Team team;
    private HashMap<Discipline, Discipline> disciplines = new HashMap<>();
    private Trainer trainer;
    private HashMap<Discipline, ArrayList<ResultTraining>> trainingResults = new HashMap<>();
    private HashMap<Discipline, ArrayList<ResultCompetition>> competitionResults = new HashMap<>();
    private Scanner scanner;

    public EliteSwimmer(String phoneNumber, String name, int age, String gender, String mail,
                        MemberActivity memberActivity, TrainingType trainingType, Team team,
                        HashMap<Discipline, Discipline> disciplines, Trainer trainer) {
        super(phoneNumber, name, age, gender, mail, memberActivity, trainingType);
        this.team = team;
        this.disciplines = disciplines;
        this.trainer = trainer;
    }

    public EliteSwimmer(){

    }

    public ResultCompetition createCompetitionResult() {
        scanner = new Scanner(System.in);

        //Date input
        String date = "";
        while (date.isBlank()) {
            System.out.println("Enter date for result: YYYY-MM-DD");
            date = scanner.nextLine();
            if (date.isBlank()) {
                System.out.println("Date cannot be empty.");
            }
        }

        //Time result input
        double timeResult = -1;

        System.out.println("Enter time result (must be a positive number):");
        while (!scanner.hasNextDouble() || (timeResult = scanner.nextDouble()) <= 0) {
            System.out.println("Invalid input. Please enter a number greater than 0:");
            scanner.nextLine();
        }
        scanner.nextLine();

        //Discipline Input
        Discipline[] discipline = Discipline.values();
        System.out.println("Choose a discipline's number from following list");
        for (int i = 0; i < discipline.length; i++) {
            System.out.println((i + 1) + ": " + discipline[i]);
        }
        int input = scanner.nextInt();
        scanner.nextLine();

        Discipline chosenDiscipline;

        if (input >= 1 && input <= discipline.length) {
            chosenDiscipline = discipline[input - 1];
        } else {
            System.out.println("Invalid input. Defaulting to FREESTYLE.");
            chosenDiscipline = Discipline.FREESTYLE;
        }


        //COMPETITION NAME
        String competition = "";
        while (competition.isBlank()) {
            System.out.println("Enter competition name: ");
            competition = scanner.nextLine();
            if (competition.isBlank()) {
                System.out.println("Competition name cannot be empty.");
            }
        }

        //Placement input
        int placement = -1;

        System.out.println("Enter placement/rank result: ");
        while (!scanner.hasNextInt() || (placement = scanner.nextInt()) <= 0) {
            System.out.println("Invalid input. Please enter a number greater than 0:");
            scanner.nextLine();
        }
        scanner.nextLine();

        //Skaber et nyt ResultCompetition objekt

        return new ResultCompetition(date, timeResult, chosenDiscipline, competition, placement);
    }

    public ResultTraining createTrainingResult() {
        scanner = new Scanner(System.in);

        //Date input
        String date = "";
        while (date.isBlank()) {
            System.out.println("Enter date for result: YYYY-MM-DD");
            date = scanner.nextLine();
            if (date.isBlank()) {
                System.out.println("Date cannot be empty.");
            }
        }

        //Time result input
        double timeResult = -1;

        System.out.println("Enter time result (must be a positive number):");
        while (!scanner.hasNextDouble() || (timeResult = scanner.nextDouble()) <= 0) {
            System.out.println("Invalid input. Please enter a number greater than 0:");
            scanner.nextLine();
        }
        scanner.nextLine();

        //Discipline Input
        Discipline[] discipline = Discipline.values();
        System.out.println("Choose a discipline's number from following list");
        for (int i = 0; i < discipline.length; i++) {
            System.out.println((i + 1) + ": " + discipline[i]);
        }
        int input = scanner.nextInt();
        scanner.nextLine();

        Discipline chosenDiscipline;

        if (input >= 1 && input <= discipline.length) {
            chosenDiscipline = discipline[input - 1];
        } else {
            System.out.println("Invalid input. Defaulting to FREESTYLE.");
            chosenDiscipline = Discipline.FREESTYLE;
        }


        //Skaber et nyt ResultTraining objekt

        return new ResultTraining(date, timeResult, chosenDiscipline);
    }

    // Metode til at tilføje et resultat (se Main.java)
    public void addTrainingResult(ResultTraining resultTraining) {

        trainingResults.putIfAbsent(resultTraining.getDiscipline(), new ArrayList<>()); //Hvis der ikke allerede findes en liste for denne disciplin, så sæt en tom liste ind.
        trainingResults.get(resultTraining.getDiscipline()).add(resultTraining);
    }

    public void addCompetitionResult(ResultCompetition resultCompetition) {
        competitionResults.putIfAbsent(resultCompetition.getDiscipline(), new ArrayList<>()); //Hvis der ikke allerede findes en liste for denne disciplin, så sæt en tom liste ind.
        competitionResults.get(resultCompetition.getDiscipline()).add(resultCompetition);
    }

    public double getBestTimeForTrainingResuts(Discipline discipline) {
        ArrayList<ResultTraining> results = trainingResults.get(discipline);

        if(results == null || results.isEmpty()){
            return Double.MAX_VALUE;
        }

        return results.stream()
                .mapToDouble(Result::getTime)
                .min()
                .orElse(Double.MAX_VALUE); // Hvis ingen resultater
    }

    public double getBestTimeForCompetitionResults(Discipline discipline) {
        ArrayList<ResultCompetition> results = competitionResults.get(discipline);

        if(results == null || results.isEmpty()){
            return Double.MAX_VALUE;
        }

        return results.stream()
                .mapToDouble(Result::getTime)
                .min()
                .orElse(Double.MAX_VALUE); // Hvis ingen resultater
    }

    public HashMap<Discipline, ArrayList<ResultTraining>> getTrainingResults() {
        return trainingResults;
    }

    public HashMap<Discipline, ArrayList<ResultCompetition>> getCompetitionResults() {
        return competitionResults;
    }

    public void showTrainingResults(Discipline discipline){
        System.out.println("\n" + getName() + "\n");

        System.out.printf("%-15s %-15s %-15s \n", discipline, "DATE", "TIME");

        if(trainingResults.get(discipline) != null){
            for(ResultTraining result : trainingResults.get(discipline)){
                System.out.println(result);
            }
        }

        System.out.println("--------------------------------------------------------------------");
    }

    public void showCompetitionResults(Discipline discipline){
        System.out.println("\n" + getName() + "\n");

        System.out.printf("%-15s %-15s %-15s \n", discipline, "DATE", "TIME");

        if(trainingResults.get(discipline) != null){
            for(ResultTraining result : trainingResults.get(discipline)){
                System.out.println(result);
            }
        }

        System.out.println("--------------------------------------------------------------------");
    }

    // Getters og setters for øvrige felter...

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public HashMap<Discipline, Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(HashMap<Discipline, Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String toString(){
        return String.format("%-15s %-25s %-10s %-10s %-15s %-25s %-10s %-40s ", getPhoneNumber(), getName(), getGender(),getMemberActivity(), getTrainingType(), trainer, team, disciplines);
    }
}
