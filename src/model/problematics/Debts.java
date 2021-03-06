package model.problematics;

import java.util.Hashtable;

public class Debts implements Cloneable {
    private Hashtable<String, Double> debt;
    private final String identifier;

    Debts() {
        debt = new Hashtable<>();
        identifier = "\nDebt it is of: " + getValueDebt() + "\n";
    }

    double calculate(double tempSalary) {
        double debts = getValueDebt();
        clearDebts();

        if (tempSalary - debts < 0) {
            addDebt("Company Debit", debts % tempSalary);
            tempSalary = 0;
        } else tempSalary -= debts;

        return tempSalary;
    }

    public void addDebt(String service, double value) {
        debt.put(service, value);
    }

    public void removeDebt(String service) {
        debt.remove(service);
    }

    private void clearDebts() {
        debt.clear();
    }

    Double getValueDebt() {
        double allDebit = 0;
        for (double d : debt.values()) {
            allDebit += d;
        }

        return allDebit;
    }

    public String toString() {
        return identifier;
    }

    public Debts clone() throws CloneNotSupportedException {
        Debts item = (Debts) super.clone();
        //noinspection unchecked
        item.debt = (Hashtable<String, Double>) this.debt.clone();

        return item;
    }
}
