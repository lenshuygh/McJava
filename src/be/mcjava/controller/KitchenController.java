package be.mcjava.controller;

import be.mcjava.dao.CookingOrderDao;
import be.mcjava.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class KitchenController {
    @FXML
    private FlowPane contentPane;
    private List<CookingOrders> ordersToCook = new ArrayList<>();
    
    public void initialize() {
        getOrderData();
    }
    
    private void getOrderData() {
        CookingOrderDao cookingOrderDao = new CookingOrderDao();
        getOrders(cookingOrderDao);
        pollDatabase(cookingOrderDao);
    }
    
    private void addOrderToGrid() {
        contentPane.getChildren().clear();
        for (CookingOrders order : ordersToCook) {
            for (CustomerOrder customerOrder : order.getOrdersToCook()) {
                VBox pane = new VBox();
                pane.setPadding(new Insets(15));
                pane.setStyle("-fx-border-color: black");
                for (AbstractOrderItem abstractOrderItem : customerOrder.getItemsToOrder()) {
                    String productName = ((SingleOrderItem) abstractOrderItem).getItems().getName();
                    String formattedEntry = String.format("%d\t%s", abstractOrderItem.getAmount(), productName);
                    Text text = new Text(formattedEntry);
                    if (abstractOrderItem.isFinished()) {
                        text.strikethroughProperty().setValue(true);
                    }
                    Button finish = new Button("Done");
                    finish.setOnMouseClicked(this::finishedIsPressed);
                    HBox hBox = new HBox(15,finish,text);
                    pane.getChildren().add(hBox);
                }
                Button complete = new Button("Complete order");
                Separator separator = new Separator();
                VBox.setMargin(complete,new Insets(10,0,0,0));
                VBox.setMargin(separator,new Insets(10,0,0,0));
                pane.getChildren().addAll(separator,complete);
                contentPane.getChildren().add(pane);
            }
        }
    }
    
    private void pollDatabase(CookingOrderDao cookingOrderDao) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), event -> getOrders(cookingOrderDao)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private void getOrders(CookingOrderDao cookingOrderDao) {
        ordersToCook = cookingOrderDao.getOrdersToCook();
        addOrderToGrid();
    }
    
    @FXML
    private void finishedIsPressed(MouseEvent event) {
        // add order to list finished orders.
        // when pressed initialize remove order.
        // when pressed
    }
    
    private void removeOrder() {
        // removes order initialized trough an action event method.
    }
    
}
