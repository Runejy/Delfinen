package delfinen;

public class Menu {
    public static void main(String[] args) {
        SwimmingClub delfinen = new SwimmingClub();
        Database database = new Database();

        System.out.println(delfinen.members.get(1).getGender());

        delfinen.updateMember();

    }
}
