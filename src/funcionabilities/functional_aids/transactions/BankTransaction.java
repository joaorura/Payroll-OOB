package funcionabilities.functional_aids.transactions;

class BankTransaction extends BankAccount {
    @SuppressWarnings("CanBeFinal")
    double value;

    private String identifier;

    BankTransaction(BankAccount bank, double value) {
        super(bank);
        this.value = value;
        this.identifier = super.toString() + "\nBank Transaction: \n\t" + "\tValue: " + value + "\n";
    }

    public String toString() {
        return identifier;
    }
}
