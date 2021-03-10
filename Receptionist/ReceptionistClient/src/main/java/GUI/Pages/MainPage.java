package GUI.Pages;

import GUI.App;
import GUI.Dialogues.ConnectionError;
import GUI.Widgets.Caller;
import GUI.Widgets.RoundButton;
//import com.google.cloud.language.v1.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static GUI.Pages.Login.nhs;

/**
 * The mainpage of the program
 */
public class MainPage extends JFrame implements ActionListener {
    //the links of the server
    private final String BASE_URL;
    private final String FROM_URL;
    private final String SEND_URL;
    private final String LOGOUT_URL;
    //the properties of the ui
    public static Timer timer;
    private final JButton logoutButton = new RoundButton("");
    private final JButton configurationButton = new RoundButton("");
    private final JLabel numWaiting = new JLabel();
    private final int BASEYGAP = 130;
    //the num of callers
    private int callerNum;
    //the list of callers
    private final LinkedList<Caller> callers = new LinkedList<>();
    //write the record in a file, to be initialized in constructor
    private FileWriter fileWriter;
    //the user's username
    private final String userName;
    //a configuration page bound to the main page
    public Configuration configurationPage;
    //a manager which manages all cookies
    public static final CookieManager cookieManager = new CookieManager();

    /**
     * creates a new mainpage
     * @param userName receptionist's username
     * @param url server's link
     */
    public MainPage(String userName, String url) {
        this.configurationPage = new Configuration();
        this.BASE_URL = url;
        this.FROM_URL = BASE_URL + "/getChanges/";
        this.SEND_URL = BASE_URL + "/addURLID/";
        this.LOGOUT_URL = BASE_URL + "/logout/";
        this.userName = userName;
        this.callerNum = 0;

        try{
            fileWriter = new FileWriter(new File(App.currentDirectory + "record.txt"),true);
        }catch (Exception e){
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
        username.setText(userName);
        username.setBounds(580,10,300,55);
        username.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        username.setForeground(new Color(0xFFFFFF));

        JLabel loggedinas = new JLabel();
        loggedinas.setText("Logged in as");
        loggedinas.setBounds(380,10,200,55);
        loggedinas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,35));
        loggedinas.setForeground(new Color(0xFFFFFF));

        JSeparator separator = new JSeparator();
        separator.setBounds(10,71,965,5);

        numWaiting.setText(Integer.toString(callerNum));
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

        this.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(WindowEvent winEvt) {
                logout();
            }
        });

        timer = new Timer(10000,this);
    }

    /**
     * This is a method which listens to the events
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton){
            logout();
            timer.stop();
            new Login();
            this.configurationPage.dispose();
            this.dispose();
            try {
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else if(e.getSource() ==configurationButton){
            timer.stop();
            configurationPage.setVisible(true);
        }else if(e.getSource() == timer){
            parse(checkStatus());
        }
    }

    /**
     * check whether there are changes in the json file sent by the server
     * @return the changes from the server
     */
    private String checkStatus() {
        try{
            URL obj = new URL(FROM_URL);
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

    /**
     * parse the response and display it on the screen
     * @param response the response from the server
     */
    private void parse(String response){
        if(response.equals("connection error")){
            new ConnectionError();
            timer.stop();
            return;
        }
        if(!response.equals("\"{'callers':[]}\"")){
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
        for(Caller caller: callers){
            if(caller.getId().equals(id)){
                caller.setDescription(description,translated);
                this.repaint();
                break;
            }
        }
    }

    private void leaveQueue(String current){
        String id = current.substring(7,current.indexOf("'active'")-2);
        for(Caller y: callers){
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
        callerNum++;
        numWaiting.setText(Integer.toString(callerNum));
        Caller caller = new Caller(name,dob,"", callerNum *BASEYGAP,id);
        callers.add(caller);
        this.add(caller);
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
        callerNum++;
        numWaiting.setText(Integer.toString(callerNum));
        Caller caller = new Caller(name,dob,"", callerNum *BASEYGAP,id);
        caller.setDescription(description,translated);
        callers.add(caller);
        this.add(caller);
        this.repaint();
    }

    /**
     * If the caller's name is not english, we have to change the byte values into String
     * @param name non-english names (something like: \u803f )
     * @return name in english
     */
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

    /**
     * Send the relevant information to the server
     * @param id the id of the caller
     * @param link the link to be sent to the caller
     * @param description the name of the link
     */
    public void send(String id, String link, String description) {
        try{
            link = link.replaceAll("https://", "");
            link = link.replaceAll("http://", "");
            String info = "id=" + id + "&url=" + link + "&description=" + description;
            URL obj = new URL(SEND_URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            os.write(info.getBytes());
            os.flush();
            os.close();
            connection.getResponseCode();
        }catch (Exception e){
            e.printStackTrace();
            new ConnectionError();
        }
    }

    /**
     * After a caller is removed, update the location of all other callers
     * @param id the id of the removed caller
     */
    public void update(String id){
        callerNum--;
        numWaiting.setText(Integer.toString(callerNum));
        callers.removeIf(caller -> caller.getId().equals(id));
        for(int i = 0; i < callers.size(); i++){
            int expectedYvalue = (i+1)*BASEYGAP;
            if(callers.get(i).yValue != expectedYvalue){
                callers.get(i).changeLocation(expectedYvalue);
            }
        }
        this.repaint();
    }

    /**
     * Save the handling records in a txt file
     * @param name the name of the caller
     * @param dob the date of birth of the caller
     * @param description the description sent by the caller
     * @param receptionist the receptionist's username
     * @param decision the name of the link sent to the caller
     */
    public void record(String name, String dob, String description, String receptionist, String decision)  {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            fileWriter.write(formatter.format(new Date()) + "|" + name + "|" + dob + "|" + description + "|" + receptionist + "|" + decision + System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Get the name of the receptionist
     * @return the name of the receptionist
     */
    public String getReceptionist() {
        return userName;
    }

    /**
     * After the user has configured new settings, update combo boxes in all existing callers
     */
    public void updateComboBoxes(){
        for(Caller caller : callers){
            caller.updateComboBox();
        }
        this.repaint();
    }

    /**
     * Logout the account
     */
    private void logout(){
        try{
            URL obj = new URL(LOGOUT_URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * analyze the description using the google api
     * @param description the descriptions from the caller
     * @return an arraylist of all the entities found by google api
     */
//    public ArrayList<String> analyzeDescription(String description){
//        ArrayList<String> results = new ArrayList<>();
//        try (LanguageServiceClient language = LanguageServiceClient.create()) {
//            Document doc = Document.newBuilder().setContent(description).setType(Document.Type.PLAIN_TEXT).build();
//            AnalyzeEntitiesRequest request =
//                    AnalyzeEntitiesRequest.newBuilder()
//                            .setDocument(doc)
//                            .setEncodingType(EncodingType.UTF16)
//                            .build();
//            AnalyzeEntitiesResponse response = language.analyzeEntities(request);
//
//            for (Entity entity : response.getEntitiesList()) {
//                results.add(entity.getName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            new ConnectionError();
//        }
//        results.sort((o1, o2) -> Integer.compare(o2.length(), o1.length()));
//        return results;
//    }
}
