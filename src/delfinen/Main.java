package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Trainer;
import delfinen.Results.ResultCompetition;

public class Main {
    public static void main(String[] args) {
        Trainer trainer = new Trainer("Træner Tina");

        ResultCompetition result = trainer.createCompetitionResult(
                "2025-05-15",
                52.37,
                Discipline.BUTTERFLY,
                "DM 2025",
                1
        );

        System.out.println(result);
    }
}
