package model.problematics;

public class Syndicate implements Cloneable {
    private final String identifierSynd;
    private final double monthlyFee;

    private final String stringIndentifier;

    public Syndicate(String identifierSynd, double monthlyFee) {
        this.identifierSynd = identifierSynd;
        this.monthlyFee = monthlyFee;
        this.stringIndentifier = "\n\tIdentification of Syndicate: " + identifierSynd  + "\n\tMonthly fee: " + monthlyFee + "\n";
    }

    public String getIdentifier() {
        return identifierSynd;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public Syndicate clone() throws CloneNotSupportedException {
        return (Syndicate) super.clone();
    }

    public String toString() {
        return stringIndentifier;
    }
}
