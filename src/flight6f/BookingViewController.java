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
    SearchViewController searchViewController = new SearchViewController();

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


    List<Passenger> passengersForBooking = new ArrayList<>();
    BookingController bc = new BookingController();


    @FXML
    private void alerBoxTooMany(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Too many passengers inserted");
        alert.setContentText("Please select book flight");

        alert.showAndWait();
    }


    @FXML
    private void addPassenger(ActionEvent event){


        String name = this.name.getText();
        String kennitala = this.kennitala.getText();
        passengers.add(name + "\n" + kennitala);
        passengerlist.setItems(passengers);
        passengersForBooking.add(new Passenger(kennitala, name));
    }

    @FXML
    private void bookFlights(ActionEvent event) throws SQLException {
        successful.setText("Booking Successful");
        bookingIDs.setText("BookingID's");
    }


}
