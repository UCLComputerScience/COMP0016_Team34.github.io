package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Login extends JFrame implements ActionListener {
    public static MainPage mainPage;
    private final JButton loginButton;
    private final JButton needhelpButton;
    private final JTextField usernameInput;
    private final JPasswordField passwordInput;
    public static ImageIcon image = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "login.png");
    public static ImageIcon nhs = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "nhs.png");

    public Login(){
        JLabel nhsIcon = new JLabel();
        nhsIcon.setIcon(nhs);
        nhsIcon.setBounds(30,10,150,55);

        JLabel title = new JLabel();
        title.setText("Q-Vu System");
        title.setBounds(185,17,350,55);
        title.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC ,50));
        title.setForeground(new Color(0xFFFFFF));

        JLabel username = new JLabel();
        username.setText("Username");
        username.setBounds(30,80,200,35);
        username.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        username.setForeground(new Color(0xFFFFFF));

        JLabel password = new JLabel();
        password.setText("Password");
        password.setBounds(30,150,200,35);
        password.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        password.setForeground(new Color(0xFFFFFF));

        usernameInput = new JTextField();
        usernameInput.setBounds(30,115,420,35);
        usernameInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        usernameInput.setBackground(new Color(0x77A8E5));
        usernameInput.setForeground(new Color(0xFFFFFF));
        usernameInput.setCaretColor(new Color(0xFFFFFF));

        passwordInput = new JPasswordField();
        passwordInput.setBounds(30,185,420,35);
        passwordInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        passwordInput.setBackground(new Color(0x77A8E5));
        passwordInput.setForeground(new Color(0xFFFFFF));
        passwordInput.setCaretColor(new Color(0xFFFFFF));

        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        loginButton.setFocusable(false);
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setVerticalAlignment(SwingConstants.CENTER);
        loginButton.setBounds(30,250,205,50);
        loginButton.setBackground(new Color(0x2C90EC));
        loginButton.addActionListener(this);

        needhelpButton = new JButton();
        needhelpButton.setText("Need Help?");
        needhelpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,24));
        needhelpButton.setFocusable(false);
        needhelpButton.setForeground(new Color(0xFFFFFF));
        needhelpButton.setHorizontalAlignment(SwingConstants.CENTER);
        needhelpButton.setVerticalAlignment(SwingConstants.CENTER);
        needhelpButton.setBounds(245,250,205,50);
        needhelpButton.setBackground(new Color(0x2C90EC));
        needhelpButton.addActionListener(this);

        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(2, 95, 185));
        this.setLayout(null);
        this.add(nhsIcon);
        this.add(title);
        this.add(loginButton);
        this.add(needhelpButton);
        this.add(username);
        this.add(password);
        this.add(usernameInput);
        this.add(passwordInput);
        this.setBounds(600,300,500,350);
//        this.getRootPane().setDefaultButton(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            if(searchDatabase(usernameInput.getText(),new String(passwordInput.getPassword()))){
                this.dispose();
                mainPage = new MainPage(usernameInput.getText());
                mainPage.initialize();
            }else{
                usernameInput.setText("");
                passwordInput.setText("");
                InvalidDetail invalidDetail = new InvalidDetail();
            }
        }else if(e.getSource() ==needhelpButton){
            new NeedHelp();
        }

    }

    public boolean searchDatabase(String username,String password){
        return true;
//        if(username.equals("123") && password.equals("123")){
//            return true;
//        }else {
//            return false;
//        }
    }


}
