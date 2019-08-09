package model;

@SuppressWarnings("CanBeFinal")
public class CheckHands extends Check implements IMethodsPayments {

    private String identifier;

    public CheckHands(BankAccount bank, double value, String name, int ids) {
        super(bank, value, name);
        this.identifier = super.toString() + "\nId to employee: " + ids + "\n\t";
    }

    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (CheckHands) super.clone();
    }

    @Override
    public void setValue(double value) {
        super.value = value;
    }

    public String toString() {
        return identifier;
    }
}
