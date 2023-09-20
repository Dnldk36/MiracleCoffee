package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.Image;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
}


