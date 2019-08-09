package model.problematics;

public interface IMethodsPayments {
    String toString();

    IMethodsPayments clone() throws CloneNotSupportedException;

    void setValue(double value);
}
