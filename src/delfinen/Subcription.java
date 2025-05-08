package delfinen;

import delfinen.Enums.MemberActivity;
import delfinen.Enums.MemberType;

public class Subcription {
    private double price;
    private boolean isPaid;

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public void viewMissingPayers(){

    }

    public void totalRevenueByMemberType(){

    }

    public void totalRevenue(){

    }

    public void updateMemberPayment(){

    }
//Regner pris for hvad medlemmet skal betale baseret på deres informationer.
    public void calculatePrice(Member member){
        if(member.getMemberActivity() == MemberActivity.PASSIVE) {
            this.price = 500;
        }else{
            this.price = member.getMemberType().getPrice();
            }
        }
    }

