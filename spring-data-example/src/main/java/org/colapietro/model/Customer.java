package org.colapietro.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Pete Colapietro
 */
@Entity
@XmlRootElement(name = "customer")
public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @XmlElement
  private long id;

  @XmlElement
  private String name;

  @XmlElement
  private long duns;

  @XmlElement
  @Enumerated(EnumType.STRING)
  private Active active;

  public Customer() {}

  public Customer(final String name, final int duns, final Active active) {
    this.name     = name;
    this.duns     = duns;
    this.active   = active;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public long getDuns() {
    return duns;
  }

  public Active getActive() {
    return active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(duns, customer.duns) &&
        Objects.equals(name, customer.name) &&
        Objects.equals(active, customer.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, duns, active);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", duns=" + duns +
        ", active=" + active +
        '}';
  }

}
