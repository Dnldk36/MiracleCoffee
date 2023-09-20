package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "CoffeeShop_img")
@Data
public class ImageCoffeeShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private Blob image;
    private Date date = new Date();
}

