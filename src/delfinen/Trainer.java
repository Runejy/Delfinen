package delfinen;

import java.util.ArrayList;

public class Trainer {
    private String name;
    private Database database;

    Trainer(String name, Database database){
        this.name = name;
        this.database = database;
    }

    public String getName(){
        return name;
    }

    public void listOfEliteSwimmer() {
        ArrayList<Member> eliteList = database.getEliteMemberArrayList();

        System.out.println("List of Elite Swimmers:");
        for (Member member : eliteList) {
            System.out.println("- " + member.getName());
            //EVT OGSÅ TILFØJE DISCIPLINER DE SVØMMER I NÅR DET ER OPRETTET
        }
    }

    public void listOfTrainersEliteSwimmers(){

    }
}
