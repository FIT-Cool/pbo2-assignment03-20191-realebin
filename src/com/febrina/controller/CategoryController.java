package com.febrina.controller;

import com.febrina.entity.Category;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Febrina Anastasha 1772006
 * class CategoryController
 */
public class CategoryController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Category> tbCategory;
    @FXML
    private TableColumn<Category,String> colID;
    @FXML
    private TableColumn<Category,String> colCategory;


    /**
     * @author Febrina Anastasha 1772006
     * class setTokoController
     */
    public void setTokoController(TokoController tokoController) {
        this.tokoController = tokoController;
        tbCategory.setItems(tokoController.getCategories());

    }


    private TokoController tokoController;
    @FXML
    /**
     * @author Febrina Anastasha 1772006
     * class tableClicked
     */
    private void tableClicked(MouseEvent mouseEvent) {
        Category c = tbCategory.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(c.getId()));

    }

    /**
     * @author Febrina Anastasha 1772006
     * class FSave
     */
    @FXML
    private void FSave(MouseEvent mouseEvent) {
        Category c = new Category();
        c.setName(txtName.getText().trim());
        c.setId(Integer.valueOf(txtId.getText().trim()));
        tokoController.getCategories().add(c);
    }

    /**
     * @author Febrina Anastasha 1772006
     * class initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCategory.setCellValueFactory(data-> {
            Category c = data.getValue();
            return new SimpleStringProperty(c.getName());
        });
        colID.setCellValueFactory(data-> {
            Category c = data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getId()));
        });
    }
}
