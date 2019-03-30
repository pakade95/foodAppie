package com.danieltrzaskoma.foodieapp_project_1.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByName(String name);
}
