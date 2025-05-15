package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.MemberActivity;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;
import delfinen.Results.Result;

import java.util.ArrayList;

public class EliteSwimmer extends Member {
    private Team team;
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private Trainer trainer;
    private ArrayList<Result> results = new ArrayList<>();

    public EliteSwimmer(String phoneNumber, String name, int age, String gender, String mail,
                        MemberActivity memberActivity, TrainingType trainingType, Team team,
                        ArrayList<Discipline> disciplines, Trainer trainer) {
        super(phoneNumber, name, age, gender, mail, memberActivity, trainingType);
        this.team = team;
        this.disciplines = disciplines;
        this.trainer = trainer;
    }

    // Metode til at tilføje et resultat (se Main.java)
    public void addResult(Result result) {
        results.add(result);
    }

    public ArrayList<Result> getResults() {
        return results;
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
