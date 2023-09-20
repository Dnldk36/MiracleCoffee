package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private double price;

   /* @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id")
    private Categories categories;*/

    @ManyToOne
    private Categories categories;

    public Product(String title, String description, double price, long categories) {
        this.title = title;
        this.description = description;
        this.price = price;
        if (this.categories != null) {
            this.categories.setId(categories);
        }
    }
    public Product(String title, String description, double price, Categories categories) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }
}

/*class Product(models.Model):
    title = models.CharField(max_length=30)
    price = models.CharField(max_length=10)
    description = models.TextField(blank=True)
    photo = models.ImageField(upload_to='coffee/')
    category = models.ForeignKey(Categories, on_delete=models.CASCADE)*/
