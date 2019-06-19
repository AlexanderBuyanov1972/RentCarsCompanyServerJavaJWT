package cars.dao;

import cars.entities.AccountMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<AccountMongo, String>{

}
