package funcionabilities.functional_aids.transactions;

public interface IMethodsPayments {
    double doPayment();
    String toString();

    IMethodsPayments clone() throws CloneNotSupportedException;
}
