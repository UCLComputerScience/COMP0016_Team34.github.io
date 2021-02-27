package GUI.Widgets;

import GUI.App;
import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entity extends JPanel implements ActionListener {

    private final JTextArea description = new JTextArea();
    private final JButton send = new JButton();
    private final JButton neglect = new JButton();
    private final JTextArea name = new JTextArea();
    private final JTextArea dob = new JTextArea();
    private JComboBox<String> options;
    private static final ImageIcon cross = new ImageIcon(App.currentDirectory + "cross.jpg");

    private final String id;
    public String getId() {
        return id;
    }
    public int yValue;

    public Entity(String name, String dob, String description, int y,String id){
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

        this.description.setText(description);
        this.description.setEditable(false);
        this.description.setBounds(320,10,500,100);
        this.description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,25));
        this.description.setLineWrap(true);
        this.description.setWrapStyleWord(true);
        this.description.setBackground(null);

        this.send.setBounds(830,85,120,30);
        this.send.setBackground(Color.lightGray);
        this.send.setForeground(Color.white);
        this.send.setText("send");
        this.send.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,25));
        this.send.addActionListener(this);

        this.neglect.setBounds(940,0,20,20);
        this.neglect.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.neglect.setIcon(cross);
        this.neglect.addActionListener(this);

        this.options = new JComboBox<>(Login.mainPage.configuration.names);
        this.options.setBounds(830,50,120,30);
        this.options.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,15));

        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(10,yValue,960,120);
        this.setBackground(new Color(0x72D8FF));
        this.add(this.name);
        this.add(this.dob);
        this.add(this.description);
        this.add(this.send);
        this.add(this.neglect);
        this.add(this.options);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.send){
            Login.mainPage.send(this.id,Login.mainPage.configuration.linkValues[options.getSelectedIndex()],Login.mainPage.configuration.names[options.getSelectedIndex()]);
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),(String) options.getSelectedItem());
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.neglect){
            Login.mainPage.send(this.id,"","This is a warning. Please don't waste public resources");
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),"prank");
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }
    }

    public void changeLocation(int value){
        yValue = value;
        this.setBounds(10,yValue,960,120);
    }

    public void setDescription(String description,boolean translated){
        this.description.setText(description);
        if(translated){
            ImageIcon translatedIcon = new ImageIcon(App.currentDirectory + "translated.png");
            JLabel translatedLabel = new JLabel();
            translatedLabel.setIcon(translatedIcon);
            translatedLabel.setBounds(270,65,50,50);
            translatedLabel.setToolTipText("This is a translated content");
            this.add(translatedLabel);
        }
    }

    public void updateComboBox(){
        options.removeAllItems();
        for(String string : Login.mainPage.configuration.names){
            options.addItem(string);
        }
    }


}
