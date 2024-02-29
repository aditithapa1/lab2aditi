package com.example.lab2aditi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField phoneinput, nameinput, addressinput, idinput;
    @FXML
    private TableColumn<studentinfo, String> addresscolumn;
    @FXML
    private TableColumn<studentinfo, Integer> idcolumn;
    @FXML
    private TableColumn<studentinfo, String> phonecolumn, studentcolumn;
    @FXML
    private TableView<studentinfo> tableforstudent;

    private ObservableList<studentinfo> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentcolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phonecolumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addresscolumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableforstudent.setItems(studentList);
    }

    @FXML
    void loaddata(ActionEvent event) {
        populateTable();
    }

    public void populateTable() {
        studentList.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM studentinfo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                studentList.add(new studentinfo(id, name, phone, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void insertdata(ActionEvent event) {
        String name = nameinput.getText();
        String phone = phoneinput.getText();
        String address = addressinput.getText();
        insertTable(name, phone, address);
        populateTable();
    }

    public void insertTable(String name, String phone, String address) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO studentinfo (name, phone, address) VALUES ('" + name + "','" + phone + "','" + address + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deletedata(ActionEvent actionEvent) {
        String sid = idinput.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM studentinfo WHERE id='" + sid + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    @FXML
    void updatedata(ActionEvent actionEvent) {
        String sid = idinput.getText();
        String sname = nameinput.getText();
        String sphone = phoneinput.getText();
        String saddress = addressinput.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE studentinfo SET name='" + sname + "', phone='" + sphone + "', address='" + saddress + "' WHERE id='" + sid + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        populateTable();
    }

}