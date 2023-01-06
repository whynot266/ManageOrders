package repository;

import entity.OrderDetails;
import entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {


    List<OrderDetails> findAll();
    @Query(value = "Select * from OrderDetails where orderId = ?1", nativeQuery = true)
    List<OrderDetails> findByOrderId(Orders id);
}
