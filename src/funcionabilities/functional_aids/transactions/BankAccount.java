package funcionabilities.functional_aids.transactions;

@SuppressWarnings("CanBeFinal")
public class BankAccount implements Cloneable {
    private String name;
    private String sourceAcount;
    private String identification;

    private String stringIdentifier;

    public BankAccount(String name, String sourceAcount, String identification) {
        this.name = name;
        this.sourceAcount = sourceAcount;
        this.identification = identification;
        this.stringIdentifier = "Source Acount: " + sourceAcount + "\n";
    }

    public BankAccount(BankAccount bk) {
        this(bk.name, bk.sourceAcount, bk.identification);
    }

    @Override
    public String toString() {
        return stringIdentifier;
    }
}
