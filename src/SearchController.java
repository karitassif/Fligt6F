import java.util.Date;
import java.util.List;

public class SearchController {

    private List<Flight> flights;
    private List<Flight> filteredFlights;

    public void searchFlights(Airport departure, Airport destination, int maxPrice,
                              int passengerCount, Date depTime) {

    }

    public void searchDiscountFlights(int maxPrice){

    }

    public int showPrice(int adultCount, int childCount){
        return 1;
    }

    public void filterFlights(int maxPrice, boolean childDiscount){

    }

    public void sortFlights(boolean sortByPrice){

    }

}
