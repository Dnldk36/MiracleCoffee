package com.testing.webapp2.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(schema = "public", name = "Contacts")
@NoArgsConstructor
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String jobTitle;
    private String name;
    @Lob
    private String description;
    @Lob
    private long phoneNumber;

    public Contacts(String jobTitle, String name, String description, long phoneNumber) {
        this.jobTitle = jobTitle;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }
}


/*class Contacts(models.Model):
    job_title = models.CharField(max_length=40)
    name = models.CharField(max_length=50)
    description = models.TextField(blank=True)
    slug = models.SlugField(blank=True, unique=True)
    phone_number = models.CharField(max_length=20)
    photo = models.ImageField(upload_to='contact/')*/