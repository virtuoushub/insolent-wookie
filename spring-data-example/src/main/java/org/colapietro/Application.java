package org.colapietro;

import org.colapietro.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pete Colapietro
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  public static final String TMP_FILE_XML = "/tmp/file.xml";
  @Autowired
  CustomerRepository customerRepository;


  @Autowired
  CustomersRepository customersRepository;


  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... strings) throws Exception {
    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = sf.newSchema(new File("src/main/resources/customer.xsd"));

    JAXBContext jc = JAXBContext.newInstance(Customer.class);

    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setSchema(schema);
    //marshaller.setEventHandler(new MyValidationEventHandler());

    Customer customer = new Customer("mkyong", 29, Active.N);


    marshaller.marshal(customer, System.out);

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    unmarshaller.setSchema(schema);

    final File tempFile = new File(TMP_FILE_XML); //FIXME
    boolean exists = tempFile.exists();
    if(!exists) {
      testMarshaller(customer);
    }
    final Customer unmarshaledCustomer = (Customer)unmarshaller.unmarshal(tempFile);
    System.out.println(unmarshaledCustomer);
    System.out.println(customer.equals(unmarshaledCustomer));

    //testMarshaller(customer);
    //initializeRepositories();
  }

  private void testMarshaller(Customer customer) {

    try {

      File file = new File(TMP_FILE_XML);
      JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(customer, file);
      jaxbMarshaller.marshal(customer, System.out);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  private void initializeRepositories() {
    // save a couple of customers
    customerRepository.save(new Customer("Jack Bauer", 00, Active.Y));
    customerRepository.save(new Customer("Chloe O'Brian", 01, Active.N));
    customerRepository.save(new Customer("Kim Bauer", 10, Active.N));
    customerRepository.save(new Customer("David Palmer", 11, Active.N));
    customerRepository.save(new Customer("Michelle Dessler", 00, Active.N));

    // fetch all customers
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    final Iterable<Customer> allCustomers = customerRepository.findAll();
    for (Customer customer : allCustomers) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer by ID
    Customer customer = customerRepository.findOne(1L);
    System.out.println("Customer found with findOne(1L):");
    System.out.println("--------------------------------");
    System.out.println(customer);
    System.out.println();

    // fetch customers by last name
    System.out.println("Customer found with findByLastName('Bauer'):");
    System.out.println("--------------------------------------------");
    customerRepository.findByName("Jack Bauer").forEach(System.out::println);

    Set<Customer> set = new HashSet<>();
    for(Customer thisCustomer : allCustomers) {
      set.add(thisCustomer);
    }

    customersRepository.save(new Customers(set));
    final Customers customers = customersRepository.findOne(1L);
    System.out.println("Customers found with findOne(1L):");
    System.out.println("--------------------------------");
    System.out.println(customers);
    System.out.println();
  }
}
