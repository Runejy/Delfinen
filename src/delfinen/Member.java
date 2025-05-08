package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;

public class Member {
    private String name;
    private static int idCounter = 0;
    private final int ID;
    private int age;
    private String gender;
    private String mail;
    private MemberType memberType;
    private MemberActivity memberActivity;
    private TrainingType trainingType;


    Member(String name, int age, String gender, String mail, MemberActivity memberActivity, MemberType memberType, TrainingType trainingType){
        idCounter++;
        ID = idCounter;

        this.name = name;
        this.age = age;
        this.mail = mail;
        this.gender = gender;
        this.memberActivity = memberActivity;
        this.memberType = memberType;
        this.trainingType = trainingType;

        //sæt MemberType enum baseret på alder(age)
    }


}
