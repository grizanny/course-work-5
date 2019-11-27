package com.arah.cwa.gui.ui.main;

import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.entity.UserCredentials;
import com.arah.cwa.gui.rest.producer.UserProducer;
import com.arah.cwa.gui.security.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class AuthenticationFrame extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPanel panel;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserProducer userProducer;

    public AuthenticationFrame() {
        setContentPane(panel);
        setVisible(true);
        initButtons();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initButtons() {

        //здесь типа есть в базе пользователь с кредами qwerty/qwerty
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername("qwerty");
        userCredentials.setPassword("qwerty");

        OKButton.addActionListener(actionEvent -> {
            securityManager.refreshToken(userCredentials);

            List<User> all = userProducer.findAll();
            User user = all.get(0);
            JOptionPane.showMessageDialog(
                    null,
                    user.getLogin() + ", hello my friend!",
                    "" + user.getEmail(),
                    JOptionPane.INFORMATION_MESSAGE);
        });
        
        getContentPane().add(panel);
    }


}
