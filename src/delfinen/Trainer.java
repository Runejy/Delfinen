package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;
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

    public Trainer() {
    }

    public static double getBestTimeForResultList(ArrayList<Result> resultList) {
        if(resultList == null || resultList.isEmpty()){
            return Double.MAX_VALUE;
        }

        return resultList.stream()
                .mapToDouble(Result::getTime)
                .min()
                .orElse(Double.MAX_VALUE); // Hvis ingen resultater
    }

    public static void getTop5TrainingResults(Discipline discipline) {
        ArrayList<Result> resultList;

        if (discipline.equals(Discipline.BACKSTROKE)) {
            resultList = Database.getBackstrokeList();
        } else if (discipline.equals(Discipline.BUTTERFLY)) {
            resultList = Database.getButterflyList();
        } else if (discipline.equals(Discipline.FREESTYLE)) {
            resultList = Database.getFreestyleList();
        } else if(discipline.equals(Discipline.BREASTSTROKE)){
            resultList = Database.getBreastStrokeList();

        } else{
            System.out.println("Drizzler was here, error, try again.");
            return;
        }


         List<Result>top5TrainingResults = resultList.stream().filter(result -> result.getResultType().equals(TrainingType.CASUAL))
                .sorted(Comparator.comparingDouble(Result::getTime))
                .limit(5)
                .toList();

        for(Result result: top5TrainingResults){
            System.out.println(result);
        }
    }

    public static void getTop5CompetitionResults(Discipline discipline) {
        ArrayList<Result> resultList;

        if (discipline.equals(Discipline.BACKSTROKE)) {
            resultList = Database.getBackstrokeList();
        } else if (discipline.equals(Discipline.BUTTERFLY)) {
            resultList = Database.getButterflyList();
        } else if (discipline.equals(Discipline.FREESTYLE)) {
            resultList = Database.getFreestyleList();
        } else if(discipline.equals(Discipline.BREASTSTROKE)){
            resultList = Database.getBreastStrokeList();

        } else{
            System.out.println("Drizzler was here, error, try again.");
            return;
        }


        List<Result>top5TrainingResults = resultList.stream().filter(result -> result.getResultType().equals(TrainingType.COMPETITION))
                .sorted(Comparator.comparingDouble(Result::getTime))
                .limit(5)
                .toList();

        for(Result result: top5TrainingResults){
            System.out.println(result);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
