package main;

import entity.Orders;
import service.OrderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int choose;
        do{
            System.out.println("0. Exit");
            System.out.println("1. Create new order");
            System.out.println("2. Create new order-details");
            System.out.println("3. List All");
            System.out.println("4. Orders and Details by by ID");
            System.out.println("5. Orders in this month");
            System.out.println("6. List Orders more than an amount");
            System.out.println("7. List Orders buy a specific product");
            switch (choose = sc.nextInt()){
                case 1:
                    OrderService.enterNewOrder();
                    break;
                case 2:
                    OrderService.createNewOrderDetails();
                    break;
                case 3:
                    OrderService.listAll();
                    break;
                case 4:
                    System.out.println("3. Enter your ID: ");
                    OrderService.listById(sc.nextInt());
                    break;
                case 5:
                    OrderService.listOrdersInThisMonth();
                    break;
                case 6:
                    System.out.println("3. Enter the amount: ");
                    OrderService.listOrderThatPurchasedMoreThan(sc.nextDouble());
                    break;
                case 7:
                    System.out.println("3. Enter the product to search for: ");
                    sc.nextLine();
                    OrderService.listOrdersThatPurchasedAProduct(sc.nextLine());
                    break;
                default:
                    exit = true;
                    break;
            }
        } while (!exit);



    }
}
