package funcionabilities.functional_aids.transactions;

class BankTransaction extends BankAcount {
    final double value;

    BankTransaction(BankAcount bank, double value) {
        super(bank);
        this.value = value;
    }

    public String toString() {
        return super.toString() + "\n\tBank Transaction: \n\tValue: " + value + "\n";
    }
}
