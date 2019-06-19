package cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("telran.cars.security.configuration")
//@EnableMongoRepositories("telran.cars.dao")
public class RentCompanyApplServer {
    public static void main(String[] args) {
        SpringApplication.run(RentCompanyApplServer.class, args);

    }

}
