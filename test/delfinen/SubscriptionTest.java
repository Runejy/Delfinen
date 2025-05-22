package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;
import delfinen.Enums.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static delfinen.Enums.MemberType.*;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {


    @Test
    void calculatePrice() {
        Subscription sub = new Subscription();
        ArrayList<Member> revenueList = new ArrayList<>();
        Member yrsa = new Member("34543212", "Yrsa Larsen", 90, "Female", "yrsal@gmail.com", MemberActivity.PASSIVE, TrainingType.CASUAL);
        Member hans = new Member("67543219", "Daniel Larsen", 29, "Male", "daniellar@gmail.com", MemberActivity.ACTIVE, TrainingType.COMPETITION);

        sub.calculatePrice(yrsa);
        assertEquals(500, sub.getPrice());

        sub.calculatePrice(hans);
        assertEquals(1600, sub.getPrice());

        //TEST AF AT ALDER AFGØR MEMBER TYPE OG PRIS
        assertEquals(MemberType.RETIREE, yrsa.getMemberType());
        assertEquals(MemberActivity.PASSIVE, yrsa.getMemberActivity());

        assertEquals(SENIOR, hans.getMemberType());
        assertEquals(MemberActivity.ACTIVE, hans.getMemberActivity());
        assertEquals(1600, hans.getMemberType().getPrice());

        assertEquals(1600, SENIOR.getPrice());
        assertEquals(1200, RETIREE.getPrice());
        assertEquals(1000, JUNIOR.getPrice());
        assertEquals(500, PASSIVEPRICE.getPrice());

    }
}