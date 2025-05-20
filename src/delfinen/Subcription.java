package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;

import java.io.*;
import java.util.*;

public class Subcription {
    private double price;
    private boolean isPaid;

    //For Restance Method
    private static String userInput;

    private static final Scanner sc = new Scanner(System.in);

    private static int userInt;
    //

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    //Brugerinterface til Menu
    public void showRestance() {
        Database d = new Database();
        Menu m = new Menu();
        d.databaseOutput();
        while (true) {
            System.out.println("""
                    1. Set missing payer
                    2. View missing payers
                    3. Back""");

            userInput = Menu.getUserNumber(3);
            switch (userInput) {
                case "1":
                    setRestance();
                    break;
                case "2":
                    viewRestance();
                    break;
                case "3":
                    return;
            }
        }
    }

    // Bruges til at update Member Activity, til ACTIVE, PASSIVE ELLER RESTANCE, for kasseren.
    public void setRestance() {
        Database d = new Database();
        Menu m = new Menu();
        String userInputString;

        while (true) {
            System.out.println("""
                    1. Update Active
                    2. Update Passive
                    3. Update Restance
                    4. Back""");
            String userInput = Menu.getUserNumber(4);

            try {
                String rowIdentificer;

                switch (userInput) {
                    case "1":
                        System.out.println("Enter the phone number of the member to set as Active (xx xx xx xx): ");
                        rowIdentificer = sc.nextLine();

                        if (rowIdentificer.matches("\\d{8}")) {
                            rowIdentificer = rowIdentificer.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
                        }

                        if (!rowIdentificer.matches("\\d{2} \\d{2} \\d{2} \\d{2}")) {
                            System.out.println("Invalid format. Use XX XX XX XX.");
                            break;
                        }

                        if (d.memberUpdated(rowIdentificer)) {
                            d.changeDataByRow(rowIdentificer, "Member Activity", "ACTIVE");
                            System.out.println("Member status updated to ACTIVE");
                        } else {
                            System.out.println("No member found with that phone number.");
                        }
                        break;

                    case "2":
                        System.out.println("Enter the phone number of the member to set as PASSIVE (xx xx xx xx): ");
                        rowIdentificer = sc.nextLine();

                        if (rowIdentificer.matches("\\d{8}")) {
                            rowIdentificer = rowIdentificer.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
                        }

                        if (!rowIdentificer.matches("\\d{2} \\d{2} \\d{2} \\d{2}")) {
                            System.out.println("Invalid format. Use XX XX XX XX.");
                            break;
                        }

                        if (d.memberUpdated(rowIdentificer)) {
                            d.changeDataByRow(rowIdentificer, "Member Activity", "PASSIVE");
                            System.out.println("Member status updated to PASSIVE");
                        } else {
                            System.out.println("No member found with that phone number.");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the phone number of the member to set as RESTANCE (xx xx xx xx): ");
                        rowIdentificer = sc.nextLine();

                        if (rowIdentificer.matches("\\d{8}")) {
                            rowIdentificer = rowIdentificer.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1 $2 $3 $4");
                        }

                        if (!rowIdentificer.matches("\\d{2} \\d{2} \\d{2} \\d{2}")) {
                            System.out.println("Invalid format. Use XX XX XX XX.");
                            break;
                        }

                        if (d.memberUpdated(rowIdentificer)) {
                            d.changeDataByRow(rowIdentificer, "Member Activity", "RESTANCE");
                            System.out.println("Member status updated to RESTANCE");
                        } else {
                            System.out.println("No member found with that phone number.");
                        }
                        break;

                    case "4":
                        return;

                    default:
                        System.out.println("Invalid choice. Please select 1-4.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e);
            }
        }

    }

    //Viser brugere i Restance fra CSV fil
    public void viewRestance() {
        List<String> membersInRestance = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/database.csv"))) {
            br.readLine(); // Skip header

            String line;
            System.out.println("=== Members missing payment ===");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                MemberActivity activity = MemberActivity.valueOf(values[5].trim());

                if (activity == MemberActivity.RESTANCE) {
                    membersInRestance.add("Phone: " + values[0] + " Name: " + values[1]);

                }
            }

            // Print results
            for (String name : membersInRestance) {
                System.out.println("Missing payment: " + name);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    //Kan adskille de forskellige medlemstyper og viser, hvor meget de pågældende indbrginer af kontigent.
    public void totalRevenueByMemberType() {
        ArrayList<Member> revenueList = new ArrayList<>();
        int totalPassiveRevenue = 0;
        int totalJuniorRevenue = 0;
        int totalSeniorRevenue = 0;
        int totalRetireeRevenue = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/database.csv"))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String activityStr = values[5];
                String memberTypeStr = values[6];

                MemberActivity activity = MemberActivity.valueOf(activityStr);
                MemberType memberType = MemberType.valueOf(memberTypeStr);

                revenueList.add(new Member(activity, memberType));

                if (activity == MemberActivity.PASSIVE) {
                    totalPassiveRevenue += MemberType.PASSIVEPRICE.getPrice();
                } else {
                    switch (memberType) {
                        case JUNIOR -> totalJuniorRevenue += MemberType.JUNIOR.getPrice();
                        case SENIOR -> totalSeniorRevenue += MemberType.SENIOR.getPrice();
                        case RETIREE -> totalRetireeRevenue += MemberType.RETIREE.getPrice();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Yearly income for passive members: " + totalPassiveRevenue + " DKK");
        System.out.println("Yearly income for Junior members: " + totalJuniorRevenue + " DKK");
        System.out.println("Yearly income for Senior members: " + totalSeniorRevenue + " DKK");
        System.out.println("Yearly income for Retiree members: " + totalRetireeRevenue + " DKK");
    }

    //Viser sammenregenet kontigent
    public void totalRevenue() {
        ArrayList<Member> revenueList = new ArrayList<>();
        int totalRevenue = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/database.csv"))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String activityStr = values[5];
                String memberTypeStr = values[6];

                MemberActivity activity = MemberActivity.valueOf(activityStr);
                MemberType memberType = MemberType.valueOf(memberTypeStr);

                revenueList.add(new Member(activity, memberType));

                if (activity == MemberActivity.PASSIVE) {
                    totalRevenue += 500;
                } else {
                    switch (memberType) {
                        case JUNIOR -> totalRevenue += 1000;
                        case SENIOR -> totalRevenue += 1600;
                        case RETIREE -> totalRevenue += 1200;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Anually gross revenue: " + totalRevenue + " DKK");

    }


    //Regner pris for hvad medlemmet skal betale baseret på deres informationer.
    public void calculatePrice(Member member) {
        if (member.getMemberActivity() == MemberActivity.PASSIVE) {
            this.price = 500;
        } else {
            this.price = member.getMemberType().getPrice();
        }
    }
}





