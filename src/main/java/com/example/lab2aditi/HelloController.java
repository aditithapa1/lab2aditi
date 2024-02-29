package com.example.lab2aditi;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;


public class HelloController implements Initializable {
    @FXML
    private TableColumn<studentinfo,String> adresscolumn;

    @FXML
    private Button deletebutn;

    @FXML
    private TableColumn<studentinfo, Integer> idcoulmn;

    @FXML
    private Button insertbutn;

    @FXML
    private Button loadbutn;

    @FXML
    private TableColumn<studentinfo, String> phonecolumn;

    @FXML
    private TableColumn<studentinfo, String> studentcolumn;

    @FXML
    private Button updatebutn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

     private static final String url="jdbc:mysql://localhost:3306/lab2";
    private static final String password="";
    private static final  String username="root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);}
    public void populateTable() {

        list.clear();


// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_employee`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                int salary = resultSet.getInt("salary");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                tableView.getItems().add(new Employee(id, employee_name, salary,
                        address,age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {

        String Ename =ename.getText();
        String Esalary =esalary.getText();
        String Eaddress =eaddress.getText();
        String Eage =eage.getText();

        InserTable(Ename,Esalary,Eaddress,Eage);
    }


    public void InserTable(String ename,String esalary,String eaddress,String eage) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `tbl_employee`( `employee_name`, `salary`, `address`, `age`) VALUES ('"+ename+"','"+esalary+"','"+eaddress+"','"+eage+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteData(ActionEvent actionEvent) {


        String Eid=eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `tbl_employee` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateData(ActionEvent actionEvent) {

        String Eid =eid.getText();
        String Ename =ename.getText();
        String Esalary =esalary.getText();
        String Eaddress =eaddress.getText();
        String Eage =eage.getText();



        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `tbl_employee` SET `employee_name`='"+Ename+"',`salary`='"+Esalary+"',`address`='"+Eaddress+"',`age`='"+Eage+"' WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {


        String Eid =eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_employee` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                int salary = resultSet.getInt("salary");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");


                ename.setText(employee_name);
                esalary.setText(String.valueOf(salary));
                eaddress.setText(address);
                eage.setText(String.valueOf(age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void set_username(String messge){
        user_name.setText(messge);
    }
}
}