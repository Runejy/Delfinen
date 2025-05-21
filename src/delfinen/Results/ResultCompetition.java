package delfinen.Results;

import delfinen.Enums.Discipline;

public class ResultCompetition implements Result {
    private String date;
    private double time;
    private Discipline discipline;
    private String competitionName;
    private int placement;

    public ResultCompetition(String date, double time, Discipline discipline, String competitionName, int placement) {
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
        return String.format("%-15s %-15s %-15s %-15s %-15s%n", getDiscipline(), getDate(), getTime(), getCompetitionName(), getPlacement());
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
}
