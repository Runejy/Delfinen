package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Results.ResultCompetition;

public class Trainer {

    private String name;

    public Trainer(String name) {
        this.name = name;
    }

    public ResultCompetition createCompetitionResult(String date, double time, Discipline discipline, String competitionName, int placement) {
        return new ResultCompetition(date, time, discipline, competitionName, placement);
    }

    public String getName() {
        return name;
    }
}
