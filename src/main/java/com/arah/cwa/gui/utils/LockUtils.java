package com.arah.cwa.gui.utils;

import lombok.experimental.UtilityClass;

import javax.swing.*;

@UtilityClass
public class LockUtils {

    public static void setWindowsLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error: " + e,
                    "Alert",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
