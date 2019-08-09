package funcionabilities.functional_aids.transactions;

public class CheckPostOffices extends Check implements IMethodsPayments {
    private final String identifier;

    public CheckPostOffices(BankAccount bank, double value, String name, String address) {
        super(bank, value, name);
        this.identifier = super.toString() + "\n\tSend to address: " + address + "\n";
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (CheckPostOffices) super.clone();
    }

    @Override
    public void setValue(double value) {
        super.value = value;
    }
}
