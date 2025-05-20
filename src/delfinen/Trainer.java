package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Results.Result;
import delfinen.Results.ResultCompetition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Trainer {
    private Database database = new Database();
    private ArrayList<Member> eliteList = database.getEliteMemberArrayList();

    private String name;
    private List<EliteSwimmer> eliteSwimmers = new ArrayList<>();

    public Trainer(String name) {
        this.name = name;
    }

    public ResultCompetition createCompetitionResult(String date, double time, Discipline discipline, String competitionName, int placement) {
        return new ResultCompetition(date, time, discipline, competitionName, placement);
    }

    public void addEliteSwimmer(EliteSwimmer swimmer) {
        eliteSwimmers.add(swimmer);
    }

    public List<EliteSwimmer> getTop5SwimmersByDiscipline(Discipline discipline) {
        return eliteSwimmers.stream()
                .filter(swimmer -> swimmer.getDisciplines().contains(discipline))
                .sorted(Comparator.comparingDouble(swimmer -> getBestTimeForDiscipline(swimmer, discipline)))
                .limit(5)
                .collect(Collectors.toList());
    }

    private double getBestTimeForDiscipline(EliteSwimmer swimmer, Discipline discipline) {
        return swimmer.getResults().stream()
                .filter(result -> result.getDiscipline() == discipline)
                .mapToDouble(Result::getTime)
                .min()
                .orElse(Double.MAX_VALUE); // Hvis ingen resultater
    }

    public String getName() {
        return name;
    }

    public List<EliteSwimmer> getEliteSwimmers() {
        return eliteSwimmers;
    }

    public void listOfTrainersEliteSwimmers() {

    }
}
