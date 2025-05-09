package delfinen;

public class Menu {
    public static void main(String[] args) {
        SwimmingClub delfinen = new SwimmingClub();
        Database database = new Database();

        database.DatabaseOutput();

        delfinen.addNewMember();
    }
}
