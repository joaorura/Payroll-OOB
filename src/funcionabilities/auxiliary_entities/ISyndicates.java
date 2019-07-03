package funcionabilities.auxiliary_entities;

public interface ISyndicates {
    ISyndicates clone() throws CloneNotSupportedException;

    double costSyndicate();

    String toString();
}
