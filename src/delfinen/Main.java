package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Trainer;
import delfinen.Results.ResultCompetition;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();

        ArrayList<EliteSwimmer> liste = database.getEliteMemberArrayList();

        for(EliteSwimmer eliteSwimmer : liste){
            System.out.println("asdas"+ eliteSwimmer);
        }
        //Menu Menu = new Menu();
        //Menu.mainMenu();

//        EliteSwimmer eliteSwimmer = new EliteSwimmer();
//
//
//
//        while(true){
//            eliteSwimmer.addCompetitionResult(eliteSwimmer.createCompetitionResult());
//            eliteSwimmer.showCompetitionResult();
//        }
    }
}
