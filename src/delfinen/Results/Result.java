package delfinen.Results;

import delfinen.Enums.Discipline;

public interface Result {
    String getDate();
    double getTime();
    Discipline getDiscipline();
    String toCSVfile();


    //String viewResults();  // Metode som returnerer en String
}
