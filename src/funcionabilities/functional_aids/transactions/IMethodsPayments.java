package funcionabilities.functional_aids.transactions;

public interface IMethodsPayments {
    String toString();

    IMethodsPayments clone() throws CloneNotSupportedException;

    void setValue(double value);
}
