package main.auxiliary_entities;

public class Syndicate implements ISyndicates {
    private String indetificationSyndicate;
    private double monthlyFee;

    public Syndicate(String indetificationSyndicate, double monthlyFee) {
        this.indetificationSyndicate = indetificationSyndicate;
        this.monthlyFee = monthlyFee;
    }

    public ISyndicates clone() {
        return null;
    }

    public double costSyndicate() {
        return monthlyFee;
    }

    public String getIndetificationSyndicate() {
        return indetificationSyndicate;
    }
}
