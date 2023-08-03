package com.mycompany.surveyfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

    @FXML
    private TextField question;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;
    @FXML
    private TextField op3;
    @FXML
    private TextField op4;
    @FXML
    private Text result;
    @FXML
    DatePicker datePicker;
    private ProducerDAO dao;

    @FXML
    private void addQuestion(ActionEvent event) {

        if (question.getText() == "" || op1.getText() == "" || op2.getText() == "" || op3.getText() == "" || op4.getText() == "" || datePicker.getValue()==null) {
            result.setText("Please make sure to fill in all the fields.");
        } else {
            Question q = new Question(question.getText(), op1.getText(), op2.getText(), op3.getText(), op4.getText(), datePicker.getValue());
            dao.addQuestion(q);
            result.setText("Question added!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new ProducerDAO();
    }
}
