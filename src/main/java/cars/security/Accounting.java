package cars.security;

import cars.dao.AuthRepository;
import cars.entities.AccountMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@ManagedResource
public class Accounting implements IAccounting {
    @Value("${period:30}")
    int experationPeriod;

    @ManagedAttribute
    public int getExperationPeriod() {
        return experationPeriod;
    }

    @ManagedAttribute
    public void setExperationPeriod(int experationPeriod) {
        this.experationPeriod = experationPeriod;
    }

    @Autowired
    AuthRepository repository;

    @Override
    public String getPassword(String username) {
        AccountMongo account = repository.findById(username).orElse(null);
        if (account == null) {
            return "";
        }
        LocalDate expDate = account.getDate().plusDays(experationPeriod);
        if (LocalDate.now().isAfter(expDate) || LocalDate.now().equals(expDate)) {
            return "";
        }
        return account.getPassword();
    }

    @Override
    public String[] getRoles(String username) {
        AccountMongo account = repository.findById(username).orElse(null);
        if (account == null) {
            return new String[0];
        }
        return account.getRoles();
    }

}
