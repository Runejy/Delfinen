package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    public static void main(String[] args) throws IOException {
        SwimmingClub delfinen = new SwimmingClub();
        Database database = new Database();

        database.changeDataByRow("60 58 77 14", "Name", "Nynne Larsen");
        database.changeDataByRow("60 58 77 14", "Age", "1");
        database.changeDataByRow("60 58 77 14", "Mail", "@gmail.com");
        database.changeDataByRow("60 58 77 14", "Member Activity", "ACTIVE");
        database.changeDataByRow("60 58 77 14", "Member Type", "JUNIOR");
        database.changeDataByRow("60 58 77 14", "Training Type", "CASUAL");

        database.updateDatabase();

        print(database.memberList);
    }

    static void print(ArrayList<Member> memberList){
        for(Member member: memberList){
            System.out.println(member);
        }
    }
}
