package com.kce.bank.model;

public class BOMItem {
    public Product component;
    public int quantity;

    public BOMItem(Product component, int quantity) {
        this.component = component;
        this.quantity = quantity;
    }
}
