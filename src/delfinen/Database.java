package delfinen;

import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;
import delfinen.Enums.MemberActivity;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Database {
    FileWriter fileWriter;
    String filePath = "src/Files/database.csv";
    ArrayList<Member> memberList = getMemberArrayList();

    void print(){
        for(Member member : memberList){
            System.out.println(member);
        }
    }

    void inputNewMemberData(Member newMember){
        try{
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write(
                                "\n" + newMember.getName() +
                            "," + newMember.getAge() +
                            "," + newMember.getGender() +
                            "," + newMember.getMail() +
                                "," + newMember.getPhoneNumber() +
                            "," + newMember.getMemberActivity() +
                            "," + newMember.getMemberType() +
                            "," + newMember.getTrainingType()
            );
            fileWriter.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    void changeDataByRow(String searchedPhoneNumber, String dataKey, String dataValue){
        for(Member member : memberList){
            if(member.getPhoneNumber().equalsIgnoreCase(searchedPhoneNumber)){
                switch(dataKey){
                    case "Telephone":
                        member.setPhoneNumber(dataValue);
                        break;
                    case "Name":
                        member.setName(dataValue);
                        break;
                    case "Age":
                        member.setAge(Integer.parseInt(dataValue));
                        break;
                    case "Gender":
                        member.setGender(dataValue);
                        break;
                    case "Mail":
                        member.setMail(dataValue);
                        break;
                    case "Member Activity":
                        member.setMemberActivity(MemberActivity.valueOf(dataValue));
                        break;
                    case "Member Type":
                        member.setMemberType(MemberType.valueOf(dataValue));
                        break;
                    case "Training Type":
                        member.setTrainingType(TrainingType.valueOf(dataValue));
                        break;
                }
            }
        }
    }

    void updateDatabase(){
        try{
            fileWriter = new FileWriter(filePath);

            String data = "Telephone,Name,Age,Gender,Mail,Member Activity,Member Type,Training Type";
            for(Member member : memberList){
                data += "\n" + member.getPhoneNumber() +
                        "," + member.getName() +
                        "," + member.getAge() +
                        "," + member.getGender() +
                        "," + member.getMail() +
                        "," + member.getMemberActivity() +
                        "," + member.getMemberType() +
                        "," + member.getTrainingType();
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    //INPUT ELITE DATA TO CSV FILE
    void inputNewEliteData(EliteSwimmer newEliteSwimmer){
        try {
            fileWriter = new FileWriter("src/Files/EliteSwimmer.csv", true);

            StringBuilder disciplineString = new StringBuilder();
            for (int i = 0; i < newEliteSwimmer.getDisciplines().size(); i++) {
                disciplineString.append(newEliteSwimmer.getDisciplines().get(i));
                if (i < newEliteSwimmer.getDisciplines().size() - 1) {
                    disciplineString.append(", ");
                }
            }

            fileWriter.write(
                    System.lineSeparator() +
                            newEliteSwimmer.getName() + ";" +
                            newEliteSwimmer.getTrainer() + ";" +
                            newEliteSwimmer.getTeam() + ";" +
                            disciplineString.toString()
            );
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to print out the userInputted Elite members from database
    void databaseOutputEliteMember(){
        BufferedReader brReader = null;
        String line;

        try {
            brReader = new BufferedReader(new FileReader("src/Files/EliteSwimmer.csv"));
            System.out.printf("%-20s%-20s%-15s%-40s%n", "Swimmer Name", "Trainer", "Team", "Disciplines");

            while ((line = brReader.readLine()) != null) {
                // Skip header if present
                if (line.startsWith("SwimmerName")) {
                    continue;
                }

                String[] row = line.split(";", -1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (brReader != null) brReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ARRAYLIST OVER MEMBERS IN DATABASE
     ArrayList<Member> getMemberArrayList(){
        try{
            ArrayList<Member> memberList = new ArrayList<>();
            Scanner fileReader = new Scanner(new File(filePath));
            while(fileReader.hasNextLine()){
                String[] rowData = fileReader.nextLine().split(",");
                if(rowData[2].matches("\\d+")){ //Tjekker udelukkende om rowData[1]/age består af ét eller flere cifre og ikke bogstaver

                    Member newMember = new Member(rowData[0], rowData[1],Integer.parseInt(rowData[2]),rowData[3],rowData[4],MemberActivity.valueOf(rowData[5].toUpperCase()),TrainingType.valueOf(rowData[7].toUpperCase()));

                    memberList.add(newMember);

                }
            }
            return memberList;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    // ARRAYLIST OVER ELITE MEMBERS IN DATABASE
    ArrayList<Member> getEliteMemberArrayList(){
        ArrayList<Member> eliteMemberList = new ArrayList<>();

        try{
            Scanner fileReader = new Scanner(new File(filePath));

            //INDTIL VI KAN SLETTE I CVS FIL:
            int lineCounter = 0;

            while(fileReader.hasNext()){
                String[] rowData = fileReader.nextLine().split(",");
                lineCounter++;

                // Spring de første 10 linjer over
                if (lineCounter <= 10) {
                    continue;
                }


                if(rowData[2].matches("\\d+")){ //Tjekker udelukkende om rowData[1]/age består af ét eller flere cifre og ikke bogstaver

                    Member newMember =  new Member(rowData[0], rowData[1],Integer.parseInt(rowData[2]),rowData[3],rowData[4],MemberActivity.valueOf(rowData[5].toUpperCase()),TrainingType.valueOf(rowData[7].toUpperCase()));

                    if (newMember.getTrainingType() == TrainingType.COMPETITION) {
                        eliteMemberList.add(newMember);
                    }
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return eliteMemberList;
    }

    //ADD TRAINER TO trainers.csv

    void inputNewTrainer(Trainer newTrainer){
        try{
            fileWriter = new FileWriter("src/Files/trainers.csv", true);
            fileWriter.write(
                    "\n" + newTrainer.getName()
            );
            fileWriter.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    //Method to print out the userInputted members from database
    void databaseOutput(){
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                System.out.printf("%-15s", row[0]);
                System.out.printf("%-15s", row[1]);
                System.out.printf("%-15s", row[2]);
                System.out.printf("%-30s", row[3]);
                System.out.printf("%-20s", row[4]);
                System.out.printf("%-20s", row[5]);
                System.out.printf("%-20s", row[6]);
                System.out.printf("%-20s", row[7]);

                System.out.println();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //Metoden for at printe kun navne ud på svømmerne i klubben
    public void listOfMembers() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values[0]);
            }
        } catch (FileNotFoundException z) {
            z.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}