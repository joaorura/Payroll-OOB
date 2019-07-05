package funcionabilities.functional_aids.transactions;

class BankTransaction extends BankAcount {
    double value;

    BankTransaction(BankAcount bank, double value) {
        super(bank);
        this.value = value;
    }

    public String toString() {
        return super.toString() + "\nBank Transaction: \n\t" + "\tValue: " + value + "\n";
    }

    public void setValue(double value) {
        this.value = value;
    }
}
