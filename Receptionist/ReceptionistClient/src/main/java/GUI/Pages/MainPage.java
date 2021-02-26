package GUI.Pages;

import GUI.App;
import GUI.Widgets.Entity;
import GUI.Dialogues.ConnectionError;
import GUI.Widgets.RoundButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static GUI.Pages.Login.nhs;

public class MainPage extends JFrame implements ActionListener {
    private final JButton logoutButton = new RoundButton("");
    private final JButton configurationButton = new RoundButton("");
    private final JLabel numWaiting = new JLabel();
    private final int BASEYGAP = 130;
    private final Timer timer;
    private int entityNum;
    private final String BASE_URL = "https://team34-comp0016-2020.azurewebsites.net/getChanges/";
    private final String SEND_URL = "https://team34-comp0016-2020.azurewebsites.net/addURLID/";
    private final LinkedList<Entity> entities = new LinkedList<>();
    private FileWriter fileWriter = null;
    private final String name;

    public MainPage(String name) {
        this.name = name;
        this.entityNum = 0;
        try {
            fileWriter = new FileWriter(new File(App.currentDirectory + "record.txt"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel nhsIcon = new JLabel();
        nhsIcon.setIcon(nhs);
        nhsIcon.setBounds(30,10,150,55);

        JLabel waiting = new JLabel();
        waiting.setText("callers remaining");
        waiting.setBounds(70,70,400,55);
        waiting.setFont(new Font("Calibri", Font.BOLD  ,35));
        waiting.setForeground(Color.BLACK);

        JLabel title = new JLabel();
        title.setText("Q-Vu System");
        title.setBounds(180,17,200,55);
        title.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC ,35));
        title.setForeground(new Color(0xFFFFFF));

        JLabel username = new JLabel();
        username.setText(name);
        username.setBounds(580,10,300,55);
        username.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        username.setForeground(new Color(0xFFFFFF));

        JLabel loggedinas = new JLabel();
        loggedinas.setText("Logged in as");
        loggedinas.setBounds(380,10,200,55);
        loggedinas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,35));
        loggedinas.setForeground(new Color(0xFFFFFF));

        JSeparator separator = new JSeparator();
        separator.setBounds(10,71,965,5);

        numWaiting.setText(Integer.toString(entityNum));
        numWaiting.setBounds(10,63,60,55);
        numWaiting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,35));
        numWaiting.setForeground(Color.BLACK);

        ImageIcon logout = new ImageIcon(App.currentDirectory + "logout.jpg");
        logoutButton.setIcon(logout);
        logoutButton.setToolTipText("Logout");
        logoutButton.setFocusable(false);
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.setVerticalAlignment(SwingConstants.CENTER);
        logoutButton.setBounds(910,10,55,55);
        logoutButton.setBackground(Color.white);
        logoutButton.addActionListener(this);

        ImageIcon settings = new ImageIcon(App.currentDirectory + "settings.jpg");
        configurationButton.setIcon(settings);
        configurationButton.setToolTipText("Change the link");
        configurationButton.setFocusable(false);
        configurationButton.setHorizontalAlignment(SwingConstants.CENTER);
        configurationButton.setVerticalAlignment(SwingConstants.CENTER);
        configurationButton.setBounds(850,10,55,55);
        configurationButton.setBackground(Color.white);
        configurationButton.addActionListener(this);

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
        this.add(configurationButton);
        this.add(waiting);
        this.add(separator);
        this.add(numWaiting);

        timer = new Timer(5000,this);
    }

    @Override
    //This is a method which listens to the events
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton){
            timer.stop();
            new Login();
            this.dispose();
            try {
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else if(e.getSource() ==configurationButton){
            new Configuration();
        }else if(e.getSource() == timer){
            parse(checkStatus());
        }
    }

    //Initializing the program
    public void initialize()  {
        timer.start();
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
            return "connection error";
        }
    }

    //parse the response and display it on the screen
    private void parse(String response){
        if(response.equals("connection error")){
            new ConnectionError();
            timer.stop();
            return;
        }
        if(!response.equals("\"{'callers':]}\"")){
            response = response.substring(12,response.length()-2);
            String matching = "\\{[^{]*}";
            Pattern regex = Pattern.compile(matching);
            Matcher regexMatcher = regex.matcher(response);
            while(regexMatcher.find()){
                String current = regexMatcher.group();
                if(current.contains("'description'") && !current.contains("'name'")){
                    //case 1: the caller adds a description
                    addDescription(current);
                }else if(current.contains("'active'")){
                    //case 2: the caller leaves the queue
                    leaveQueue(current);
                }else if(current.contains("'name'") && !current.contains("'description'")){
                    //case 3: a new caller is added to the queue without the description
                    newCallerWithoutDescription(current);
                }else{
                    //case 4: a new caller is added to the queue with the description
                    newCallerWithDescription(current);
                }
            }
        }
    }

    private void addDescription(String current){
        boolean translated = !current.substring(current.indexOf("'language'")+13, current.indexOf("'description'")-3).equals("/en/en");
        String id = current.substring(7,current.indexOf("'language'")-2);
        String description = current.substring(current.indexOf("'description'")+16,current.length()-2);
        for(Entity y: entities){
            if(y.getId().equals(id)){
                y.setDescription(description,translated);
                this.repaint();
                break;
            }
        }
    }

    private void leaveQueue(String current){
        String id = current.substring(7,current.indexOf("'active'")-2);
        for(Entity y: entities){
            if(y.getId().equals(id)){
                this.remove(y);
                this.update(id);
                break;
            }
        }
    }

    private void newCallerWithoutDescription(String current){
        String name = current.substring(current.indexOf("'name'")+9,current.indexOf("'dob'")-3);
        if(name.startsWith("\\")){
            name = convertUTF8(name);
        }
        String dob = current.substring(current.indexOf("'dob'")+8,current.indexOf("'id'")-3);
        String id = current.substring(current.indexOf("'id'")+6,current.length()-1);
        entityNum++;
        numWaiting.setText(Integer.toString(entityNum));
        Entity entity = new Entity(name,dob,"",entityNum*BASEYGAP,id);
        entities.add(entity);
        this.add(entity);
        this.repaint();
    }

    private void newCallerWithDescription(String current){
        boolean translated = !current.substring(current.indexOf("'language'")+13, current.indexOf("'description'")-3).equals("/en/en");
        String name = current.substring(current.indexOf("'name'")+9,current.indexOf("'dob'")-3);
        if(name.startsWith("\\")){
            name = convertUTF8(name);
        }
        String dob = current.substring(current.indexOf("'dob'")+8,current.indexOf("'id'")-3);
        String id = current.substring(current.indexOf("'id'")+6,current.indexOf("'language'")-2);
        String description = current.substring(current.indexOf("'description'")+16,current.length()-2);
        entityNum++;
        numWaiting.setText(Integer.toString(entityNum));
        Entity entity = new Entity(name,dob,"",entityNum*BASEYGAP,id);
        entity.setDescription(description,translated);
        entities.add(entity);
        this.add(entity);
        this.repaint();
    }

    //If the caller's name is not english, we have to change the byte values into String
    private String convertUTF8(String name){
        char[] chars = new char[name.length()/6+1];
        int count = 0;
        for(int i = 0; i < name.length(); i += 6){
            String temp = name.substring(i+2,i+6);
            if(name.charAt(i) == ' '){
                i ++;
                temp = name.substring(i+2,i+6);
                chars[count] = ' ';
                count ++;
            }
            chars[count] =  (char)Integer.parseInt(temp,16);
            count ++;
        }
        return new String(chars);
    }

    //Send the relevant information to the server
    public void send(String id, String link, String decision){
//        String info = "1231231123";
//        try{
//            URL url = new URL(SEND_URL);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setUseCaches(false);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
//            writer.write(info);
//            writer.flush();
//            writer.close();
//        }catch (Exception e){
//            e.printStackTrace();
//            new ConnectionError();
//        }
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

    //Save the handling records in a txt file
    public void record(String name, String dob, String description, String receptionist, String decision)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            fileWriter.write(formatter.format(new Date()) + "|" + name + "|" + dob + "|" + description + "|" + receptionist + "|" + decision + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get the name of the receptionist
    public String getReceptionist() {
        return name;
    }
}
