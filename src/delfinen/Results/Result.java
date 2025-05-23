package delfinen.Results;

import delfinen.Enums.Discipline;
import delfinen.Enums.Team;
import delfinen.Enums.TrainingType;

public interface Result {
    TrainingType getResultType() ;
    String getPhoneNumber();
    String getSwimmerName();
    Team getTeam();
    String getDate();
    double getTime();
    Discipline getDiscipline();
    String toCSVfile();


    //String viewResults();  // Metode som returnerer en String
}
