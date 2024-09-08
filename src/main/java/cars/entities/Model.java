package cars.entities;

import cars.dto.ModelDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "models")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Model {
    @Id
    private String modelName;
    private int gasTank;
    private String company;
    private String country;
    private int priceDay;
    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private List<Car> cars;

    public Model(ModelDto one) {
        this.modelName = one.getModelName();
        this.company = one.getCompany();
        this.country = one.getCountry();
        this.priceDay = one.getPriceDay();
        this.gasTank = one.getGasTank();
    }
}
