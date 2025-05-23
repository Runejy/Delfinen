package delfinen.Enums;

public enum MemberType {
    JUNIOR(1000.0),
    SENIOR(1600.0),
    RETIREE(1600*0.75),
    PASSIVEPRICE(500.0);

    private double price;

    MemberType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

