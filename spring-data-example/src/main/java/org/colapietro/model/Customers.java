package org.colapietro.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pete Colapietro
 */
@Entity
public class Customers {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "customers_id")
  private Set<Customer> customerSet;

  protected Customers() {}

  public Customers(final Set<Customer> customerSet) {
    this.customerSet = customerSet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customers customers1 = (Customers) o;
    return Objects.equals(id, customers1.id) &&
        Objects.equals(customerSet, customers1.customerSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerSet);
  }

  @Override
  public String toString() {
    return "Customers{" +
        "id=" + id +
        ", customerSet=" + customerSet +
        '}';
  }
}
