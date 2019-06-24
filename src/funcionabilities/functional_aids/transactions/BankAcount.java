package funcionabilities.functional_aids.transactions;

public abstract class BankAcount {
    private String sourceAcount;

    public BankAcount(String sourceAcount) {
        this.sourceAcount = sourceAcount;
    }

    public String getInfo() {
        return "Source Acount: " + sourceAcount + "\n";
    }
}
