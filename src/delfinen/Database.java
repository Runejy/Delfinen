package delfinen;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    File database = new File("/Files/database.csv");

    Database(){
        try{
            FileWriter fileWriter = new FileWriter(database);
        } catch(IOException e){

        }
    }
    //Method to print out the userInputted members from database
    void DatabaseOutput(){
        String file = "/Files/database.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                for(String index : row) {
                    System.out.printf("%-15s", index);
                }
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

    //Metoden for at printe kune navne ud på svømmerne i klubben
    public void listOfMembers() {
        String path = "/Files/database.csv";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
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






