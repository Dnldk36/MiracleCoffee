package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.CoffeShop;
import com.testing.webapp2.Models.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WorkerRepository extends CrudRepository<Worker, Long> {

    public List<Worker> findByOrderByIdDesc();
    public List<Worker> findByOrderByIdAsc();

    public boolean existsByCoffeShopStreet(String coffeShop_street);

    /*
* PAGINATION*/
    Page<Worker> findAll(Pageable pageable);

}
