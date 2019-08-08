package funcionabilities.functional_aids.transactions;

class Check extends BankTransaction {
    private final String name_destiny;
    private String identifier;

    Check(BankAccount bank, double value, String name_destiny) {
        super(bank, value);
        this.name_destiny = name_destiny;
        this.identifier = super.toString() + "\nName of destiny: " + name_destiny + "\t\n";;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
