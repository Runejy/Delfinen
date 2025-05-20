package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Results.Result;
import delfinen.Results.ResultCompetition;
import delfinen.Results.ResultTraining;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Trainer {
    private String name;

    public Trainer(String name) {
        this.name = name;
    }

    public Trainer(){

    }

    public ResultCompetition createCompetitionResult(String date, double time, Discipline discipline, String competitionName, int placement) {
        return new ResultCompetition(date, time, discipline, competitionName, placement);
    }

    public void addEliteSwimmer(EliteSwimmer swimmer) {
        Database.getEliteSwimmerList().add(swimmer);
    }

    public static void getTop5TrainingResults(Discipline discipline) {
        List<EliteSwimmer> top5TrainingResults = Database.getEliteSwimmerList().stream()
                .filter(eliteSwimmer -> eliteSwimmer.getDisciplines().containsKey(discipline))
                .sorted(Comparator.comparingDouble(eliteSwimmer -> eliteSwimmer.getBestTimeForTrainingResuts(discipline)))
                .limit(5)
                .collect(Collectors.toList());

        for(EliteSwimmer eliteSwimmer : top5TrainingResults){
            eliteSwimmer.showTrainingResults();
        }
    }

    public static void getTop5CompetitionResults(Discipline discipline) {
        List<EliteSwimmer> top5TrainingResults = Database.getEliteSwimmerList().stream()
                .filter(eliteSwimmer -> eliteSwimmer.getDisciplines().containsKey(discipline))
                .sorted(Comparator.comparingDouble(eliteSwimmer -> eliteSwimmer.getBestTimeForCompetitionResults(discipline)))
                .limit(5)
                .collect(Collectors.toList());

        for(EliteSwimmer eliteSwimmer : top5TrainingResults){
            eliteSwimmer.showCompetitionResult();
        }
    }

    public String getName() {
        return name;
    }

    public static void listOfTrainersEliteSwimmers() {
    }

    @Override
    public String toString(){
        return name;
    }
}
