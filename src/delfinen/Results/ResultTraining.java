package delfinen.Results;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;

public class ResultTraining implements Result {
    private TrainingType resultType = TrainingType.CASUAL;
    private String phoneNumber;
    private String swimmerName;
    private Team team;
    private String date = "";
    private double time = 0;
    private Discipline discipline;

    public ResultTraining(String phoneNumber, String swimmerName, Team team, String date, double time, Discipline discipline) {
        this.phoneNumber = phoneNumber;
        this.swimmerName = swimmerName;
        this.team = team;
        this.date = date;
        this.time = time;
        this.discipline = discipline;
    }

    @Override
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public String getSwimmerName() {
        return "";
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-15s %-15s %n", swimmerName, date, time + " sec", discipline);
    }

    @Override
    public String toCSVfile() {
        return phoneNumber + "," +
                swimmerName + "," +
                team + "," +
                date + "," +
                time + "," +
                ",,";
    }
    @Override
    public TrainingType getResultType(){
        return resultType;
    }
}
