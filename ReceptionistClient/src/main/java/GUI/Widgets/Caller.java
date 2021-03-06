package GUI.Widgets;

import GUI.App;
import GUI.Dialogues.AddMessage;
import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A caller
 */
public class Caller extends JPanel implements ActionListener {

    //ui properties
    private final AddMessage messageBox = new AddMessage(this);
    private final JEditorPane description = new JEditorPane();
    private final JButton send = new JButton();
    private final JButton addMessage = new JButton();
    private final JButton neglect = new JButton();
    private final JTextArea name = new JTextArea();
    private final JTextArea dob = new JTextArea();
    private final JComboBox<String> options;
    private static final ImageIcon cross = new ImageIcon(App.currentDirectory + "cross.jpg");
    //a string without html format to be recorded
    private String descriptionWithoutFormat;
    //the id of the caller
    private final String id;
    //the vertical value of the caller
    public int yValue;
    //the message to the caller
    private String message;

    /**
     * creating a caller
     * @param name the name of the caller
     * @param dob the date of birth of the caller
     * @param description the description of the caller
     * @param y the vertical y value of the caller
     * @param id the id of the caller
     */
    public Caller(String name, String dob, String description, int y, String id){
        this.yValue = y;
        this.id = id;
        
        this.name.setText(name);
        this.name.setEditable(false);
        this.name.setBounds(10,10,300,45);
        this.name.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,30));
        this.name.setBackground(null);
        
        this.dob.setText(dob);
        this.dob.setEditable(false);
        this.dob.setBounds(10,65,200,45);
        this.dob.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,30));
        this.dob.setBackground(null);

        this.description.setContentType("text/html");
        this.description.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        this.description.setText(description);
        this.description.setEditable(false);
        this.description.setBounds(320,5,500,110);
        this.description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,20));
        this.description.setBackground(null);

        this.send.setBounds(830,85,120,30);
        this.send.setBackground(Color.BLACK);
        this.send.setForeground(Color.white);
        this.send.setText("send link");
        this.send.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,12));
        this.send.addActionListener(this);

        this.addMessage.setBounds(830,53,120,30);
        this.addMessage.setBackground(Color.BLACK);
        this.addMessage.setForeground(Color.white);
        this.addMessage.setText("add message");
        this.addMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,12));
        this.addMessage.addActionListener(this);

        this.neglect.setBounds(940,0,20,20);
        this.neglect.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.neglect.setIcon(cross);
        this.neglect.addActionListener(this);

        this.options = new JComboBox<>(Login.mainPage.configurationPage.names);
        this.options.setBounds(830,20,120,30);
        this.options.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,15));

        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(10,yValue,960,120);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(new Color(2,95,185)));
        this.add(this.name);
        this.add(this.dob);
        this.add(this.description);
        this.add(this.send);
        this.add(this.addMessage);
        this.add(this.neglect);
        this.add(this.options);
    }

    /**
     * listens to all the events
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.send){
            Login.mainPage.send(this.id,Login.mainPage.configurationPage.linkValues[options.getSelectedIndex()],Login.mainPage.configurationPage.names[options.getSelectedIndex()],this.message);
            Login.mainPage.record(this.descriptionWithoutFormat,Login.mainPage.getReceptionist(),(String) options.getSelectedItem(),Login.mainPage.configurationPage.linkValues[options.getSelectedIndex()],this.message);
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.neglect){
            Login.mainPage.send(this.id,"","","This is a warning. Please don't waste public resources");
            Login.mainPage.record(this.descriptionWithoutFormat,Login.mainPage.getReceptionist(),"prank","","");
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.addMessage){
            this.messageBox.setVisible(true);
        }else if(e.getSource() == this.messageBox.saveAndClose){
            this.messageBox.setVisible(false);
            this.message = this.messageBox.line2.getText();
        }else if(e.getSource() == this.messageBox.sendWithoutLink){
            this.messageBox.setVisible(false);
            this.message = this.messageBox.line2.getText();
            Login.mainPage.send(this.id,"","",this.message);
            Login.mainPage.record(this.descriptionWithoutFormat,Login.mainPage.getReceptionist(),"","",this.message);
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }
    }

    /**
     * get the id of the caller
     * @return the id of the caller
     */
    public String getId() {
        return id;
    }

    /**
     * change the location of the caller on the screen
     * @param value the vertical value to be set
     */
    public void changeLocation(int value){
        yValue = value;
        this.setBounds(10,yValue,960,120);
    }

    /**
     * set the description of caller and display it on the screen
     * @param description the description
     * @param translated whether it is translated from other languages
     */
    public void setDescription(String description,boolean translated){
        this.descriptionWithoutFormat = description;
        ArrayList<String> importants = Login.mainPage.analyzeDescription(description);
        if(importants == null){
            this.description.setText(description);
        } else {
            for (String important : importants){
                description = description.replace(important,"<b>" + important + "</b>");
            }
            this.description.setText(description);
        }
        if(translated){
            ImageIcon translatedIcon = new ImageIcon(App.currentDirectory + "translated.png");
            JLabel translatedLabel = new JLabel();
            translatedLabel.setIcon(translatedIcon);
            translatedLabel.setBounds(270,65,50,50);
            translatedLabel.setToolTipText("This is a translated content");
            this.add(translatedLabel);
        }
    }

    /**
     * after the user has add/removed/changed any link, update all the drop down boxes
     */
    public void updateComboBox(){
        options.removeAllItems();
        for(String string : Login.mainPage.configurationPage.names){
            options.addItem(string);
        }
    }


}
