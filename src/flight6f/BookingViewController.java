package flight6f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by atlim on 12.4.2017.
 */
public class BookingViewController {

    @FXML
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TextField name;

    @FXML
    private TextField kennitala;

    @FXML
    ListView<String> passengerlist;

    @FXML
    ObservableList<String> passengers = FXCollections.observableArrayList();

    @FXML
    TextArea comment;

    @FXML
    Text successful;
    @FXML
    Text bookingID1;
    @FXML
    Text bookingID2;
    @FXML
    Text bookingIDs;


    private List<Passenger> passengersForBooking = new ArrayList<>();
    private BookingController bc = new BookingController();
    private Flight flight1;
    private Flight flight2;
    private int numberOfPassengers;


    @FXML
    private void alertBoxTooMany(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Too many passengers inserted");
        alert.setContentText("Please select book flight");

        alert.showAndWait();
    }


    @FXML
    private void addPassenger(ActionEvent event){

        //Breyta 4 í fjölda farþega úr SearchViewController
        if (passengers.size() >= numberOfPassengers){
            alertBoxTooMany();
            return;
        }


        String name = this.name.getText();
        String kennitala = this.kennitala.getText();
        passengers.add(name + "\n" + kennitala);
        passengerlist.setItems(passengers);
        passengersForBooking.add(new Passenger(kennitala, name));
    }

    @FXML
    private void bookFlights(ActionEvent event) throws SQLException {
        Booking booking1 = bc.bookFlight(flight1, passengersForBooking, comment.getText());
        Booking booking2 = bc.bookFlight(flight2, passengersForBooking, comment.getText());

        bookingID1.setText(booking1.getBookingID() + "");
        bookingID2.setText(booking2.getBookingID() + "");

        successful.setText("Booking Successful");
        bookingIDs.setText("BookingID's");
    }

    public void setNumberOfPassengers(int n){
        this.numberOfPassengers = n;
    }
    public void setFlight1(Flight flight){
        this.flight1 = flight;
    }
    public void setFlight2(Flight flight){
        this.flight2 = flight;
    }


}
