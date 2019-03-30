package com.danieltrzaskoma.foodieapp_project_1.Controller;

import com.danieltrzaskoma.foodieapp_project_1.Item.Item;
import com.danieltrzaskoma.foodieapp_project_1.Item.ItemRepository;
import com.danieltrzaskoma.foodieapp_project_1.Model.ClientOrder;
import com.danieltrzaskoma.foodieapp_project_1.Order.Order;
import com.danieltrzaskoma.foodieapp_project_1.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OrderController {

    private ClientOrder clientOrder;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(ItemRepository itemRepository,ClientOrder clientOrder, OrderRepository orderRepository){
        this.clientOrder=clientOrder;
        this.itemRepository=itemRepository;
        this.orderRepository=orderRepository;
    }

    @GetMapping("/zamowienie/dodaj")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresent(clientOrder::add);
        if (item.isPresent()) {
            model.addAttribute("message", ("Dodano do zamówienia: " + item.get().getName()));
        } else {

            model.addAttribute("message", ("Nie dodano, pobrano błędne id" + itemId));
        }
        return "message";

    }

    @GetMapping("/zamowienie")
        public String getCurrentOrder(Model model){
        model.addAttribute("order",clientOrder.getOrder());
        model.addAttribute("sum",clientOrder.getOrder().getItems().stream().mapToDouble(Item::getPrice).sum());
        return "order";
        }

        @PostMapping("/zamowienie/realizuj")
        public String proceedOrder(@RequestParam String adress,@RequestParam String telephone,Model model){
        Order order = clientOrder.getOrder();
        order.setAddress(adress);
        order.setTelephone(telephone);
        orderRepository.save(order);
        clientOrder.clear();
        model.addAttribute("message",("Dziękujemy. Zamówienie przyjęto do realizacji!"));
        return "message";
        }

    }
