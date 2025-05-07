package delfinen;

public class Results {
    private String date;
    private double time;
    private int placement;
    private String competitionName;

    private enum TrainingType{
        TRAINING,
        COMPETITION;
    }

    private enum Discipline{
        FREESTYLE,
        BACKSTROKE,
        BREASTSTROKE,
        BUTTERFLY;
    }

    Results(String date, double time){
        this.date = date;
        this.time = time;

        //Sæt TrainingType til TRAINING member
    }

    Results(String date, double time, int placement, String competitionName){
        this.date = date;
        this.time = time;
        this.placement = placement;
        this.competitionName = competitionName;

        //Sæt TrainingType til COMPETITION memeber
    }

    public String getDate(){
        return date;
    }

    //husk getters og setters

    public void updateResult(){

    }

    public void viewRusults(){

    }
}
