package delfinen.Results;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;

public class ResultCompetition implements Result {
    private TrainingType resultType = TrainingType.COMPETITION;
    private String phoneNumber;
    private String swimmerName;
    private Team team;
    private String date;
    private double time;
    private Discipline discipline;
    private String competitionName;
    private int placement;

    public ResultCompetition(String phoneNumber, String swimmerName, Team team, String date, double time, Discipline discipline, String competitionName, int placement) {
        this.phoneNumber = phoneNumber;
        this.swimmerName = swimmerName;
        this.team = team;
        this.date = date;
        this.time = time;
        this.discipline = discipline;
        this.competitionName = competitionName;
        this.placement = placement;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getSwimmerName() {
        return swimmerName;
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

    public String getCompetitionName() {
        return competitionName;
    }

    public int getPlacement() {
        return placement;
    }

    @Override
    public String toString() {
        return String.format("%-25s %-15s %-15s %-15s %-25s %-15s%n", swimmerName, date, time + " sec", discipline, competitionName, placement);
    }

    @Override
    public String toCSVfile() {
        return phoneNumber + "," +
                swimmerName + "," +
                team + "," +
                date + "," +
                time + "," +
                competitionName + "," +
                placement;
    }

    @Override
    public TrainingType getResultType(){
        return resultType;
    }
}
