package be.mcjava.controller;

import be.mcjava.model.CustomerOrder;
import be.mcjava.service.CustomerOrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerScreenController {
    @FXML
    TextField customername;

    @FXML
    TextField customerphonenumber;

    //checks if it is a string with only letters.
    public boolean isName(String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    //checks valid phone-number
    public boolean isPhoneNumber(String customerPhoneNumber) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(customerPhoneNumber);
        return (m.find() && m.group().equals(customerPhoneNumber));
    }

    @FXML
    private void continueFromLoginToMenuPressed(ActionEvent event) throws Exception {
        //if(isName(customername.getText()) && isPhoneNumber(customerphonenumber.getText())) {

        CustomerOrder customerOrder = new CustomerOrder.Builder().name(customername.getText()).telephoneNumber(customerphonenumber.getText()).build();
        CustomerOrderService.customerOrder = customerOrder;

        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
        //ViewManager.setStageDimensions(1000.0,500.0);
    }
}
