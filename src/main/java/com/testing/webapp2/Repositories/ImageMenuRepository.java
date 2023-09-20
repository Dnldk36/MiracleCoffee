package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.ImageMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ImageMenuRepository extends CrudRepository<ImageMenu, Long> {
}
