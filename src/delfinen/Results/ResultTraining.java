package delfinen.Results;

import delfinen.Enums.Discipline;

public class ResultTraining implements Result {
    private String date;
    private double time;
    private Discipline discipline;

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
}
