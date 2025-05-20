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
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private Trainer trainer;
    private HashMap<Discipline, ArrayList<ResultTraining>> trainingResult = new HashMap<>();
    HashMap<Discipline, ArrayList<ResultCompetition>> competitionResult = new HashMap<>();
    private ArrayList<Result> results = new ArrayList<>();
    private Scanner scanner;

    public EliteSwimmer(String phoneNumber, String name, int age, String gender, String mail,
                        MemberActivity memberActivity, TrainingType trainingType, Team team,
                        ArrayList<Discipline> disciplines, Trainer trainer) {
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

        trainingResult.putIfAbsent(resultTraining.getDiscipline(), new ArrayList<>()); //Hvis der ikke allerede findes en liste for denne disciplin, så sæt en tom liste ind.
        trainingResult.get(resultTraining.getDiscipline()).add(resultTraining);
    }

    public void addCompetitionResult(ResultCompetition resultCompetition) {
        competitionResult.putIfAbsent(resultCompetition.getDiscipline(), new ArrayList<>()); //Hvis der ikke allerede findes en liste for denne disciplin, så sæt en tom liste ind.
        competitionResult.get(resultCompetition.getDiscipline()).add(resultCompetition);
    }

    public ArrayList<ResultTraining> getTrainingResults(Discipline discipline) {
        return trainingResult.get(discipline);
    }

    public ArrayList<ResultCompetition> getCompetitionResults(Discipline discipline) {
        return competitionResult.get(discipline);
    }

    public void showTrainingResults(){
        for(Discipline discipline: trainingResult.keySet()){
            System.out.println(String.format("%-15s %-15s", "DATE", "TIME"));
            for(ResultTraining result : trainingResult.get(discipline)){
                System.out.println(String.format("%-15s %-15s", result.getDate(), result.getTime()));
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }

    public void showCompetitionResult(){
        for(Discipline discipline: competitionResult.keySet()){
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s", "DATE", "TIME", "DISCIPLINE", "COMPETITION NAME", "PLACEMENT"));
            for(ResultCompetition result : competitionResult.get(discipline)){
                System.out.println(String.format("%-15s %-15s %-15s %-25s %-15s", result.getDate(), result.getTime(), result.getDiscipline(), result.getCompetitionName(), result.getPlacement()));
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }

    // Getters og setters for øvrige felter...

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
