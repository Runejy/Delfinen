package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Enums.MemberActivity;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;
import delfinen.Results.ResultCompetition;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Trainer trainer = new Trainer("Træner Tina");

        // Opret første konkurrence-resultat
        ResultCompetition result1 = trainer.createCompetitionResult(
                "2025-05-15",
                52.37,
                Discipline.BUTTERFLY,
                "DM 2025",
                1
        );

        // Opret anden konkurrence-resultat
        ResultCompetition result2 = trainer.createCompetitionResult(
                "2025-06-20",
                50.12,
                Discipline.FREESTYLE,
                "Nordiske Mesterskaber",
                3
        );

        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BUTTERFLY);
        disciplines.add(Discipline.FREESTYLE);

        EliteSwimmer eliteSwimmer = new EliteSwimmer(
                "29876543",
                "Mikkel Sørensen",
                22,
                "Mand",
                "mikkel.sorensen@mail.dk",
                MemberActivity.ACTIVE,
                TrainingType.COMPETITION,
                Team.SENIOR,
                disciplines,
                trainer
        );

        // Tilføj begge resultater til elite svømmeren
        eliteSwimmer.addResult(result1);
        eliteSwimmer.addResult(result2);

        System.out.println("Elite svømmer: " + eliteSwimmer.getName());
        System.out.println("Resultater:");
        for (var r : eliteSwimmer.getResults()) {
            System.out.println(r);
        }
    }
}
