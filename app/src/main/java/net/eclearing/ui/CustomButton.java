package net.eclearing.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//Taken from 'Marcel' on stack overflow (didnt want to bother with making a custom button myself)

public class CustomButton extends JButton {
    private final Color pressedColor = new Color(0xC7C7E3);
    private final Color rolloverColor = new Color(0x5C5C70);
    private final Color normalColor = new Color(0x9898B8);;

    public CustomButton (String text) {
        super(text);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(rolloverColor, 2));
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(normalColor);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));
        setText(text);

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                } else {
                    setBackground(normalColor);
                }
            }
        });
    }
}