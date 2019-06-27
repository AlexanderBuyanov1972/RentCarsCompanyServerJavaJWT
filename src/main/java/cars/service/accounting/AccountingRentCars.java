package cars.service.accounting;

import cars.dao.AuthRepository;
import cars.entities.AccountMongo;
import cars.security.accounting.IAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@ManagedResource
public class AccountingRentCars implements IAccounting {
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
    public String getPassword(String email) {
        System.out.println("получаю паспорт по мылу");
        AccountMongo account = repository.findById(email).orElse(null);
        System.out.println("получил аккаунт из базы");
        if (account == null) {
            System.out.println("аккаунт пустой");
            return "";
        }
        LocalDate expDate = account.getDate().plusDays(experationPeriod);
        if (LocalDate.now().isAfter(expDate) || LocalDate.now().equals(expDate)) {
            System.out.println("аккаунт просрочен");
            return "";
        }
        System.out.println("получил паспорт");
        return account.getPassword();
    }

    @Override
    public String[] getRoles(String email) {
        System.out.println("получаю роли по мылу");
        AccountMongo account = repository.findById(email).orElse(null);
        System.out.println("получил аккаунт из базы");
        if (account == null) {
            System.out.println("аккаунт пустой");
            return new String[0];
        }
        System.out.println("получил роли из базы");
        return account.getRoles();
    }

}
