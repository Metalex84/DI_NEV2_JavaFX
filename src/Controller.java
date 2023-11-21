
import java.util.NoSuchElementException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller {

    // Attribs from both FXML TextFields
    @FXML
    public TextField name;
    @FXML
    public TextField surname;

    // The object TableView itself
    @FXML
    public TableView<User> tw;
    // Both TableColumn objects within TableView from FXML
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> colSurname;

    // The ArrayList that contains all User objects
    @FXML
    private ObservableList<User> users;

    @FXML
    public void initialize() {
        users = FXCollections.observableArrayList();
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
    }

    public void newUser() {

        Alert alerta = new Alert(AlertType.CONFIRMATION, "New user request");
        alerta.setHeaderText("Please confirm the new user request");
        alerta.setContentText("NAME: " + name.getText() + "\nSURNAME: " + surname.getText());

        Optional<ButtonType> result = alerta.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            User u1 = new User(name.getText(), surname.getText());
            this.users.add(u1);
            this.tw.setItems(users);
            Alert infoNew = new Alert(AlertType.INFORMATION);
            infoNew.setHeaderText("Data loaded!");
            infoNew.showAndWait();
        }
        else {
            Alert infoNew = new Alert(AlertType.INFORMATION);
            infoNew.setHeaderText("No changes!");
            infoNew.showAndWait();
        }
    }
    
    public void delUser(ActionEvent event) {
        this.tw.getItems();
        ObservableList<User> singleUser;
        try {
            singleUser = this.tw.getSelectionModel().getSelectedItems();
            if (singleUser.isEmpty())
            {
                Alert errAlert = new Alert(AlertType.ERROR);
                errAlert.setHeaderText("Nothing to delete!");
                errAlert.showAndWait();
            }
            else {
                singleUser.forEach(users::remove);
                Alert infoNew = new Alert(AlertType.INFORMATION);
                infoNew.setHeaderText("User deleted from system!");
                infoNew.showAndWait();
            }
        } catch (NoSuchElementException e) {
            Alert errAlert = new Alert(AlertType.WARNING);
            errAlert.setHeaderText("Empty database");
            errAlert.showAndWait();
        }
    }
}