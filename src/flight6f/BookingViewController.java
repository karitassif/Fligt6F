package flight6f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    private void alertBoxTooFew(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Too few passengers inserted");
        alert.setContentText("Please insert more passengers");

        alert.showAndWait();
    }
    @FXML
    private void alertBoxInvalidKennitala(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        alert.setTitle("oops!");
        alert.setHeaderText("Invalid Kennitala");
        alert.setContentText("Please input a valid kennitala");

        alert.showAndWait();
    }

    private boolean validateKennitala(String kt){
        return kt.matches("\\d*") && kt.length() == 10;
    }


    @FXML
    private void addPassenger(ActionEvent event){


        if (passengers.size() >= numberOfPassengers){
            alertBoxTooMany();
            return;
        }

        String name = this.name.getText();
        String kennitala = this.kennitala.getText();
        int b;
        b = passengers.size()+1;

        if (!validateKennitala(kennitala)){
            alertBoxInvalidKennitala();
            return;
        }

            passengers.add("Passenger " + b + ":" + "\n" + name + "\n" + kennitala);
            passengerlist.setItems(passengers);
            passengersForBooking.add(new Passenger(kennitala, name));
            this.name.clear();
            this.kennitala.clear();

    }

    @FXML
    private void bookFlights(ActionEvent event) throws SQLException {
        if (passengers.size() < numberOfPassengers){
            alertBoxTooFew();
            return;
        }
        Booking booking1 = bc.bookFlight(flight1, passengersForBooking, comment.getText());
        Booking booking2 = bc.bookFlight(flight2, passengersForBooking, comment.getText());

        bookingID1.setText(booking1.getBookingID() + "");
        bookingID2.setText(booking2.getBookingID() + "");

        successful.setText("Booking Successful!");
        bookingIDs.setText("BookingID's");
    }



    @FXML
    private void searchWindow(ActionEvent event) throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("searchpanel.fxml"));

            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Flight6F Search Engine");
            stage.setScene(scene);

            Node source = (Node) event.getSource();
            Stage searchStage = (Stage) source.getScene().getWindow();
            searchStage.close();

            stage.show();
        }
        catch (IOException e) {
            System.err.println("Failed to create new Window." + e);
        }
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
