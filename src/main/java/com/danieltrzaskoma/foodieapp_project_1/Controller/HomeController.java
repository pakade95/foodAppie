package com.danieltrzaskoma.foodieapp_project_1.Controller;

import com.danieltrzaskoma.foodieapp_project_1.Item.Item;
import com.danieltrzaskoma.foodieapp_project_1.Item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ItemRepository itemRepository;

    @Autowired
    public HomeController(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Item> itemList=itemRepository.findAll();
        model.addAttribute("items",itemList);
        return "index";
    }
}
