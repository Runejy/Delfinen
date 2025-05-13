package delfinen;

import delfinen.Enums.TrainingType;
import delfinen.Enums.MemberActivity;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Database {
    FileWriter fileWriter;
    String filePath = "src/Files/database.csv";

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

    void changeDataByRow(String searchedPhoneNumber,String inputDataType, String inputData){
        try{
            fileWriter = new FileWriter(filePath);
            Scanner fileReader = new Scanner(new File(filePath));

            while(fileReader.hasNext()){
                String[] rowData = fileReader.nextLine().split(",");
                if(rowData[4].equals(searchedPhoneNumber)){
                    HashMap<String, String> rowAndColumnNames = new HashMap<String,String>();
                    rowAndColumnNames.put("Name", rowData[0]);
                    rowAndColumnNames.put("Age", rowData[1]);
                    rowAndColumnNames.put("Gender", rowData[2]);
                    rowAndColumnNames.put("Mail", rowData[3]);
                    rowAndColumnNames.put("Phonenumber", rowData[4]);
                    rowAndColumnNames.put("Member Activity", rowData[5]);
                    rowAndColumnNames.put("Member Type", rowData[6]);
                    rowAndColumnNames.put("Training Type", rowData[7]);

                    rowAndColumnNames.put(inputDataType, inputData);

                    fileWriter.write(
                            "\n" + rowAndColumnNames.get("Name") +
                                    "," + rowAndColumnNames.get("Age") +
                                    "," + rowAndColumnNames.get("Gender") +
                                    "," + rowAndColumnNames.get("Mail") +
                                    "," + rowAndColumnNames.get("Phonenumber") +
                                    "," + rowAndColumnNames.get("Member Activity") +
                                    "," + rowAndColumnNames.get("Member Type") +
                                    "," + rowAndColumnNames.get("Training Type")
                    );
                    fileWriter.close();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

     ArrayList<Member> getMemberArrayList(){
        ArrayList<Member> memberList = new ArrayList<>();

        try{
            Scanner fileReader = new Scanner(new File(filePath));

            while(fileReader.hasNext()){
                String[] rowData = fileReader.nextLine().split(",");

                if(rowData[1].matches("\\d+")){ //Tjekker udelukkende om rowData[1]/age består af ét eller flere cifre og ikke bogstaver

                    Member newMember = new Member(rowData[0], Integer.parseInt(rowData[1]),rowData[2],rowData[3],rowData[4],MemberActivity.valueOf(rowData[5].toUpperCase()),TrainingType.valueOf(rowData[7].toUpperCase()));

                    memberList.add(newMember);
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return memberList;
    }

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


                if(rowData[1].matches("\\d+")){ //Tjekker udelukkende om rowData[1]/age består af ét eller flere cifre og ikke bogstaver

                    Member newMember =  new Member(rowData[0], Integer.parseInt(rowData[1]),rowData[2],rowData[3],rowData[4],MemberActivity.valueOf(rowData[5].toUpperCase()),TrainingType.valueOf(rowData[7].toUpperCase()));

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

    //Method to print out the userInputted members from database
    void DatabaseOutput(){
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                System.out.printf("|%-15s", row[0]);
                System.out.printf("|%-15s", row[1]);
                System.out.printf("|%-15s", row[2]);
                System.out.printf("|%-30s", row[3]);
                System.out.printf("|%-20s", row[4]);
                System.out.printf("|%-20s", row[5]);
                System.out.printf("|%-20s", row[6]);
                System.out.printf("|%-20s", row[7]);

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