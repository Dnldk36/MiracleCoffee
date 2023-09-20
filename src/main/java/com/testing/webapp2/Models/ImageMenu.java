package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@Table(schema = "public", name = "Menu_Img")
public class ImageMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private Blob image;
    private Date date = new Date();
}
