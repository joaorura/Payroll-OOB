package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.sales.ISalesList;
import funcionabilities.functional_aids.sales.SaleList;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

public class Commisioned extends Salaried {
    private final ISalesList sales;
    private final double ratioSales;

    public Commisioned(String adress, String name, int personal_id, ISyndicates personalSyndicate,
                       IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker,
                       Double salary, Double ratioSales) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker, salary);
        this.sales = new SaleList();
        this.ratioSales = ratioSales;
    }

    public double attMoney() {
        double temp_salary = super.attMoney();
        temp_salary += sales.getSales() * ratioSales;
        sales.clearSales();
        return temp_salary;
    }

    public ISalesList<String, Double> getSales() {
        return sales;
    }
}
