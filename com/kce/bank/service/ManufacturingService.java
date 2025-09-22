package com.kce.bank.service;

import com.kce.bank.model.*;
import com.kce.bank.exception.*;
import java.util.ArrayList;


public class ManufacturingService {
    public ArrayList<Product> products = new ArrayList<>();
    public ArrayList<BOM> boms = new ArrayList<>();
    public ArrayList<WorkOrder> workOrders = new ArrayList<>();
    public Warehouse warehouse;

    public ManufacturingService(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product findProduct(String id) {
        for (Product p : products)
            if (p.id.equals(id))
            	return p;
                return null;
    }

    public WorkOrder findWorkOrder(String id) {
        for (WorkOrder w : workOrders)
            if (w.id.equals(id)) 
            	return w;
                return null;
    }

    public BOM findBOM(Product p) {
        for (BOM b : boms)
            if (b.finishedProduct == p) 
            	return b;
        return null;
    }
    
     public void issueMaterials(WorkOrder wo) throws InsufficientStockException, InvalidWorkOrderException {
        if (wo.status.equals("COMPLETED")) {
            throw new InvalidWorkOrderException("Work order already completed!");
        }

        BOM bom = findBOM(wo.product);
        if (bom == null) {
            throw new InvalidWorkOrderException("No BOM defined for product " + wo.product.name);
        }

        for (BOMItem item : bom.items) {
            int need = item.quantity * wo.quantity;
            if (item.component.stock < need) {
                throw new InsufficientStockException("Not enough " + item.component.name);
            }
        }

        for (BOMItem item : bom.items) {
            item.component.removeStock(item.quantity * wo.quantity);
        }

        wo.status = "ISSUED";
    }

    public void reportProduction(WorkOrder wo, int producedQty) throws InvalidWorkOrderException {
        if (!wo.status.equals("ISSUED")) {
            throw new InvalidWorkOrderException("Materials not issued yet!");
        }
        wo.product.addStock(producedQty);
        wo.status = "COMPLETED";
    }
}
