package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;

import java.io.*;
import java.util.*;

public class Subcription {
    private double price;
    private boolean isPaid;


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

    public void showRestance() {
        Database d = new Database();
        Menu m = new Menu();
        d.databaseOutput();
        while (true) {
            System.out.println("""
                    1. Set restance
                    2. View restance
                    3. Tilbage""");

            userInput = Menu.getUserNumber(3);
            switch (userInput) {
                case "1":
                    // field for method - setRestance
                    break;
                case "2":
                    viewRestance();
                    break;
                case "3":
                    return;
            }
        }
    }

    public void setRestance() {


    }

    public void viewRestance() {
        List<String> membersInRestance = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/database.csv"))) {
            br.readLine(); // Skip header

            String line;
            System.out.println("=== Medlemmer i restance ===");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                MemberActivity activity = MemberActivity.valueOf(values[5].trim());

                if (activity == MemberActivity.RESTANCE) {
                    membersInRestance.add("Mobilnummer: " + values[0] + " Navn: " + values[1]);

                }
            }

            // Print results
            for (String name : membersInRestance) {
                System.out.println("I restance: " + name);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

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
                    totalPassiveRevenue += 500;
                } else {
                    switch (memberType) {
                        case JUNIOR -> totalJuniorRevenue += 1000;
                        case SENIOR -> totalSeniorRevenue += 1600;
                        case RETIREE -> totalRetireeRevenue += 1200;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Samlet årlig indtægt for passive medlemmer: " + totalPassiveRevenue + " DKK");
        System.out.println("Samlet årlig indtægt for Junior medlemmer: " + totalJuniorRevenue + " DKK");
        System.out.println("Samlet årlig indtægt for Senior medlemmer: " + totalSeniorRevenue + " DKK");
        System.out.println("Samlet årlig indtægt for Retiree medlemmer: " + totalRetireeRevenue + " DKK");
    }

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

        System.out.println("Samlet årlig indtægt: " + totalRevenue + " DKK");

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





