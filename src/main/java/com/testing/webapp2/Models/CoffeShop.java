package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(schema = "public", name = "CoffeShop")
@NoArgsConstructor
public class CoffeShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String description;
    private LocalDateTime dateTime = LocalDateTime.now();
    private int workTime;

    public CoffeShop(String street, String description, int workTime) {
        this.street = street;
        this.description = description;
        this.workTime = workTime;
    }
}

/*class CoffeeShop(models.Model):
    street = models.TextField(max_length=200)
        slug = models.SlugField(unique=True, blank=True)
    description = models.TextField()
    photo = models.ImageField(upload_to='shops/', blank=True)
    date_of_creation = models.DateTimeField()
    work_time = models.CharField(max_length=20)*/