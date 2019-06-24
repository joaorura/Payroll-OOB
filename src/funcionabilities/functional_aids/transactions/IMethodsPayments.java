package funcionabilities.functional_aids.transactions;

public interface IMethodsPayments {
    double doPayment();

    IMethodsPayments clone() throws CloneNotSupportedException;
}
