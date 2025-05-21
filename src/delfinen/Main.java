package delfinen;

import delfinen.Enums.Discipline;
import delfinen.Trainer;
import delfinen.Results.ResultCompetition;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Menu.mainMenu();

        Database.getEliteSwimmersArrayList();
    }
}
