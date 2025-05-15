package delfinen;

import delfinen.Enums.MemberType;

import java.util.ArrayList;

public class Trainer {
    public static String name;
    private Database database = new Database();
    private ArrayList<Member> eliteList = database.getEliteMemberArrayList();

    Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void listOfEliteSwimmer() {

        System.out.println("List of Elite Swimmers:");
        for (Member member : eliteList) {
            System.out.println("- " + member.getName());
            //EVT OGSÅ TILFØJE DISCIPLINER DE SVØMMER I NÅR DET ER OPRETTET
        }
    }

    public void showTeam() {
        System.out.println("træner; " + name);
        System.out.println("Svømmer på holdet:");
        for (Member member : eliteList) ;
    }

    public void listOfTrainersEliteSwimmers() {
    }

}
