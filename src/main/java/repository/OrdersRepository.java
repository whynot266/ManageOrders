package repository;

import entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {
    @Query("Select o from Orders o where MONTH(o.orderDate)= MONTH(curdate()) and YEAR(o.orderDate)=YEAR(curdate())")
    List<Orders> getOrdersInCurrentMonth();


}
