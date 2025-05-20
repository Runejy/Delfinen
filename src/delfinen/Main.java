package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Trainer;
import delfinen.Results.ResultCompetition;

public class Main {
    public static void main(String[] args) {


        //Menu Menu = new Menu();
        //Menu.mainMenu();

        EliteSwimmer eliteSwimmer = new EliteSwimmer();

        while(true){
            eliteSwimmer.addCompetitionResult(eliteSwimmer.createCompetitionResult());
            eliteSwimmer.showCompetitionResult();
        }
    }
}
