package delfinen;

public class Member {
    private String name;
    private static int idCounter = 0;
    private final int ID;
    private int age;
    private String mail;

    private enum MemberType{
        JUNIOR,
        SENIOR,
        RETIRERE;
    }

    Member(String name, int age, String mail){
        idCounter++;
        ID = idCounter;

        this.name = name;
        this.age = age;
        this.mail = mail;

        //sæt MemberType enum baseret på alder(age)
    }


}
