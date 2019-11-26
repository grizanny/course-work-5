package com.arah.cwa.gui;

import com.arah.cwa.gui.ui.main.controller.AuthentificationController;
import com.arah.cwa.gui.ui.main.controller.MainMenuController;
import com.arah.cwa.gui.utils.LockUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GuiApplication {

	public static void main(String[] args) {
		LockUtils.setWindowsLookAndFeel();
		ConfigurableApplicationContext context = SpringApplication.run(GuiApplication.class, args);
//		MainMenuController mainMenuFrame = context.getBean(MainMenuController.class);
//		mainMenuFrame.prepareAndOpenFrame();
		AuthentificationController AuthentificationController = context.getBean(AuthentificationController.class);
		AuthentificationController.prepareAndOpenFrame();
	}

}
