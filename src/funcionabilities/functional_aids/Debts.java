package funcionabilities.functional_aids;

import java.util.HashMap;
import java.util.Map;

public class Debts {
    private  Map<String, Double> debt;
    double leftOvers;

    public Debts() {
        debt = new HashMap<>();
    }

    public double calculate(double tempSalary) {
        double debts = getValueDebt();
        clearDebts();

        if(tempSalary - debts < 0) {
            addDebt("Company Debit", debts % tempSalary);
            tempSalary = 0;
        }
        else tempSalary -= debts;

        return tempSalary;
    }

    public void addDebt(String service, double value) {
        debt.put(service, value);
    }

    public void removeDebt(String service) { debt.remove(service); }

    public void clearDebts () { debt.clear();}

    public Double getValueDebt() {
        double allDebit = 0;
        for (double d : debt.values()) {
            allDebit += d;
        }

        return allDebit;
    }
}
