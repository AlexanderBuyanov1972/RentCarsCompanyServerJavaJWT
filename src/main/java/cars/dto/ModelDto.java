package cars.dto;

public class ModelDto {
    private String modelName;
    private int gasTank;
    private String company;
    private String country;
    private int priceDay;

    public ModelDto() {
    }
    // ***************Getters***************************

    public String getModelName() {
        return modelName;
    }

    public int getGasTank() {
        return gasTank;
    }

    public String getCompany() {
        return company;
    }

    public String getCountry() {
        return country;
    }

    public int getPriceDay() {
        return priceDay;
    }

    // ************Setters**************************


    public ModelDto setModelName(String modelName) {
        this.modelName = modelName;
        return this;

    }

    public ModelDto setGasTank(int gasTank) {
        this.gasTank = gasTank;
        return this;
    }

    public ModelDto setCompany(String company) {
        this.company = company;
        return this;
    }

    public ModelDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public ModelDto setPriceDay(int priceDay) {
        this.priceDay = priceDay;
        return this;
    }
}
