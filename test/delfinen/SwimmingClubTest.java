package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SwimmingClubTest {

    @Test
    void addNewMember() {
        Member memberNew = new Member("34543212", "Yrsa Larsen", 90, "Female", "yrsal@gmail.com", MemberActivity.PASSIVE, TrainingType.CASUAL);

        //TEST AF AT ALDER AFGØR MEMBER TYPE OG PRIS
        assertEquals(MemberType.RETIREE, memberNew.getMemberType());
        assertEquals(1200, memberNew.getMemberType().getPrice());

        //ANDRE TEST
        assertEquals("Yrsa Larsen", memberNew.getName());
        assertEquals("Female", memberNew.getGender());
        assertEquals("yrsal@gmail.com", memberNew.getMail());
        assertEquals(MemberActivity.PASSIVE, memberNew.getMemberActivity());
        assertEquals(TrainingType.CASUAL, memberNew.getTrainingType());
        assertEquals(1200, MemberType.RETIREE.getPrice());
        assertEquals(1600, MemberType.SENIOR.getPrice());


    }

    @Test
    void addTrainer() {
        Trainer testTrainer = new Trainer("Test Test");
        assertEquals("Test Test", testTrainer.getName());

    }

    @Test
    void listOfTrainers() {
        SwimmingClub club = new SwimmingClub();
        ArrayList<String> trainers = club.getTrainersFromFile();

        assertTrue(trainers.contains("Jens Hansen"));
    }

}