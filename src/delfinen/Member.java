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
    private Subcription sub;



    Member(String name, int age, String gender, String mail, MemberActivity memberActivity, TrainingType trainingType){
        idCounter++;
        ID = idCounter;

        this.name = name;
        this.age = age;
        this.mail = mail;
        this.gender = gender;
        this.memberActivity = memberActivity;
        this.trainingType = trainingType;

        if(age < 40){
            this.memberType = MemberType.JUNIOR;
        } else if (age < 60) {
            this.memberType = MemberType.SENIOR;
        } else {
            this.memberType = MemberType.RETIREE;
        }

        //sæt MemberType enum baseret på alder(age)

        //Opretter en subscription når medlem bliver oprettet.
        Subcription sub = new Subcription();
        sub.calculatePrice(this);
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public MemberActivity getMemberActivity() {
        return memberActivity;
    }

    public void setSubcription(Subcription sub){
            this.sub = sub;
    }
    public Subcription getSubscription(){
            return sub;
    }

    public void setMemberActivity(MemberActivity memberActivity) {
        this.memberActivity = memberActivity;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }
}
