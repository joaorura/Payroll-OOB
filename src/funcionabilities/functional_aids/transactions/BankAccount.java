package funcionabilities.functional_aids.transactions;

public class BankAccount implements Cloneable {
    private final String name;
    private final String sourceAccount;
    private final String identifier;

    private final String stringIdentifier;

    public BankAccount(String name, String sourceAccount, String identifier) {
        this.name = name;
        this.sourceAccount = sourceAccount;
        this.identifier = identifier;
        this.stringIdentifier = "Name: " + name + "\n" +
                "Source Account: " + sourceAccount + "\n" +
                "Identifier: " + identifier + "\n";
    }

    @SuppressWarnings("CopyConstructorMissesField")
    BankAccount(BankAccount bk) {
        this(bk.name, bk.sourceAccount, bk.identifier);
    }

    @Override
    public String toString() {
        return stringIdentifier;
    }
}
