package service;

import config.SpringConfig;
import entity.OrderDetails;
import entity.Orders;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import repository.OrderDetailsRepository;
import repository.OrdersRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
@Service
public class OrderService {

    static AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext("SpringConfig.class");
    static OrdersRepository ordersRepository= (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository=(OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static Orders enterNewOrder(){
        Scanner sc= new Scanner(System.in);
        Orders order= new Orders();
        System.out.println("Enter username");
        order.setCustomerName(sc.nextLine());
        System.out.println("Enter your address");
        order.setCustomerAddress(sc.nextLine());
        order.setOrderDate(LocalDate.now());
        ordersRepository.save(order);

        return order;
    }

    public static void createNewOrderDetails(){
        Scanner sc= new Scanner(System.in);
        OrderDetails orderDetails= new OrderDetails();
        System.out.println("Enter your ID: ");
        Orders order= ordersRepository.findById(sc.nextInt()).get();
        System.out.println("Enter your order details here");
        List<OrderDetails> orderDetailsList= order.getOrderDetailsList();
        System.out.println("1.Product name: ");
        orderDetails.setProductName(sc.nextLine());
        System.out.println("1.Product quantity: ");
        orderDetails.setQuantity(sc.nextInt());
        System.out.println("1.Product unit price: ");
        orderDetails.setUnitPrice(sc.nextDouble());
        orderDetails.setOrderId(order.getId());
        orderDetailsList.add(orderDetails);
        orderDetailsRepository.saveAll(orderDetailsList);

    }
    public static void listAll(){
        System.out.println(ordersRepository.findAll().toString());
    }

    public static void listById(int id){
        System.out.println(ordersRepository.findById(id).toString());
    }

    public static void listOrdersInThisMonth(){
        ordersRepository.getOrdersInCurrentMonth();
    }

    public static void listOrderThatPurchasedMoreThan(double amount){
        List<OrderDetails> orderDetailsList= orderDetailsRepository.getOrderDetailsListGreaterThan(amount);
        for (OrderDetails od: orderDetailsList
             ) {
            System.out.println(ordersRepository.findById(od.getOrderId()).toString());
        }

    }
}
