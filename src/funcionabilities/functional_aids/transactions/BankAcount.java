package funcionabilities.functional_aids.transactions;

public  class BankAcount {
    final String name;
    private final String sourceAcount;
    private final String identification;

    BankAcount(BankAcount bank) {
        this.name = bank.name;
        this.sourceAcount = bank.sourceAcount;
        this.identification = bank.identification;
    }

    public BankAcount(String name, String sourceAcount, String identification) {
        this.name = name;
        this.sourceAcount = sourceAcount;
        this.identification = identification;
    }

    String getInfo() {
        return "Source Acount: " + sourceAcount + "\n";
    }
}
