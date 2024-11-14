package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.language.App;
import com.narraration.Narriator;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Narriator.playSound("secondary");
        App.setRoot("secondary");
    }
}
