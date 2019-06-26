package funcionabilities.auxiliary_entities;

public class Syndicate implements ISyndicates {
    private String indetificationSyndicate;
    private double monthlyFee;

    public Syndicate(String indetificationSyndicate, double monthlyFee) {
        this.indetificationSyndicate = indetificationSyndicate;
        this.monthlyFee = monthlyFee;
    }

    public ISyndicates clone() throws CloneNotSupportedException{
        return (Syndicate) super.clone();
    }

    public double costSyndicate() {
        return monthlyFee;
    }

    public String getIndetificationSyndicate() {
        return indetificationSyndicate;
    }
}