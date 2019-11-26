package com.arah.cwa.gui.ui.main;

import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.entity.UserCredentials;
import com.arah.cwa.gui.rest.producer.UserProducer;
import com.arah.cwa.gui.security.SecurityManager;
import com.arah.cwa.gui.utils.LockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class MainMenuFrame extends JFrame {

    private JButton helloButton;

    private JPanel helloPanel;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserProducer userProducer;

    public MainMenuFrame() {
        setFrameUp();
        initButtons();
        pack();
    }

    private void setFrameUp() {
        getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        setTitle("English School");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        LockUtils.setWindowsLookAndFeel();
        setLayout(new GridLayout(1, 2, 20, 20));
    }

    private void initButtons() {
        helloButton = new JButton("login");

        //здесь типа есть в базе пользователь с кредами qwerty/qwerty
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername("qwerty");
        userCredentials.setPassword("qwerty");

        helloButton.addActionListener(actionEvent -> {
            securityManager.refreshToken(userCredentials);

            List<User> all = userProducer.findAll();
            User user = all.get(0);
            JOptionPane.showMessageDialog(
                null,
                    user.getLogin() + ", hello my friend!",
                    "" + user.getEmail(),
                    JOptionPane.INFORMATION_MESSAGE);
        });

        helloPanel = new JPanel();
        helloPanel.add(helloButton);
        getContentPane().add(helloPanel);
    }
}
