package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.CoffeShop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeShopRepository extends CrudRepository<CoffeShop, Long> {

    public List<CoffeShop> findByStreet(String street);

}
