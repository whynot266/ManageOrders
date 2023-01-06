package repository;

import entity.OrderDetails;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {
    @Query(value = "Select * from OrderDetails where sum(unitPrice*quantity) >= ?1 group by orderId", nativeQuery = true)
    List<OrderDetails> getOrderDetailsListGreaterThan(double amount);
}
