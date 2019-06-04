package cars.dao;

import cars.entities.accounting.AccountMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<AccountMongo, String>{

}
