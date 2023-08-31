package com.example.demo.service;

import com.example.demo.model.AppProperties;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class DatabaseService {

    private static final String URL = "jdbc:postgresql://localhost:5432/trial";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "test123123";

    public AppProperties getProperties() {
        AppProperties properties = new AppProperties();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM properties");

            while (resultSet.next()) {
                properties.setApp_name(resultSet.getString("app_name"));
                properties.setVersion(resultSet.getInt("version"));
                properties.setColor(resultSet.getString("backgroundColor"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void addProperties(AppProperties properties) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO properties (app_name, backgroundColor) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, properties.getApp_name());
            preparedStatement.setString(2, properties.getColor());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
