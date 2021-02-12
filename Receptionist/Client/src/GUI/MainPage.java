package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static GUI.Login.nhs;

public class MainPage extends JFrame implements ActionListener {
    private MainPage(){}
    private final JButton logoutButton = new JButton();
    private final JLabel numWaiting = new JLabel();
    public static final int BASEYGAP = 130;
    private Timer timer;
    public int entityNum;
    private final String BASE_URL = "https://team34-comp0016-2020.azurewebsites.net/getChanges/";
    private final LinkedList<Entity> entities = new LinkedList<>();

    public MainPage(String name){
        entityNum = 0;

        JLabel nhsIcon = new JLabel();
        nhsIcon.setIcon(nhs);
        nhsIcon.setBounds(30,10,150,55);

        JLabel waiting = new JLabel();
        waiting.setText("callers remaining");
        waiting.setBounds(70,70,300,55);
        waiting.setFont(new Font("Calibri", Font.BOLD  ,35));
        waiting.setForeground(Color.BLACK);

        JLabel title = new JLabel();
        title.setText("Q-Vu System");
        title.setBounds(170,17,350,55);
        title.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC ,35));
        title.setForeground(new Color(0xFFFFFF));

        JLabel username = new JLabel();
        username.setText(name);
        username.setBounds(580,10,300,55);
        username.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        username.setForeground(new Color(0xFFFFFF));

        JLabel loggedinas = new JLabel();
        loggedinas.setText("Logged in as");
        loggedinas.setBounds(380,10,220,55);
        loggedinas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,35));
        loggedinas.setForeground(new Color(0xFFFFFF));

        JSeparator separator = new JSeparator();
        separator.setBounds(10,71,965,5);

        numWaiting.setText(Integer.toString(entityNum));
        numWaiting.setBounds(10,63,60,55);
        numWaiting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,35));
        numWaiting.setForeground(Color.BLACK);

        logoutButton.setText("Logout");
        logoutButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,32));
        logoutButton.setFocusable(false);
        logoutButton.setForeground(new Color(0xFFFFFF));
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.setVerticalAlignment(SwingConstants.CENTER);
        logoutButton.setBounds(830,10,145,55);
        logoutButton.setBackground(new Color(0x2C90EC));
        logoutButton.addActionListener(this);

        this.setTitle("Q-Vu");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(500,40,1000,1000);
        this.getContentPane().setBackground(new Color(2, 95, 185));
        this.add(nhsIcon);
        this.add(title);
        this.add(username);
        this.add(loggedinas);
        this.add(logoutButton);
        this.add(waiting);
        this.add(separator);
        this.add(numWaiting);

        timer = new Timer(1000,this);
    }

    @Override
    //This is a method which listens to the events
    //if the logout button is clicked, logout
    //if 1 sec passes, check changes
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton){
            new Login();
            this.dispose();
            timer.stop();
        }else if(e.getSource() == timer){
            parse(checkStatus());
        }
    }

    //Initializing the program
    public void initialize()  {
        String response = checkStatus();
        if(response.equals("connection error")){
            Entity error = new Entity("","" ,"An unexpected connection error occured, please contact support",BASEYGAP,"0");
            this.add(error);
            this.repaint();
        }else{
            parse(response);
            timer.start();
        }
    }

    //check whether there are changes in the json file
    private String checkStatus() {
        try{
            URL obj = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "connection error";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "connection error";
    }

    //parse the response and display it on the screen
    private void parse(String response){
        if(!response.equals("\"{'callers':]}\"")){
            response = response.substring(12,response.length()-2);
            String matching = "\\{[^{]*}";
            Pattern regex = Pattern.compile(matching);
            Matcher regexMatcher = regex.matcher(response);
            while(regexMatcher.find()){
                String current = regexMatcher.group();
                if(current.contains("description") && !current.contains("name")){
                    //case 1: the caller adds a description
                    String id = current.substring(7,current.indexOf("'description'")-2);
                    String description = current.substring(current.indexOf("'description'")+16,current.length()-2);
                    for(Entity y: entities){
                        if(y.getId().equals(id)){
                            y.setDescription(description);
                            this.repaint();
                            break;
                        }
                    }
                }else if(current.contains("active")){
                    //case 2: the caller leaves the queue
                    String id = current.substring(7,current.indexOf("'active'")-2);
                    for(Entity y: entities){
                        if(y.getId().equals(id)){
                            this.remove(y);
                            this.update(id);
                            break;
                        }
                    }
                }else if(current.contains("name") && !current.contains("description")){
                    //case 3: a new caller is added to the queue without the description
                    String name = current.substring(current.indexOf("'name'")+9,current.indexOf("'dob'")-3);
                    String dob = current.substring(current.indexOf("'dob'")+8,current.indexOf("'id'")-3);
                    String id = current.substring(current.indexOf("'id'")+6,current.length()-1);
                    entityNum++;
                    numWaiting.setText(Integer.toString(entityNum));
                    Entity entity = new Entity(name,dob,"",entityNum*BASEYGAP,id);
                    entities.add(entity);
                    this.add(entity);
                    this.repaint();
                }else{
                    //case 4: a new caller is added to the queue with the description
                    String name = current.substring(current.indexOf("'name'")+9,current.indexOf("'dob'")-3);
                    String dob = current.substring(current.indexOf("'dob'")+8,current.indexOf("'id'")-3);
                    String id = current.substring(current.indexOf("'id'")+6,current.indexOf("'description'")-2);
                    String description = current.substring(current.indexOf("'description'")+16,current.length()-2);
                    entityNum++;
                    numWaiting.setText(Integer.toString(entityNum));
                    Entity entity = new Entity(name,dob,description,entityNum*BASEYGAP,id);
                    entities.add(entity);
                    this.add(entity);
                    this.repaint();
                }
            }
        }

    }

    //After an entity is removed, update the location of all other entities
    public void update(String id){
        entityNum --;
        numWaiting.setText(Integer.toString(entityNum));
        entities.removeIf(entity -> entity.getId().equals(id));
        for(int i = 0; i < entities.size(); i++){
            int expectedYvalue = (i+1)*BASEYGAP;
            if(entities.get(i).yValue != expectedYvalue){
                entities.get(i).changeLocation(expectedYvalue);
            }
        }
        this.repaint();
    }
}
