package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;

public class Member {
    private String name;
    private String phoneNumber;
    private int age;
    private String gender;
    private String mail;
    private MemberType memberType;
    private MemberActivity memberActivity;
    private TrainingType trainingType;
    private Subcription sub;



    Member(String phoneNumber, String name, int age, String gender, String mail, MemberActivity memberActivity, TrainingType trainingType){


        this.name = name;
        this.age = age;
        this.mail = mail;
        this.gender = gender;
        this.memberActivity = memberActivity;
        this.trainingType = trainingType;
        this.phoneNumber = phoneNumber;

        if(age < 18){
            this.memberType = MemberType.JUNIOR;
        } else if (age >= 60) {
            this.memberType = MemberType.RETIREE;
        } else {
            this.memberType = MemberType.SENIOR;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
