package delfinen.Results;

import delfinen.Enums.Discipline;

public class ResultTraining implements Result {
    private String date = "";
    private double time = 0;
    private Discipline discipline;

    public ResultTraining(String date, double time, Discipline discipline) {
        this.date = date;
        this.time = time;
        this.discipline = discipline;
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
        // Returner relevant data som string
        return String.format("%-15s %-15s %-15s%n", discipline, getDate(), getTime());
    }
}
