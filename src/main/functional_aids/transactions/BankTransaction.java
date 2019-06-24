package main.functional_aids.transactions;

public class BankTransaction extends BankAcount {
    protected double value;

    public BankTransaction(String source, double value) {
        super(source);
        this.value = value;
    }

    public String getInfo() {
        return "Bank Transaction: \n\t" + super.getInfo() + "\tValue: " + value + "\n";
    }
}
