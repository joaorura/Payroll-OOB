package main.functional_aids.sales;

import java.util.Map;

public interface ISalesList<T, H> {
    void addProduct(T identificator, H price);

    H removeProduct(T identificator);

    Map<T, H> getList();

    ISalesList clone();
}
