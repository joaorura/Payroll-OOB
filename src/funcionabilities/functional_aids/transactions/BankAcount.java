package funcionabilities.functional_aids.transactions;

public  class BankAcount {
    protected String name;
    protected String sourceAcount;
    protected String identification;

    public BankAcount(BankAcount bank) {
        this.name = bank.name;
        this.sourceAcount = bank.sourceAcount;
        this.identification = bank.identification;
    }

    public BankAcount(String name, String sourceAcount, String identification) {
        this.name = name;
        this.sourceAcount = sourceAcount;
        this.identification = identification;
    }

    public String getInfo() {
        return "Source Acount: " + sourceAcount + "\n";
    }
}
