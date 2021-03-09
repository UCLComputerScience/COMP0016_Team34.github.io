package GUI.Dialogues;

import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;

/**
 * the page to be displayed when user clicks "need help" button
 */
public class NeedHelp extends JFrame {

    public NeedHelp(){
        JTextField line1 = new JTextField();
        line1.setBorder(null);
        line1.setDisabledTextColor(Color.black);
        line1.setText("If you forget your password,");
        line1.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line1.setEditable(false);
        line1.setBounds(15,15,460,50);
        line1.setForeground(Color.white);
        line1.setVisible(true);
        line1.setBorder(null);
        line1.setBackground(null);

        JTextField line2 = new JTextField();
        line2.setBorder(null);
        line2.setDisabledTextColor(Color.black);
        line2.setText("Please contact: xxx@nhs.com");
        line2.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line2.setEditable(false);
        line2.setBounds(15,85,460,50);
        line2.setForeground(Color.white);
        line2.setVisible(true);
        line2.setBorder(null);
        line2.setBackground(null);

        JTextField line3 = new JTextField();
        line3.setBorder(null);
        line3.setDisabledTextColor(Color.black);
        line3.setText("If you encounter technical issues,");
        line3.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line3.setEditable(false);
        line3.setBounds(15,155,460,50);
        line3.setForeground(Color.white);
        line3.setVisible(true);
        line3.setBorder(null);
        line3.setBackground(null);

        JTextField line4 = new JTextField();
        line4.setBorder(null);
        line4.setDisabledTextColor(Color.black);
        line4.setText("Please contact: xxx@ucl.ac.uk");
        line4.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line4.setEditable(false);
        line4.setBounds(15,225,460,50);
        line4.setForeground(Color.white);
        line4.setVisible(true);
        line4.setBorder(null);
        line4.setBackground(null);

        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Need Help?");
        this.getContentPane().setBackground(new Color(2, 95, 185));
        this.setBounds(620,320,460,320);
        this.getContentPane().add(line1);
        this.getContentPane().add(line2);
        this.getContentPane().add(line3);
        this.getContentPane().add(line4);
        this.setIconImage(Login.image.getImage());
    }


}
