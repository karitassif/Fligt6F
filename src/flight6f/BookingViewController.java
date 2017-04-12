package flight6f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * Created by atlim on 12.4.2017.
 */
public class BookingViewController {

    @FXML
    private TextField name;

    @FXML
    private TextField kennitala;

    @FXML
    ListView<String> passengerlist;

    @FXML
    ObservableList<String> passengers = FXCollections.observableArrayList();




    @FXML
    private void addPassenger(ActionEvent event){
        String name = this.name.getText();
        String kennitala = this.kennitala.getText();
        passengers.add(name + "\n" + kennitala);
        passengerlist.setItems(passengers);
    }

}
