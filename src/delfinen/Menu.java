package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;

import java.io.IOException;

public class Menu {
    public static void main(String[] args) throws IOException {
        SwimmingClub delfinen = new SwimmingClub();
        Database database = new Database();
        delfinen.updateMember();

        EliteSwimmer haala = new EliteSwimmer("Yacqub", 21, "male","YAcqubgmail", "20807993", MemberActivity.ACTIVE, TrainingType.CASUAL  );
        haala.addDiscipline();



    }
}
