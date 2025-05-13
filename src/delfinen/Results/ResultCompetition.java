package delfinen.Results;

import delfinen.Enums.Discipline;

public class ResultCompetition implements Result {
    private String date;
    private double time;
    private Discipline discipline;
    private String competitionName;
    private int placement;

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

    public void viewRusults(){

    }
}
