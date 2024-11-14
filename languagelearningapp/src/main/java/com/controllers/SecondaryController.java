package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import com.language.App;
import com.narraration.Narriator;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Narriator.playSound("primary");
        App.setRoot("primary");

    }
}