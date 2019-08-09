package model.problematics;

public class Commissioned extends Salaried {
    private final SaleList sales;
    private final double ratioSales;
    private final String identifier;

    public Commissioned(String address, String name, int personal_id, Syndicate personalSyndicate,
                        IMethodsPayments typePayment, PaymentBills personalIPayment, PointCalendar worker,
                        Double salary, Double ratioSales) {
        super(address, name, personal_id, personalSyndicate, typePayment, personalIPayment, worker, salary);
        this.sales = new SaleList();
        this.ratioSales = ratioSales;
        this.identifier = super.toString() + "Ratio Sales: " + ratioSales + "\nValues of Sales: " + sales.getAllValues() + "\n";
    }

    public SaleList getSales() {
        return sales;
    }

    public double getRatioSales() {
        return ratioSales;
    }

    @Override
    public double attMoney() {
        double tempSalary = super.attMoney();
        tempSalary += sales.getAllValues();
        sales.clearList();
        return tempSalary;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public Commissioned clone() throws CloneNotSupportedException {
        return (Commissioned) super.clone();
    }
}
