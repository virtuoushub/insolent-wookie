package org.colapietro.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Pete Colapietro
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  List<Customer> findByName(String name);
}
