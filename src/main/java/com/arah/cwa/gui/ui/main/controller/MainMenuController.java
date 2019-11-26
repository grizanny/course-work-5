package com.arah.cwa.gui.ui.main.controller;

import com.arah.cwa.gui.ui.AbstractFrameController;
import com.arah.cwa.gui.ui.main.MainMenuFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController extends AbstractFrameController {

    @Autowired
    private MainMenuFrame mainMenuFrame;

    public void prepareAndOpenFrame() {
        mainMenuFrame.setVisible(true);
    }
}
