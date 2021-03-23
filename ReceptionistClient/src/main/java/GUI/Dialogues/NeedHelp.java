package GUI.Dialogues;

import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;

/**
 * the page to be displayed when user clicks "need help" button
 */
public class NeedHelp extends JFrame {

    private static final NeedHelp needHelp = new NeedHelp();

    public NeedHelp(){
        JTextField line1 = new JTextField();
        line1.setBorder(null);
        line1.setDisabledTextColor(Color.black);
        line1.setText("If you forget your password,");
        line1.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line1.setEditable(false);
        line1.setBounds(15,0,460,30);
        line1.setForeground(new Color(2,95,185));
        line1.setVisible(true);
        line1.setBorder(null);
        line1.setBackground(null);

        JTextField line2 = new JTextField();
        line2.setBorder(null);
        line2.setDisabledTextColor(Color.black);
        line2.setText("Please contact: xxx@nhs.com");
        line2.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line2.setEditable(false);
        line2.setBounds(15,30,460,30);
        line2.setForeground(new Color(2,95,185));
        line2.setVisible(true);
        line2.setBorder(null);
        line2.setBackground(null);

        JTextField line3 = new JTextField();
        line3.setBorder(null);
        line3.setDisabledTextColor(Color.black);
        line3.setText("If you encounter technical issues,");
        line3.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line3.setEditable(false);
        line3.setBounds(15,60,460,30);
        line3.setForeground(new Color(2,95,185));
        line3.setVisible(true);
        line3.setBorder(null);
        line3.setBackground(null);

        JTextField line4 = new JTextField();
        line4.setBorder(null);
        line4.setDisabledTextColor(Color.black);
        line4.setText("Please contact: xxx@ucl.ac.uk");
        line4.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line4.setEditable(false);
        line4.setBounds(15,90,460,30);
        line4.setForeground(new Color(2,95,185));
        line4.setVisible(true);
        line4.setBorder(null);
        line4.setBackground(null);

        JTextField line5 = new JTextField();
        line5.setBorder(null);
        line5.setDisabledTextColor(Color.black);
        line5.setText("This system is developed by");
        line5.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line5.setEditable(false);
        line5.setBounds(15,120,460,30);
        line5.setForeground(new Color(2,95,185));
        line5.setVisible(true);
        line5.setBorder(null);
        line5.setBackground(null);

        JTextField line6 = new JTextField();
        line6.setBorder(null);
        line6.setDisabledTextColor(Color.black);
        line6.setText("Joshua Mukherjee");
        line6.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line6.setEditable(false);
        line6.setBounds(15,150,460,30);
        line6.setForeground(new Color(2,95,185));
        line6.setVisible(true);
        line6.setBorder(null);
        line6.setBackground(null);

        JTextField line7 = new JTextField();
        line7.setBorder(null);
        line7.setDisabledTextColor(Color.black);
        line7.setText("Tansheng Geng");
        line7.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line7.setEditable(false);
        line7.setBounds(15,180,460,30);
        line7.setForeground(new Color(2,95,185));
        line7.setVisible(true);
        line7.setBorder(null);
        line7.setBackground(null);

        JTextField line8 = new JTextField();
        line8.setBorder(null);
        line8.setDisabledTextColor(Color.black);
        line8.setText("Shaheer Ahmed");
        line8.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line8.setEditable(false);
        line8.setBounds(15,210,460,30);
        line8.setForeground(new Color(2,95,185));
        line8.setVisible(true);
        line8.setBorder(null);
        line8.setBackground(null);

        JTextField line9 = new JTextField();
        line9.setBorder(null);
        line9.setDisabledTextColor(Color.black);
        line9.setText("from University College London");
        line9.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        line9.setEditable(false);
        line9.setBounds(15,240,460,30);
        line9.setForeground(new Color(2,95,185));
        line9.setVisible(true);
        line9.setBorder(null);
        line9.setBackground(null);
        
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Need Help?");
        this.getContentPane().setBackground(Color.white);
        this.setBounds(20,100,460,320);
        this.getContentPane().add(line1);
        this.getContentPane().add(line2);
        this.getContentPane().add(line3);
        this.getContentPane().add(line4);
        this.getContentPane().add(line5);
        this.getContentPane().add(line6);
        this.getContentPane().add(line7);
        this.getContentPane().add(line8);
        this.getContentPane().add(line9);
        this.setIconImage(Login.image.getImage());
    }

    public static void invokeNeedHelp(){
        needHelp.setVisible(true);
    }


}
