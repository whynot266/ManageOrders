package repository;

import entity.OrderDetails;
import entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    @Query(value = "Select * from orders where MONTH(orderDate)= MONTH(curdate()) and YEAR(orderDate)=YEAR(curdate())", nativeQuery = true)
    List<Orders> getOrdersInCurrentMonth();
    List<Orders> findAll();

    @Query(value = "select * from orders o join orderdetails od on o.id = od.orderId group by o.id having sum(od.quantity*od.unitPrice)>?1", nativeQuery = true)
    List<Orders> getOrdersListGreaterThan(double amount);

    @Query(value = "select * from orders o join orderdetails od on o.id = od.orderId where productName like %?1%", nativeQuery = true)
    List<Orders> getOrdersThatPurchasedAProduct(String name);


}
