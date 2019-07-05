package funcionabilities.functional_aids;

import java.util.HashMap;
import java.util.Map;

public class Debts {
    private  Map<String, Double> debt;

    public Debts() {
        debt = new HashMap<>();
    }

    public void addDebt(String service, double value) {
        debt.put(service, value);
    }

    public void removeDebt(String service, double value) {
        debt.remove(service, value);
    }

    public void clearDebts () { debt.clear();}

    public Double getValueDebt() {
        double allDebit = 0;
        for (double d : debt.values()) {
            allDebit += d;
        }

        return allDebit;
    }
}
