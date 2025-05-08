package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;

public class Menu {
    public static void main(String[] args) {
        SwimmingClub delfinen = new SwimmingClub();
        Database database = new Database();

        database.DatabaseOutput();
    }
}
