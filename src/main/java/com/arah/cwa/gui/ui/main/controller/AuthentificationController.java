package com.arah.cwa.gui.ui.main.controller;

import com.arah.cwa.gui.ui.AbstractFrameController;
import com.arah.cwa.gui.ui.main.AuthenticationFrame;
import com.arah.cwa.gui.ui.main.MainMenuFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthentificationController extends AbstractFrameController {

    @Autowired
    private AuthenticationFrame authenticationFrame;

    public void prepareAndOpenFrame() {
        authenticationFrame.setVisible(true);
    }
}
