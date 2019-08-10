package model.problematics;

public class Syndicate implements Cloneable {
    private final String identifierSyndicate;
    private final double monthlyFee;

    private final String stringIdentifier;

    public Syndicate(String identifierSyndicate, double monthlyFee) {
        this.identifierSyndicate = identifierSyndicate;
        this.monthlyFee = monthlyFee;
        this.stringIdentifier = "\n\tIdentification of Syndicate: " + identifierSyndicate  + "\n\tMonthly fee: " + monthlyFee + "\n";
    }

    public String getIdentifier() {
        return identifierSyndicate;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public Syndicate clone() throws CloneNotSupportedException {
        return (Syndicate) super.clone();
    }

    public String toString() {
        return stringIdentifier;
    }
}
