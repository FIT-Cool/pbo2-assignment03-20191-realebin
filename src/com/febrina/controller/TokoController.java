package com.febrina.controller;

import com.febrina.Main;
import com.febrina.entity.Category;
import com.febrina.entity.Item;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * @author Febrina Anastasha 1772006
 * class TokoController
 */

public class TokoController implements Initializable{

    private ObservableList<Category> categories;
    private ObservableList<Item> items;
    private Item i;

    /**
     * @author Febrina Anastasha 1772006
     * class getCategories
     */
    public ObservableList<Category> getCategories() {
        if(categories == null)
        {
            categories = FXCollections.observableArrayList();
        }
        return categories;
    }

    /**
     * @author Febrina Anastasha 1772006
     * class getItems
     */
    public ObservableList<Item> getItems() {
        if(items == null){
            items = FXCollections.observableArrayList();
        }
        return items;
    }

    @FXML
    private TableView tbToko;
    @FXML
    private TableColumn<Item,String> colID;
    @FXML
    private TableColumn<Item,String> colName;
    @FXML
    private TableColumn<Item,String> colCategory;
    @FXML
    private TableColumn<Item,String> colExpiredDate;
    @FXML
    private DatePicker dpExpiredDate;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btReset;
    @FXML
    private Button btSave;
    @FXML
    private ComboBox<Category> cbxKategori;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    Alert error = new Alert(Alert.AlertType.ERROR);


    /**
     * @author Febrina Anastasha 1772006
     * class FSave
     */
    @FXML
    private void FSave(MouseEvent mouseEvent) {
        Item i = new Item();
        i.set_category(cbxKategori.getValue());
        i.set_name(txtName.getText());
        i.set_expiredDate(String.valueOf(dpExpiredDate.getValue()));
        i.set_id(txtId.getText());
        getItems().add(i);
        txtId.clear();
        txtName.clear();
        cbxKategori.getSelectionModel().clearSelection();
        dpExpiredDate.getEditor().clear();
    }

    /**
     * @author Febrina Anastasha 1772006
     * class FReset
     */
    @FXML
    private void FReset(MouseEvent mouseEvent) {
        txtName.clear();
        txtId.clear();
        cbxKategori.setValue(null);
        btSave.setDisable(false);
        btUpdate.setDisable(true);
    }


    /**
     * @author Febrina Anastasha 1772006
     * class FUpdate
     */
    @FXML
    private void FUpdate(MouseEvent mouseEvent) {
        btSave.setDisable(false);
        btUpdate.setDisable(true);
        int c = 0;
        String tem = i.get_name();
        i.set_id(txtId.getText());
        for (Item b:items){
            if(b.get_name().equals(txtId.getText())){
                c+=1;
            }
        }
        if(c >1){
            error.setContentText("Duplicate ID!");
            error.showAndWait();
            i.set_name(tem);
        }
        else{
            i.set_name(txtName.getText());
            i.set_category(cbxKategori.getValue());
            i.set_expiredDate(String.valueOf(dpExpiredDate.getValue()));
            txtId.clear();
            txtName.clear();
            cbxKategori.getSelectionModel().clearSelection();
            dpExpiredDate.getEditor().clear();
        }
        tbToko.refresh();

    }


    /**
     * @author Febrina Anastasha 1772006
     * class tableClicked
     */
    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
       btSave.setDisable(true);
       btUpdate.setDisable(false);
       i = (Item) tbToko.getSelectionModel().getSelectedItem();

    }

    /**
     * @author Febrina Anastasha 1772006
     * class category
     */
    @FXML
    private void categoryAct(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CategoryManagement.fxml"));
            VBox root = loader.load();
            CategoryController controller = loader.getController();
            controller.setTokoController(this);

            Stage mainStage = new Stage();
            mainStage.setTitle("Faculty Form");
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * @author Febrina Anastasha 1772006
     * class initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categories = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        cbxKategori.setItems(categories);
        tbToko.setItems(items);

        colCategory.setCellValueFactory(data-> {
        Item i = data.getValue();
        return new SimpleStringProperty(i.get_category().toString());
    });

    colExpiredDate.setCellValueFactory(data-> {
        Item i = data.getValue();
        return new SimpleStringProperty(i.get_expiredDate());
    });
    colID.setCellValueFactory(data-> {
        Item i = data.getValue();
        return new SimpleStringProperty(i.get_id());
    });
    colName.setCellValueFactory(data-> {
        Item i = data.getValue();
        return new SimpleStringProperty(i.get_name());
    });
    }

    /**
     * @author Febrina Anastasha 1772006
     * class closeAct
     */
    @FXML
    private void closeAct(ActionEvent actionEvent) {
        Platform.exit();
    }
}
