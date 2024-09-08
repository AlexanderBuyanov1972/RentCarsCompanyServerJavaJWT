package cars.service_rent_company;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractRentCompany implements IRentCompany {
    private int finePercent = 15;
    private int gasPrice = 10;


}
