package com.danieltrzaskoma.foodieapp_project_1.Model;

import com.danieltrzaskoma.foodieapp_project_1.Item.Item;
import com.danieltrzaskoma.foodieapp_project_1.Order.Order;
import com.danieltrzaskoma.foodieapp_project_1.Order.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class ClientOrder {

    private Order order;

    public ClientOrder(){
        clear();
    }

    public Order getOrder(){
        return order;
    }
    public void add(Item item){
        order.getItems().add(item);
    }

    public void clear(){
        order=new Order();
        order.setStatus(OrderStatus.NEW);
    }
}
