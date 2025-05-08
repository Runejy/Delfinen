package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;

public class EliteSwimmer extends Member{
    private String team;

    private enum discipline{

    }

    EliteSwimmer(String name, int age, String gender, String mail, MemberActivity memberActivity, TrainingType trainingType){
        super(name, age, gender, mail, memberActivity, trainingType);
    }

    public void addTrainer(){

    }

    public void addDiscipline(){

    }

    public void updateProfile(){

    }
}
