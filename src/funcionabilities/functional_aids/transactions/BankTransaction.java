package funcionabilities.functional_aids.transactions;

class BankTransaction extends BankAcount {
    final double value;

    BankTransaction(BankAcount bank, double value) {
        super(bank);
        this.value = value;
    }

    String getInfo() {
        return "Bank Transaction: \n\t" + super.getInfo() + "\tValue: " + value + "\n";
    }
}
