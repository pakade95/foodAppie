package com.danieltrzaskoma.foodieapp_project_1.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long>{
    List<Order>findAllByStatus(OrderStatus status);
}
