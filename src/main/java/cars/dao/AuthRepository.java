package cars.dao;

import cars.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<Account, String>{
    Account findByUsername(String username);
}
