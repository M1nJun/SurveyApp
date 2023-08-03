package com.mycompany.surveyfxml;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;

public class ProducerDAO {

    private Statement statement;
    PreparedStatement preparedInsert;

    public ProducerDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey?user=student&password=Cmsc250!");
            statement = connection.createStatement();

            String sqlInsert = "insert into surveys(date,question,options) values(?,?,?);";
            preparedInsert = connection.prepareStatement(sqlInsert);

        } catch (Exception ex) {
            System.out.println("Problem opening database connection.");
            ex.printStackTrace();
        }
    }

    public void addQuestion(Question q) {
        try {
            String options = q.getOption1() + "," + q.getOption2() + "," + q.getOption3() + "," + q.getOption4();
            LocalDate d = q.getDate();
            preparedInsert.setDate(1, java.sql.Date.valueOf(d));
            preparedInsert.setString(2, q.getQuestion());
            preparedInsert.setString(3, options);

            preparedInsert.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
