package com.danieltrzaskoma.foodieapp_project_1.Controller;

import com.danieltrzaskoma.foodieapp_project_1.Item.Item;
import com.danieltrzaskoma.foodieapp_project_1.Order.Order;
import com.danieltrzaskoma.foodieapp_project_1.Order.OrderRepository;
import com.danieltrzaskoma.foodieapp_project_1.Order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderPanelController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderPanelController(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    @GetMapping("/panel/zamowienia")
    public String getOrders(@RequestParam(required = false)OrderStatus status, Model model){
        List<Order> orderList;
        if(status==null){
            orderList=orderRepository.findAll();
        }
        else
        {
            orderList=orderRepository.findAllByStatus(status);
        }
        model.addAttribute("orders",orderList);
        return "panel/orders";
    }

    @GetMapping("/panel/zamowienia/{id}")
    public String singleOrder(@PathVariable Long id,Model model){
        Optional<Order>order=orderRepository.findById(id);
        return order.map(order1 -> singleOrderPanel(order1,model)).orElse("redirect:/");
    }

    @PostMapping("/panel/zamowienia/{id}")
    public String changeStatus(@PathVariable Long id,Model model){
        Optional<Order>order=orderRepository.findById(id);
        order.ifPresent(order1 -> {order1.setStatus(OrderStatus.nextStatus(order1.getStatus()));
        orderRepository.save(order1);
        });
        return order.map(o->singleOrderPanel(o,model)).orElse("redirect:/");
    }

    private String singleOrderPanel(Order order,Model model){
        model.addAttribute("order",order);
        model.addAttribute("sum",order.getItems().stream().mapToDouble(Item::getPrice).sum());
        return "panel/order";
    }
}
