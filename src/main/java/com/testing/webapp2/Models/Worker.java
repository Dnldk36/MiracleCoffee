package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*
    Delete date_of_creation
    Save dateOfCreator
* */
@Entity
@Table(schema = "public", name = "Worker")
@Data
@NoArgsConstructor
@ToString
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int salary;
    private int date_of_creation;
    private int views;
    private long coffeeShopID;

    @ManyToOne
    private CoffeShop coffeShop;

    public Worker(String title, String description, int salary, int date_of_creation) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.date_of_creation = date_of_creation;
    }
    public Worker(String title, String description, int salary, int date_of_creation, CoffeShop coffeShop) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.date_of_creation = date_of_creation;
        this.coffeShop = coffeShop;
        this.coffeeShopID = coffeShop.getId();
    }
    /*public Worker(String title, String description, int salary, int date_of_creation, long coffeShop) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.date_of_creation = date_of_creation;
        this.coffeShop.setId(coffeShop);
    }*/
}

/*class Worker(models.Model):
    title = models.CharField(max_length=40) #OK
    description = models.TextField() #OK
    photo = models.ImageField(upload_to='work/', blank=True) #OK
    salary = models.CharField(max_length=10, blank=True) #OK
    slug = models.SlugField(unique=True, blank=True)
        coffee_shop = models.ForeignKey(CoffeeShop, on_delete=models.CASCADE)
    date_of_creation = models.DateTimeField(auto_now_add=True)
    views = models.CharField(max_length=255, default=0) #OK*/
