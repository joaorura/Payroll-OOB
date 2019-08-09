package model;

public class BankTransaction extends BankAccount {
    @SuppressWarnings("CanBeFinal")
    public double value;

    private final String identifier;

    protected BankTransaction(BankAccount bank, double value) {
        super(bank);
        this.value = value;
        this.identifier = super.toString() + "\nBank Transaction: \n\t" + "\tValue: " + value + "\n";
    }

    public String toString() {
        return identifier;
    }
}
