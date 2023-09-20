package com.testing.webapp2.Repositories;


import com.testing.webapp2.Models.Summary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends CrudRepository<Summary, Long> {
}
