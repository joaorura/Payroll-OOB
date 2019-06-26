package funcionabilities.functional_aids.transactions;

public class BankTransaction extends BankAcount {
    protected double value;

    public BankTransaction(BankAcount bank, double value) {
        super(bank);
        this.value = value;
    }

    public String getInfo() {
        return "Bank Transaction: \n\t" + super.getInfo() + "\tValue: " + value + "\n";
    }
}
