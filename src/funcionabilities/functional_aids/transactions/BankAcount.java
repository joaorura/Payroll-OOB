package funcionabilities.functional_aids.transactions;

@SuppressWarnings("CanBeFinal")
public class BankAcount implements Cloneable {
    private String name;
    private String sourceAcount;
    private String identification;

    public BankAcount(String name, String sourceAcount, String identification) {
        this.name = name;
        this.sourceAcount = sourceAcount;
        this.identification = identification;
    }

    BankAcount(BankAcount bk) {
        name = bk.name;
        sourceAcount = bk.sourceAcount;
        identification = bk.identification;
    }

    public String toString() {
        return "Source Acount: " + sourceAcount + "\n";
    }
}
