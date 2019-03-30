package com.danieltrzaskoma.foodieapp_project_1.Order;

import com.danieltrzaskoma.foodieapp_project_1.Item.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_order")
public class Order implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name="order_item",
    joinColumns = @JoinColumn(name="order_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="item_id",referencedColumnName = "id"))
    private List<Item> items=new ArrayList<>();
    private String address;
    private String telephone;

    @Enumerated(EnumType.STRING)//Status jest typem enum, możemy przechowywać w nim informacje na 2 różne sposoby
    private OrderStatus status;// EnumType.STRING - w bazie danych bedzie przechowywana nazwa ENUM
    //EnumType.ORDINAL w bazie danych bedzie przechowywany numer porządkowy wartości

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
