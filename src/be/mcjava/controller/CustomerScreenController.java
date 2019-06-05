package be.mcjava.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerScreenController {
    @FXML
    TextField customername;

    @FXML
    TextField customerphonenumber;

    //checks if it is a string with only letters.
    public boolean isName(String name) {
        return Pattern.matches( "[a-zA-Z]+", name );
    }

    //checks valid phone-number
    public boolean isPhoneNumber(String customerPhoneNumber) {
        return Pattern.matches( "0[0-9]{8,9}", customerPhoneNumber );
    }

    @FXML
    private void continueFromLoginToMenuPressed(ActionEvent event) throws Exception {
        //if(isName(customername.getText()) && isPhoneNumber(customerphonenumber.getText())) {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../view/CustomerMainMenuOverview.fxml" ) );
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene( new Scene( root1 ) );
        stage.show();
    }
}
