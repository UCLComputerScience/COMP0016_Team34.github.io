package GUI.Dialogues;

import GUI.App;
import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;

/**
 * The page to be displayed when the user enters invalid details
 */
public class InvalidDetail extends JFrame {

    private final static InvalidDetail invalidDetail = new InvalidDetail();

    public InvalidDetail(){
        JTextField line1 = new JTextField();
        line1.setBorder(null);
        line1.setDisabledTextColor(Color.black);
        line1.setText("Invalid username or password");
        line1.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line1.setEditable(false);
        line1.setBounds(15,145,460,50);
        line1.setForeground(new Color(2,95,185));
        line1.setVisible(true);
        line1.setBorder(null);
        line1.setBackground(null);

        JTextField line2 = new JTextField();
        line2.setBorder(null);
        line2.setDisabledTextColor(Color.black);
        line2.setText("Please check your details and retry");
        line2.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line2.setEditable(false);
        line2.setBounds(15,195,460,50);
        line2.setForeground(new Color(2,95,185));
        line2.setVisible(true);
        line2.setBorder(null);
        line2.setBackground(null);

        ImageIcon warningPic = new ImageIcon(App.currentDirectory + "warning.png");
        JLabel warning = new JLabel();
        warning.setIcon(warningPic);
        warning.setBounds(150,0,155,150);

        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Invalid Details");
        this.getContentPane().setBackground(Color.white);
        this.setBounds(20,100,460,320);
        this.getContentPane().add(line1);
        this.getContentPane().add(line2);
        this.getContentPane().add(warning);
        this.setIconImage(Login.image.getImage());
    }

    public static void invokeInvalidDetail(){
        invalidDetail.setVisible(true);
    }


}
