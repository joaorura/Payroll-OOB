package funcionabilities;

import funcionabilities.auxiliary_entities.ISyndicates;
import funcionabilities.functional_aids.calendar.IPointCalendar;
import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.sales.ISalesList;
import funcionabilities.functional_aids.sales.SaleList;
import funcionabilities.functional_aids.transactions.IMethodsPayments;

import java.util.Map;

public class Commisioned extends Salaried {
    private ISalesList sales;
    private double ratioSales;

    public Commisioned(String adress, String name, long personal_id, ISyndicates personalSyndicate,
                       IMethodsPayments typePayment, ITypePayments personalIPayment, IPointCalendar worker,
                       Double salary, Double ratioSales) {
        super(adress, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker, salary);
        this.sales = new SaleList();
        this.ratioSales = ratioSales;
    }

    public Map getSaleList() {
        return sales.getList();
    }
}
