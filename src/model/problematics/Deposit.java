package model.problematics;

import model.BankAccount;
import model.BankTransaction;

public class Deposit extends BankTransaction implements IMethodsPayments {
    private final String identifier;

    public Deposit(BankAccount bank, double value, String send) {
        super(bank, value);
        this.identifier = super.toString() + "Account destiny: " + send;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public IMethodsPayments clone() throws CloneNotSupportedException {
        return (Deposit) super.clone();
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
    }
}
