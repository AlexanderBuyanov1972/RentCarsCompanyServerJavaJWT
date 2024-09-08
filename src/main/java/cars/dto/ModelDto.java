package cars.dto;

import cars.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ModelDto {
    private String modelName;
    private int gasTank;
    private String company;
    private String country;
    private int priceDay;

    public ModelDto(Model model) {
        this.modelName = model.getModelName();
        this.company = model.getCompany();
        this.country = model.getCountry();
        this.priceDay = model.getPriceDay();
        this.gasTank = model.getGasTank();
    }
}
