package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(schema = "public", name = "Summary")
@NoArgsConstructor
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @Lob
    private String description;
    private Gender gender;
    public Summary(String name, int age, String description, Gender gender) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.gender = gender;
    }

    public enum Gender{
        M ("Man"),
        W ("Women"),
        T ("Trans");

        private String title;

        Gender(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }
}
/*class Summary(models.Model):
    genders = (
        ('M', 'Man'),
        ('W', 'Women'),
        ('T', 'Trans')
    ) #OK
    name = models.CharField(max_length=30) #OK
    age = models.CharField(max_length=3) #OK
    gender = models.CharField(max_length=5, choices=genders) #OK
    description = models.TextField() #OK*/

