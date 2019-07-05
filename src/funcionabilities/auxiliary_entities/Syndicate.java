package funcionabilities.auxiliary_entities;

public class Syndicate {
    private final String indetificationSyndicate;
    private final double monthlyFee;

    public Syndicate(String indetificationSyndicate, double monthlyFee) {
        this.indetificationSyndicate = indetificationSyndicate;
        this.monthlyFee = monthlyFee;
    }

    public Syndicate clone() throws CloneNotSupportedException{
        return (Syndicate) super.clone();
    }

    public String getIndetification() {
        return indetificationSyndicate;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }
}
