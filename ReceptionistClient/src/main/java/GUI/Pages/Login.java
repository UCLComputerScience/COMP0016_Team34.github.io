package GUI.Pages;

import GUI.App;
import GUI.Dialogues.ConnectionError;
import GUI.Dialogues.NeedHelp;
import GUI.Dialogues.InvalidDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The login page
 */
public class Login extends JFrame implements ActionListener {
    //the main page used in the program is a static attribute of the Login class
    public static GUI.Pages.MainPage mainPage;
    //the properties of the ui
    private final JButton loginButton;
    private final JButton needhelpButton;
    private final JTextField usernameInput;
    private final JPasswordField passwordInput;
    private final JTextField linkInput;
    public static ImageIcon image = new ImageIcon(App.currentDirectory + "login.png");
    public static ImageIcon nhs = new ImageIcon(App.currentDirectory + "nhs.png");

    public Login() {
        JLabel nhsIcon = new JLabel();
        nhsIcon.setIcon(nhs);
        nhsIcon.setBounds(30, 10, 150, 55);

        JLabel title = new JLabel();
        title.setText("Q-Vu System");
        title.setBounds(185, 17, 350, 55);
        title.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC, 50));
        title.setForeground(new Color(0xFFFFFF));

        JLabel username = new JLabel();
        username.setText("Username");
        username.setBounds(30, 180, 200, 35);
        username.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        username.setForeground(new Color(0xFFFFFF));

        JLabel password = new JLabel();
        password.setText("Password");
        password.setBounds(30, 250, 200, 35);
        password.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        password.setForeground(new Color(0xFFFFFF));

        JLabel serverLabel = new JLabel();
        serverLabel.setText("Enter the link of your server");
        serverLabel.setBounds(30, 80, 400, 35);
        serverLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        serverLabel.setForeground(new Color(0xFFFFFF));

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.white);
        separator.setBounds(0, 165, 485, 5);

        linkInput = new JTextField();
        linkInput.setBounds(30, 115, 420, 35);
        linkInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        linkInput.setBackground(new Color(0x77A8E5));
        linkInput.setForeground(new Color(0xFFFFFF));
        linkInput.setCaretColor(new Color(0xFFFFFF));

        linkInput.setText("https://team34-comp0016-2020.azurewebsites.net");

        usernameInput = new JTextField();
        usernameInput.setBounds(30, 215, 420, 35);
        usernameInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        usernameInput.setBackground(new Color(0x77A8E5));
        usernameInput.setForeground(new Color(0xFFFFFF));
        usernameInput.setCaretColor(new Color(0xFFFFFF));

        usernameInput.setText("ReceptionistTest");

        passwordInput = new JPasswordField();
        passwordInput.setBounds(30, 285, 420, 35);
        passwordInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        passwordInput.setBackground(new Color(0x77A8E5));
        passwordInput.setForeground(new Color(0xFFFFFF));
        passwordInput.setCaretColor(new Color(0xFFFFFF));

        passwordInput.setText("APassword");

        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        loginButton.setFocusable(false);
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setVerticalAlignment(SwingConstants.CENTER);
        loginButton.setBounds(30, 350, 205, 50);
        loginButton.setBackground(new Color(0x2C90EC));
        loginButton.addActionListener(this);

        needhelpButton = new JButton();
        needhelpButton.setText("Need Help?");
        needhelpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        needhelpButton.setFocusable(false);
        needhelpButton.setForeground(new Color(0xFFFFFF));
        needhelpButton.setHorizontalAlignment(SwingConstants.CENTER);
        needhelpButton.setVerticalAlignment(SwingConstants.CENTER);
        needhelpButton.setBounds(245, 350, 205, 50);
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
        this.add(serverLabel);
        this.add(usernameInput);
        this.add(passwordInput);
        this.add(linkInput);
        this.add(separator);
        this.setBounds(0, 0, 500, 450);
        this.getRootPane().setDefaultButton(loginButton);
    }

    /**
     * listens to all the events happening in this page
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (confirmIdentity(usernameInput.getText(), new String(passwordInput.getPassword()), linkInput.getText())) {
                this.dispose();
                mainPage = new GUI.Pages.MainPage(usernameInput.getText(), linkInput.getText());
                mainPage.configurationPage.setVisible(true);
            } else {
                usernameInput.setText("");
                passwordInput.setText("");
                new InvalidDetail();
            }
        } else if (e.getSource() == needhelpButton) {
            new NeedHelp();
        }

    }

    /**
     * Send the username and password to the server link and gets a boolean on whether they are correct
     * @param username the username of the user
     * @param password the password of the user
     * @param link the link of the server
     * @return true if the details are valid, else false
     */
    public boolean confirmIdentity(String username, String password, String link) {
        try {
            URL obj = new URL(link + "/login/");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            String test = "username=" + username + "&" + "password=" + password;
            os.write(test.getBytes());
            os.flush();
            os.close();
            int responseCode = postConnection.getResponseCode();
            String result = "";
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                result = response.toString();
            } else {
                System.out.println("POST NOT WORKED");
            }
            return Boolean.parseBoolean(result.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            new ConnectionError();
        }
        return false;
    }


}
