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

    static AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);
    static OrdersRepository ordersRepository= (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository=(OrderDetailsRepository) context.getBean("orderDetailsRepository");

    public static void enterNewOrder(){
        Scanner sc= new Scanner(System.in);
        Orders order= new Orders();
        System.out.println("Enter username");
        order.setCustomerName(sc.nextLine());
        System.out.println("Enter your address");
        order.setCustomerAddress(sc.nextLine());
        order.setOrderDate(LocalDate.now());
        ordersRepository.save(order);


    }

    public static void createNewOrderDetails(){
        Scanner sc= new Scanner(System.in);
        OrderDetails orderDetails= new OrderDetails();
        System.out.println("Enter your ID: ");
        Orders order= ordersRepository.findById(sc.nextInt()).get();
        sc.nextLine();
        System.out.println("Enter your order details here");

        System.out.println("1.Product name: ");
        orderDetails.setProductName(sc.nextLine());
        System.out.println("1.Product quantity: ");
        orderDetails.setQuantity(sc.nextInt());
        System.out.println("1.Product unit price: ");
        orderDetails.setUnitPrice(sc.nextDouble());
        orderDetails.setOrderId(order);
        orderDetailsRepository.save(orderDetails);

    }
    public static String listOrderDetails(Orders orders){
        List<OrderDetails> detailsList= orderDetailsRepository.findByOrderId(orders);
        for (OrderDetails od: detailsList
        ) {
            System.out.println(od.toString());
        }
        return "";
    }
    public static void listAll(){
        List<Orders> ordersList= ordersRepository.findAll();
        for (Orders o: ordersList
             ) {
            System.out.println(o.toString());
            listOrderDetails(o);
        }
    }

    public static void listById(int id){
        System.out.println(ordersRepository.findById(id).toString());
        listOrderDetails(ordersRepository.findById(id).get());
    }

    public static void listOrdersInThisMonth(){
        List<Orders> ordersList= ordersRepository.getOrdersInCurrentMonth();
        for (Orders o: ordersList
        ) {
            System.out.println(o.toString());

        }
    }

    public static void listOrderThatPurchasedMoreThan(double amount){
        List<Orders> ordersList= ordersRepository.getOrdersListGreaterThan(amount);
        for (Orders o: ordersList
             ) {
            System.out.println(o.toString());
        }

    }
    public static void listOrdersThatPurchasedAProduct(String name){
        List<Orders> ordersList= ordersRepository.getOrdersThatPurchasedAProduct(name);
        for (Orders o: ordersList
        ) {
            System.out.println(o.toString());
            listOrderDetails(o);
        }

    }
}
