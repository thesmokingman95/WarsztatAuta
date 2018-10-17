package databse;

import java.util.ArrayList;
import java.util.List;

public class AvaibleCars {

    List<CarsDetails> cars = new ArrayList<>();

    public List<CarsDetails> getCars() {
        return cars;
    }

    public void setCars(List<CarsDetails> cars) {
        this.cars = cars;
    }
}
