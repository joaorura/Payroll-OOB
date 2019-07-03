package funcionabilities.functional_aids.transactions;

class Check extends BankTransaction {
    private final String name_destiny;

    Check(BankAcount bank, double value, String name_destiny) {
        super(bank, value);
        this.name_destiny = name_destiny;
    }

    String getInfo() {
        return super.toString() + "\nName of destiny: " + name_destiny + "\t\n";
    }

}
