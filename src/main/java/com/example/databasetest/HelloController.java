package com.example.databasetest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Connection myConn = null;
        Statement myStatement = null;
        ResultSet myResults = null;

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/com/example/databasetest/demo.properties"));
            System.out.println("Properties file loaded.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dbURL = props.getProperty("dburl");

        try {
            myConn = DriverManager.getConnection(dbURL, user, password);

            welcomeText.setText("Connection Successful");

            myStatement = myConn.createStatement();

            myResults = myStatement.executeQuery("SELECT * FROM employees");

//            PreparedStatement prep = myConn.prepareStatement("SELECT * FROM employee WHERE salary > ? AND department = ?");
//            prep.setDouble(1, 30000.0);
//            prep.setString(2, "accounting");


            while (myResults.next()) {
                System.out.println(myResults.getString("last_name") + ", " + myResults.getString("first_name"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}