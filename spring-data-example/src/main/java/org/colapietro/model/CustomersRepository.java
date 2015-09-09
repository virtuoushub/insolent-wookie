package org.colapietro.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Pete Colapietro
 */
public interface CustomersRepository extends CrudRepository<Customers, Long> {
  List<Customers> findById(Long id);
}
