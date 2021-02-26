package GUI.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configuration extends JFrame implements ActionListener {
    private static String callingLink = "";
    private static String nurseLink = "";
    private static String selfLink = "";
    private final JTextField callingField;
    private final JTextField nurseField;
    private final JTextField selfField;
    private final JButton save;

    public static String getCallingLink() {
        return callingLink;
    }

    public static String getNurseLink() {
        return nurseLink;
    }

    public static String getSelfLink() {
        return selfLink;
    }

    public Configuration() {
        JLabel callingLabel = new JLabel();
        callingLabel.setText("Please enter the link of your room");
        callingLabel.setBounds(10, 0, 700, 60);
        callingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        callingLabel.setForeground(new Color(0xFFFFFF));

        callingField = new JTextField();
        callingField.setText(callingLink);
        callingField.setBounds(10, 60, 760, 60);
        callingField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        callingField.setBackground(new Color(0x77A8E5));
        callingField.setForeground(new Color(0xFFFFFF));
        callingField.setCaretColor(new Color(0xFFFFFF));

        JLabel nurseLabel = new JLabel();
        nurseLabel.setText("Please enter the link of the nurse's room");
        nurseLabel.setBounds(10, 120, 700, 60);
        nurseLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        nurseLabel.setForeground(new Color(0xFFFFFF));

        nurseField = new JTextField();
        nurseField.setText(nurseLink);
        nurseField.setBounds(10, 180, 760, 60);
        nurseField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        nurseField.setBackground(new Color(0x77A8E5));
        nurseField.setForeground(new Color(0xFFFFFF));
        nurseField.setCaretColor(new Color(0xFFFFFF));

        JLabel selfLabel = new JLabel();
        selfLabel.setText("Please enter the self-referral link");
        selfLabel.setBounds(10, 240, 700, 60);
        selfLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        selfLabel.setForeground(new Color(0xFFFFFF));

        selfField = new JTextField();
        selfField.setText(selfLink);
        selfField.setBounds(10, 300, 760, 60);
        selfField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        selfField.setBackground(new Color(0x77A8E5));
        selfField.setForeground(new Color(0xFFFFFF));
        selfField.setCaretColor(new Color(0xFFFFFF));

        save = new JButton();
        save.setText("save");
        save.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        save.setFocusable(false);
        save.setForeground(new Color(0xFFFFFF));
        save.setHorizontalAlignment(SwingConstants.CENTER);
        save.setVerticalAlignment(SwingConstants.CENTER);
        save.setBounds(300,380,200,60);
        save.setBackground(new Color(0x2C90EC));
        save.addActionListener(this);

        this.setTitle("Configure your settings");
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(600, 200, 800, 500);
        this.getContentPane().setBackground(new Color(2, 95, 185));
        this.add(callingLabel);
        this.add(callingField);
        this.add(nurseLabel);
        this.add(nurseField);
        this.add(selfLabel);
        this.add(selfField);
        this.add(save);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save) {
            callingLink = callingField.getText();
            nurseLink = nurseField.getText();
            selfLink = selfField.getText();
            this.dispose();
        }
    }
}
