package com.kce.bank.main;

import java.util.Scanner;
import com.kce.bank.model.*;
import com.kce.bank.service.ManufacturingService;
import com.kce.bank.exception.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Warehouse warehouse = new Warehouse("Main Warehouse");
        ManufacturingService service = new ManufacturingService(warehouse);

        while (true) {
            System.out.println("\n------->MENU<--------");
            System.out.println("1. Add Product");
            System.out.println("2. Define BOM");
            System.out.println("3. Create Work Order");
            System.out.println("4. Issue Materials");
            System.out.println("5. Report Production");
            System.out.println("6. Show Stock");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) { 
                System.out.print("Enter product id: ");
                String id = sc.nextLine();
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter opening stock: ");
                int stock = sc.nextInt();
                sc.nextLine();
                Product p = new Product(id, name, stock);
                service.products.add(p);
                warehouse.addProduct(p);
                System.out.println("Product added!");

            }
            else if (ch == 2) 
            {
                System.out.print("Enter finished product id: ");
                String id = sc.nextLine();
                Product fp = service.findProduct(id);
                if (fp == null) { 
                	System.out.println("Product not found");
                continue; 
                }
                BOM bom = new BOM(fp);
                while (true) {
                	 System.out.print("Enter component id ( 'done'): ");
                    String cid = sc.nextLine();
                    if (cid.equals("done")) 
                    	break;
                    Product comp = service.findProduct(cid);
                    if (comp == null) {  
                    	System.out.println("Component not found!");
                    	continue; 
                    	}
                    System.out.print("Enter quantity needed: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    bom.addItem(comp, qty);
                }
                service.boms.add(bom);
                System.out.println("BOM defined!");

            } else if (ch == 3) { 
                System.out.print("Enter WorkOrder id: ");
                String wid = sc.nextLine();
                System.out.print("Enter finished product id: ");
                String pid = sc.nextLine();
                Product fp = service.findProduct(pid);
                if (fp == null) { 
                	System.out.println("Product not found!"); 
                	continue;
                	}
                System.out.print("Enter quantity to make: ");
                int qty = sc.nextInt();
                sc.nextLine();
                WorkOrder wo = new WorkOrder(wid, fp, qty);
                service.workOrders.add(wo);
                System.out.println("Work Order created!");

            } else if (ch == 4) { 
                System.out.print("Enter WorkOrder id: ");
                String wid = sc.nextLine();
                WorkOrder wo = service.findWorkOrder(wid);
                if (wo == null) { 
                	System.out.println("WorkOrder not found!"); 
                	continue;
                	}
                try {
                    service.issueMaterials(wo);
                    System.out.println("Materials issued!");
                } catch (InsufficientStockException | InvalidWorkOrderException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (ch == 5) { 
                System.out.print("Enter WorkOrder id: ");
                String wid = sc.nextLine();
                WorkOrder wo = service.findWorkOrder(wid);
                if (wo == null) {
                	System.out.println("WorkOrder not found!");
                	continue;
                	}
                System.out.print("Enter quantity produced: ");
                int prod = sc.nextInt();
                sc.nextLine();
                try {
                    service.reportProduction(wo, prod);
                    System.out.println("Production reported!");
                } catch (InvalidWorkOrderException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (ch == 6) {
                warehouse.showStock();

            } else if (ch == 7) { 
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
