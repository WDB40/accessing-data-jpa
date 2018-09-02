package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void  main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository) {

        return (args) -> {
            //Save a couple of customers
            customerRepository.save(new Customer("Jack", "Bauer"));
            customerRepository.save(new Customer("Chloe", "O'Brian"));
            customerRepository.save(new Customer("Kim", "Bauer"));
            customerRepository.save(new Customer("David", "Palmer"));
            customerRepository.save(new Customer("Michelle", "Dessler"));
            customerRepository.save(new Customer("Wes", "Brown"));

            //Fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");

            for(Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }

            //Fetch an individual customer ID
            customerRepository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("---------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            //Fetch customers by last name
            log.info("Customers found with findByLastName('Bauer'):");
            log.info("---------------------------------------------");
            customerRepository.findByLastName("Bauer")
                    .forEach(bauer-> {
                        log.info(bauer.toString());
                    });

            log.info("Trying out adding a findByFirstName");
            log.info("Customers found with findByFirstName('Wes')");
            log.info("-------------------------------------------");
            customerRepository.findByFirstName("Wes")
                    .forEach(wes-> {
                        log.info(wes.toString());
                    });

            /*
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
                }
             */

            log.info("");
        };
    }
}
