package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.MemberActivity;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;
import delfinen.Results.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class EliteSwimmer extends Member {
    private Team team;
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private Trainer trainer;
    private ArrayList<Result> results = new ArrayList<>();


    EliteSwimmer(String name, int age, String gender, String mail, String phoneNumber, MemberActivity memberActivity, TrainingType trainingType, Team team, ArrayList<Discipline> disciplines, Trainer trainer) {
        super(name, age, gender, mail, phoneNumber, memberActivity, trainingType);
        this.team = team;
        this.disciplines = disciplines;
        this.trainer = trainer;
    }



//    public String addDiscipline() throws IOException {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Hvilken svømme disciplin, svømmer du i?");
//        System.out.println("1. Crawl " + "\n 2. Butterfly" + "\n 3. Freestyle" + "\n 4. Breaststroke");
//        int data = Integer.parseInt(null);
//        int discipline = input.nextInt();
//
//        switch (discipline) {
//            case 1:
//                System.out.println("Du svømmer Crawl!");
//                break;
//            case 2:
//                System.out.println("Du svømmer Butterfly!");
//                break;
//            case 3:
//                System.out.println("Du svømmer Freestyle!");
//                break;
//            case 4:
//                System.out.println("Du svømmer Breaststroke");
//            default:
//
//                try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.csv", true))) {
//                    writer.write(discipline);
//                    writer.newLine();
//                    System.out.println("Dit valg er gemt i ´databasen.csv´");
//
//                } catch (IOException e) {
//                    System.out.println("Fejl!");
//                }
//        }
//
//        return null;
//    }


    public Team getTeam() {
        return team;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

//            public void updateProfile () {
//
//            }
//
//            public void addResult () {
//
//            }


}


