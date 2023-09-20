package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "Category")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    public Categories(String title) {
        this.title = title;
    }

    public Categories(long id) {
        this.id = id;
    }

}

/*class Categories(models.Model):
    title = models.CharField(max_length=30)*/
