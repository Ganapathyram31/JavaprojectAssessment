package com.kce.bank.model;


public class WorkOrder {
    public String id;
    public Product product;
    public int quantity;
    public String status; 

    public WorkOrder(String id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.status = "CREATED";
    }
}
