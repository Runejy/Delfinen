package delfinen.Results;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;

public interface Result {
    String getPhoneNumber();
    String getSwimmerName();
    Team getTeam();
    String getDate();
    double getTime();
    Discipline getDiscipline();
    String toCSVfile();


    //String viewResults();  // Metode som returnerer en String
}
