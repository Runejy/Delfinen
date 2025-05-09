package delfinen;

import delfinen.Enums.TrainingType;
import delfinen.Enums.MemberActivity;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
                            "," + newMember.getMemberActivity() +
                            "," + newMember.getMemberType() +
                            "," + newMember.getTrainingType()
            );

            fileWriter.close();
        } catch(IOException e){
            System.out.println("IOException");
        }
    }

     ArrayList<Member> getMemberArrayList(){
        ArrayList<Member> memberList = new ArrayList<>();
        Scanner fileReader = new Scanner(filePath);

            while(fileReader.hasNext()){
                String[] rowData = fileReader.nextLine().split(",");
                Member newMember = new Member(rowData[0],Integer.parseInt(rowData[1]),rowData[2],rowData[3],MemberActivity.valueOf(rowData[4].toUpperCase()),TrainingType.valueOf(rowData[6].toUpperCase()));

                memberList.add(newMember);
            }
        return memberList;
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