package GUI.Dialogues;


import GUI.Pages.Login;
import GUI.Widgets.Caller;

import javax.swing.*;
import java.awt.*;

public class AddMessage extends JFrame {
    Caller caller;

    public JButton sendWithoutLink;
    public JButton saveAndClose;
    public JTextField line1;
    public JTextArea line2;

    public AddMessage(Caller caller){
        this.caller = caller;

        line1 = new JTextField();
        line1.setBorder(null);
        line1.setDisabledTextColor(Color.black);
        line1.setText("Please enter the message for this caller:");
        line1.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line1.setEditable(false);
        line1.setBounds(10,10,500,30);
        line1.setForeground(new Color(2,95,185));
        line1.setVisible(true);
        line1.setBorder(null);
        line1.setBackground(null);

        line2 = new JTextArea();
        line2.setDisabledTextColor(Color.black);
        line2.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line2.setEditable(true);
        line2.setLineWrap(true);
        line2.setBounds(10,50,490,180);
        line2.setForeground(new Color(2,95,185));
        line2.setVisible(true);
        line2.setBorder(BorderFactory.createLineBorder(new Color(2,95,185)));

        sendWithoutLink = new JButton();
        sendWithoutLink.setText("send without link");
        sendWithoutLink.setBounds(10,240,240,30);
        sendWithoutLink.setBackground(new Color(2,95,185));
        sendWithoutLink.setForeground(Color.white);
        sendWithoutLink.addActionListener(caller);

        saveAndClose = new JButton();
        saveAndClose.setText("save and close");
        saveAndClose.setBounds(260,240,240,30);
        saveAndClose.setBackground(new Color(2,95,185));
        saveAndClose.setForeground(Color.white);
        saveAndClose.addActionListener(caller);

        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Add message");
        this.getContentPane().setBackground(Color.white);
        this.setBounds(20,100,530,320);
        this.getContentPane().add(line1);
        this.getContentPane().add(line2);
        this.getContentPane().add(sendWithoutLink);
        this.getContentPane().add(saveAndClose);
        this.setIconImage(Login.image.getImage());
        this.setVisible(false);
    }



}