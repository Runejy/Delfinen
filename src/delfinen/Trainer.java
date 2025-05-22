package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;
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

    public static List<EliteSwimmer> getTop5TrainingResults(Discipline discipline) {
        List<EliteSwimmer> top5TrainingResults = Database.getEliteSwimmerList().stream()
                .filter(eliteSwimmer -> eliteSwimmer.getDisciplines().containsKey(discipline))
                .sorted(Comparator.comparingDouble(eliteSwimmer -> eliteSwimmer.getBestTimeForTrainingResuts(discipline)))
                .limit(5)
                .toList();

        for (EliteSwimmer eliteSwimmer : top5TrainingResults) {
            eliteSwimmer.showTrainingResults(discipline);
        }

        return top5TrainingResults;
    }

    public static void getTop5CompetitionResults(Discipline discipline) {
        List<EliteSwimmer> top5TrainingResults = Database.getEliteSwimmerList().stream()
                .filter(eliteSwimmer -> eliteSwimmer.getDisciplines().containsKey(discipline))
                .sorted(Comparator.comparingDouble(eliteSwimmer -> eliteSwimmer.getBestTimeForCompetitionResults(discipline)))
                .limit(5)
                .toList();

        for (EliteSwimmer eliteSwimmer : top5TrainingResults) {
            eliteSwimmer.showCompetitionResults(discipline);
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
