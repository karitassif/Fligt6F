package flight6f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SearchViewController {



    private SearchController searchControllerTo = new SearchController();
    private SearchController searchControllerFrom = new SearchController();

    //Generic alert box, used for various scenarios
    @FXML
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    //Departure and destination drop menus
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


    //Number of passengers
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
    private ListView<String> departview;
    @FXML
    private ListView<String> returnview;


    //List of flights to be displayed on screen
    @FXML
    private ObservableList<String> flightsto = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> flightsfrom = FXCollections.observableArrayList();

    @FXML
    private RadioButton byPrice;
    @FXML
    private RadioButton byDate;


    @FXML
    private Text totalPrice;

    @FXML
    private CheckBox discount;

    //All for searching a booking in the booking tab
    @FXML
    private TextField bookingID;
    @FXML
    private Button searchBooking;
    @FXML
    private ListView<String> flightInfo;
    @FXML
    private ObservableList<String> flightInfoList = FXCollections.observableArrayList();



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

    //Various alertbox formats
    @FXML
    private void alertBoxDate() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please pick a date");

        alert.showAndWait();
    }

    @FXML
    private void alertBoxPassengers() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please input number of passengers");

        alert.showAndWait();
    }

    @FXML
    private void alertBoxDestintion() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please pick a destination");

        alert.showAndWait();
    }

    @FXML
    private void alertBoxChoose() {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Did you forget something?");
        alert.setContentText("Please choose a flight");

        alert.showAndWait();
    }


    //Shows price by using show price method from SearchController.java
    @FXML
    private void showPrice(ActionEvent event) {
        if (departview.getSelectionModel().isEmpty() || returnview.getSelectionModel().isEmpty()) {
            alertBoxChoose();
            return;
        } else {
            Flight flight1 = searchControllerTo.getFlights().get(departview.getSelectionModel().getSelectedIndex());
            Flight flight2 = searchControllerFrom.getFlights().get(returnview.getSelectionModel().getSelectedIndex());
            int price1 = searchControllerTo.showPrice(flight1, adults.getValue(), children.getValue());
            int price2 = searchControllerFrom.showPrice(flight2, adults.getValue(), children.getValue());
            totalPrice.setText(price1 + price2 + " kr.");
        }

    }

    //Sorts flights with sort method in SearchController.java
    @FXML
    private void sort(ActionEvent event) {
        if (flightsto.size() < 1 || flightsfrom.size() < 1) return;
        if (byPrice.isSelected()) {
            searchControllerTo.sortFlights(true);
            searchControllerFrom.sortFlights(true);
        } else {
            searchControllerTo.sortFlights(false);
            searchControllerFrom.sortFlights(false);
        }
        showlists();
    }


    //Generic method to convert all important data from flights into String
    private String getInfo(Flight flight) {
        String flightinfo = "";
        flightinfo += flight.getFlightNumber() + ": ";
        flightinfo += "from " + flight.getDeparture().getAirportCity();
        flightinfo += " to " + flight.getDestination().getAirportCity();
        flightinfo += "\nDeparture: " + flight.getFormattedDepTime();
        flightinfo += "\nArrival: " + flight.getFormattedArrTime();
        flightinfo += "\nPrice: " + flight.getPrice() + " kr.";
        return flightinfo;

    }

    @FXML
    private void searchDiscountFlights(ActionEvent event) throws SQLException {

        to.setValue("");
        from.setValue("");

        int priceMax = Integer.parseInt(maxPrice.getText());

        LocalDate date1 = departdate.getValue();
        LocalDate date2 = returndate.getValue();

        if (date1 == null || date2 == null) {
            alertBoxDate();
            return;
        }
        int numberOfPassengers = adults.getValue() + children.getValue();

        if (numberOfPassengers < 1) {
            alertBoxPassengers();
            return;
        }

        Calendar cal1 = new GregorianCalendar(date1.getYear(), date1.getMonthValue() - 1, date1.getDayOfMonth());
        Calendar cal2 = new GregorianCalendar(date2.getYear(), date2.getMonthValue() - 1, date2.getDayOfMonth());
        searchControllerTo.searchDiscountFlights(priceMax, cal1, numberOfPassengers);
        searchControllerFrom.searchDiscountFlights(priceMax, cal2, numberOfPassengers);

        sort(event);

        showlists();
    }

    //Displays flights in listview on screen
    private void showlists() {
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
    private void search(ActionEvent event) throws SQLException {
        if (maxPrice.getText().equals("")) maxPrice.setText("200000");
        if (discount.isSelected()){
            searchDiscountFlights(event);
            return;
        }
        if (from.getValue() == null || to.getValue() == null) {
            alertBoxDestintion();
            return;
        }
        String[] dep = from.getValue().split(",");
        String[] arr = to.getValue().split(",");
        int numberOfPassengers = adults.getValue() + children.getValue();

        if (numberOfPassengers < 1) {
            alertBoxPassengers();
            return;
        }

        int priceMax = Integer.parseInt(maxPrice.getText());

        LocalDate date1 = departdate.getValue();
        LocalDate date2 = returndate.getValue();

        if (date1 == null || date2 == null) {
            alertBoxDate();
            return;
        }

        Calendar cal1 = new GregorianCalendar(date1.getYear(), date1.getMonthValue() - 1, date1.getDayOfMonth());
        Calendar cal2 = new GregorianCalendar(date2.getYear(), date2.getMonthValue() - 1, date2.getDayOfMonth());

        searchControllerTo.searchFlights(dep[1], arr[1], priceMax, numberOfPassengers, cal1);
        searchControllerFrom.searchFlights(arr[1], dep[1], priceMax, numberOfPassengers, cal2);

        sort(event);

        showlists();
    }

    @FXML
    private void searchBooking(ActionEvent event) throws SQLException {
        flightInfoList.clear();
        BookingController bookingController = new BookingController();
        int bookingID = Integer.parseInt(this.bookingID.getText());
        Booking booking = bookingController.searchBooking(bookingID);
        Flight flight = booking.getFlight();
        List<Passenger> passengers = booking.getPassengers();
        String comment = booking.getComment();
        flightInfoList.add("Booking ID: " + bookingID);
        flightInfoList.add(getInfo(flight));
        for (Passenger passenger : passengers){
            flightInfoList.add(passenger.getName() + "\n" + passenger.getKennitala());
        }
        flightInfoList.add(comment);
        flightInfo.setItems(flightInfoList);
    }


    //Opens new Booking window and closes search window
    @FXML
    private void book(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookingpanel.fxml"));

            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Flight6F Search Engine");
            stage.setScene(scene);


            int n = adults.getValue() + children.getValue();
            Flight flight1 = searchControllerFrom.getFlights().get(departview.getSelectionModel().getSelectedIndex());
            Flight flight2 = searchControllerTo.getFlights().get(returnview.getSelectionModel().getSelectedIndex());
            ((BookingViewController)fxmlLoader.getController()).setNumberOfPassengers(n);
            ((BookingViewController)fxmlLoader.getController()).setFlight1(flight1);
            ((BookingViewController)fxmlLoader.getController()).setFlight2(flight2);

            //close search window
            Node source = (Node) event.getSource();
            Stage searchStage = (Stage) source.getScene().getWindow();
            searchStage.close();

            stage.show();


        } catch (IOException e) {
            System.err.println("Failed to create new Window." + e);
        }
    }
}