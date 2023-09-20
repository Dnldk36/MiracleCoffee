package com.testing.webapp2.Repositories;

import com.testing.webapp2.Models.Contacts;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contacts, Long> {
}
