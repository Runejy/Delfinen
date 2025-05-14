package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.TrainingType;

import java.io.*;
import java.util.Scanner;


public class EliteSwimmer extends Member {
    private String team;

    private enum discipline {

    }

    EliteSwimmer(String name, int age, String gender, String mail, String phoneNumber, MemberActivity memberActivity, TrainingType trainingType) {
        super(name, age, gender, mail, phoneNumber, memberActivity, trainingType);
    }

    public void addTrainer() {

    }

    public void addDiscipline() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Hvilken svømme disciplin, svømmer du i?");
        System.out.println("1. Crawl " + "\n 2. Butterfly" + "\n 3. Freestyle" + "\n 4. Breaststroke");
        int valg = input.nextInt();
        int discipline = input.nextInt();

        String disciplin = switch (valg) {
            case 1 -> "Crawl";
            case 2 -> "Butterfly";
            case 3 -> "Freestyle";
            case 4 -> "Breaststroke";
            default -> "Ugyldigt valg!";
        };

//            File inputX = new File("database.csv");
//            File wrt = new File("temp.csv");
//            boolean fundet = false;
//
//
//            try (BufferedReader reader = new BufferedReader(new FileReader(inputX));
//                 BufferedWriter writer = new BufferedWriter(new FileWriter(wrt))) {

//                String linje;
//                while ((linje = reader.readLine()) != null) {
//                    String[] dele = linje.split(",", -1);
//
//                    if (dele[0].trim().equalsIgnoreCase(navn)) {
//                        // Vi antager: format = navn, alder, disciplin
//                        if (dele.length < 3) {
//                            dele = Arrays.copyOf(dele, 3); // udvid array hvis disciplin mangler
//                        }
//                        dele[2] = disciplin;
//                        fundet = true;
//                        System.out.println("Disciplin opdateret til: " + disciplin);
//                    }
//
//                    writer.write(String.join(",", dele));
//                    writer.newLine();
//                }
//            }
//                writer.write(discipline);
//                    writer.newLine();
//                    System.out.println("Dit valg er gemt i ´databasen.csv´");
//
//                } catch (IOException e) {
//                    System.out.println("Fejl!");
//                }
    }

    ;

//            public void updateProfile () {
//
//            }
//
//            public void addResult () {
//
//            }


}


