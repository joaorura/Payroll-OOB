package model;

public class BankTransaction extends BankAccount {

    private String identifier;

    protected BankTransaction(BankAccount bank, double value) {
        super(bank);
        this.identifier = super.toString() + "\nBank Transaction: \n\t" + "\tValue: " + value + "\n";
    }

    public void setValue(double value) {
        this.identifier =super.toString() + "\nBank Transaction: \n\t" + "\tValue: " + value + "\n";
    }

    public String toString() {
        return identifier;
    }
}
