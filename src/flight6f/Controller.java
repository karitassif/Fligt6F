package flight6f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {


    private SearchController searchControllerTo = new SearchController();
    private SearchController searchControllerFrom = new SearchController();

    @FXML
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    private ComboBox<String> from;
    @FXML
    private ObservableList<String> fromoptn = FXCollections.observableArrayList("Keflavík,KEF");
    @FXML
    private ComboBox<String> to;
    @FXML
    private ObservableList<String> tooptn = FXCollections.observableArrayList(
            "Amsterdam,AMS",
            "Brussel,BRU",
            "Frankfurt,FRA",
            "Glasgow,GLA",
            "Hamburg,HAM",
            "Helsinki,HEL",
            "Copenhagen,CPH",
            "London,LHR",
            "Manchester,MAN",
            "München,MUN",
            "Oslo,OSL",
            "Paris,CDG",
            "Stockholm,ARN",
            "Boston,BOS",
            "Chicago,CHI",
            "Denver,DEN",
            "New York,JFK",
            "Seattle,SEA",
            "Toronto,YYZ"
    );

    @FXML
    private ComboBox<Integer> adults;
    @FXML
    private ComboBox<Integer> children;

    @FXML
    ObservableList numberOfPassengers = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @FXML
    private DatePicker departdate;
    @FXML
    private DatePicker returndate;

    @FXML
    private TextField maxPrice;

    @FXML
    ListView<String> departview;
    @FXML
    ListView<String> returnview;

    @FXML
    ObservableList<String> flightsto = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> flightsfrom = FXCollections.observableArrayList();


    @FXML
    Button sortByPrice;


    @FXML
    private void initialize() {
        from.setItems(fromoptn);
        to.setItems(tooptn);
        adults.setItems(numberOfPassengers);
        children.setItems(numberOfPassengers);
        adults.getSelectionModel().selectFirst();
        children.getSelectionModel().selectFirst();
        from.getSelectionModel().selectFirst();
    }

    @FXML
    private void alertBoxDate(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please pick a date");

        alert.showAndWait();
    }

    @FXML
    private void alertBoxPassengers(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please input number of passengers");

        alert.showAndWait();
    }

    @FXML
    private void alertBoxDestintion(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please pick a destination");

        alert.showAndWait();
    }

    @FXML
    private void sort(ActionEvent event){
        if (event.getSource() == sortByPrice){
            searchControllerTo.sortFlights(true);
            searchControllerFrom.sortFlights(true);
        }
        else{
            searchControllerTo.sortFlights(false);
            searchControllerFrom.sortFlights(false);
        }
        showlists();
    }


    private String getInfo(Flight flight){
            String flightinfo = "";
            flightinfo += flight.getFlightNumber() + ": ";
            flightinfo += "from " + flight.getDeparture().getAirportCity();
            flightinfo += " to " + flight.getDestination().getAirportCity() + " ";
            flightinfo += "Departure " + flight.getFormattedDepTime();
            flightinfo += " Arrival " + flight.getFormattedArrTime();
            flightinfo += " Price: " + flight.getPrice();
            return flightinfo;

    }

    @FXML
    private void searchDiscountFlights(ActionEvent event) throws SQLException {

        int priceMax = Integer.parseInt(maxPrice.getText());

        LocalDate date1 = departdate.getValue();
        LocalDate date2 = returndate.getValue();

        if (date1 == null || date2 == null) {
            alertBoxDate();
            return;
        }

        Calendar cal1 = new GregorianCalendar(date1.getYear(), date1.getMonthValue() - 1, date1.getDayOfMonth());
        Calendar cal2 = new GregorianCalendar(date2.getYear(), date2.getMonthValue()-1, date2.getDayOfMonth());
        searchControllerTo.searchDiscountFlights(priceMax, cal1);
        searchControllerFrom.searchDiscountFlights(priceMax,cal2);
        showlists();
    }

    private void showlists(){
        flightsto.clear();
        flightsfrom.clear();

        for (Flight flight : searchControllerTo.getFlights()) {
            flightsto.add(getInfo(flight));
        }
        for (Flight flight : searchControllerFrom.getFlights()) {
            flightsfrom.add(getInfo(flight));
        }

        departview.setItems(flightsto);
        returnview.setItems(flightsfrom);
    }

    @FXML
    private void search(ActionEvent event) throws SQLException{
        if (from.getValue() == null || to.getValue() == null){
            alertBoxDestintion();
            return;
        }
        String[] dep = from.getValue().split(",");
        String[] arr = to.getValue().split(",");
        int numberOfPassengers = adults.getValue() + children.getValue();

        if (numberOfPassengers < 1){
            alertBoxPassengers();
            return;
        }

        int priceMax = Integer.parseInt(maxPrice.getText());

        LocalDate date1 = departdate.getValue();
        LocalDate date2 = returndate.getValue();

        if (date1 == null || date2 == null){
            alertBoxDate();
            return;
        }

        Calendar cal1 = new GregorianCalendar(date1.getYear(), date1.getMonthValue() - 1, date1.getDayOfMonth());
        Calendar cal2 = new GregorianCalendar(date2.getYear(), date2.getMonthValue()-1, date2.getDayOfMonth());

        searchControllerTo.searchFlights(dep[1], arr[1], priceMax, numberOfPassengers, cal1);
        searchControllerFrom.searchFlights(arr[1], dep[1], priceMax, numberOfPassengers, cal2);

        showlists();
    }
}

