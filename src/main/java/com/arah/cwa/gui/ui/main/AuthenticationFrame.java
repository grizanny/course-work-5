package com.arah.cwa.gui.ui.main;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class AuthenticationFrame extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPanel panel;

    public AuthenticationFrame() {
        setContentPane(panel);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
