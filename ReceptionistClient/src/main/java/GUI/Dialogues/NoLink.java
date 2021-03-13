package GUI.Dialogues;

import GUI.App;
import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;

/**
 * The page to be displayed when the user has not set any valid link to send to the callers
 */
public class NoLink extends JFrame {
    public NoLink(){
        JTextField line1 = new JTextField();
        line1.setBorder(null);
        line1.setDisabledTextColor(Color.black);
        line1.setText("You have not provided any valid link");
        line1.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line1.setEditable(false);
        line1.setBounds(20,145,460,50);
        line1.setForeground(Color.white);
        line1.setVisible(true);
        line1.setBorder(null);
        line1.setBackground(null);

        JTextField line2 = new JTextField();
        line2.setBorder(null);
        line2.setDisabledTextColor(Color.black);
        line2.setText("Please enter at least one link");
        line2.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line2.setEditable(false);
        line2.setBounds(50,195,460,50);
        line2.setForeground(Color.white);
        line2.setVisible(true);
        line2.setBorder(null);
        line2.setBackground(null);

        ImageIcon warningPic = new ImageIcon(App.currentDirectory + "warning.png");
        JLabel warning = new JLabel();
        warning.setIcon(warningPic);
        warning.setBounds(150,0,155,150);

        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("No Link");
        this.getContentPane().setBackground(new Color(2, 95, 185));
        this.setBounds(800,330,460,310);
        this.getContentPane().add(line1);
        this.getContentPane().add(line2);
        this.getContentPane().add(warning);
        this.setIconImage(Login.image.getImage());
    }
}
