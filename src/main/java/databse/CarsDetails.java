package databse;

import java.util.LinkedList;
import java.util.List;

public class CarsDetails {

    String brand;
    List<String> models = new LinkedList<>();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }
}
