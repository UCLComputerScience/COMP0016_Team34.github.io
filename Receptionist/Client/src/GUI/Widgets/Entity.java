package GUI.Widgets;

import GUI.Pages.Configuration;
import GUI.Pages.Login;
import GUI.Dialogues.EmptyField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Entity extends JPanel implements ActionListener {

    private final JTextArea description = new JTextArea();
    private final JButton call = new JButton();
    private final JButton sendLink = new JButton();
    private final JButton sendNurse = new JButton();
    private final JButton neglect = new JButton();
    private final JTextArea name = new JTextArea();
    private final JTextArea dob = new JTextArea();
    private static final ImageIcon phone = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "phone.jpg");
    private static final ImageIcon chain = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "chain.jpg");
    private static final ImageIcon nurse = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "nurse.jpg");
    private static final ImageIcon cross = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "cross.jpg");

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

        this.call.setBounds(840,10,50,50);
        this.call.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.call.setIcon(phone);
        this.call.addActionListener(this);

        this.sendLink.setBounds(900,10,50,50);
        this.sendLink.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.sendLink.setIcon(chain);
        this.sendLink.addActionListener(this);

        this.sendNurse.setBounds(840,65,50,50);
        this.sendNurse.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.sendNurse.setIcon(nurse);
        this.sendNurse.addActionListener(this);

        this.neglect.setBounds(900,65,50,50);
        this.neglect.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        this.neglect.setIcon(cross);
        this.neglect.addActionListener(this);

        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(10,yValue,960,120);
        this.setBackground(new Color(0x72D8FF));
        this.add(this.name);
        this.add(this.dob);
        this.add(this.description);
        this.add(this.call);
        this.add(this.sendLink);
        this.add(this.sendNurse);
        this.add(this.neglect);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.call){
            if(Configuration.getCallingLink().equals("")){
                new EmptyField();
                return;
            }
            Login.mainPage.send(this.id,Configuration.getCallingLink(),"Please click this link and talk to our receptionist:");
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),"RECEPTIONIST");
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.sendLink){
            if(Configuration.getSelfLink().equals("")){
                new EmptyField();
                return;
            }
            Login.mainPage.send(this.id,Configuration.getSelfLink(),"Please click this link and talk to our nurse:");
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),"SELF-REFERRAL");
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.sendNurse){
            if(Configuration.getNurseLink().equals("")){
                new EmptyField();
                return;
            }
            Login.mainPage.send(this.id,Configuration.getNurseLink(),"Please click this self-referral link:");
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),"NURSE");
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.neglect){
            Login.mainPage.send(this.id,"","This is a warning. Please don't waste public resources");
            Login.mainPage.record(this.name.getText(),this.dob.getText(),this.description.getText(),Login.mainPage.getReceptionist(),"PRANK");
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
            ImageIcon translatedIcon = new ImageIcon(System.getProperty("user.dir") + File.separator + "src" + File.separator + "translated.png");
            JLabel translatedLabel = new JLabel();
            translatedLabel.setIcon(translatedIcon);
            translatedLabel.setBounds(270,65,50,50);
            translatedLabel.setToolTipText("This is a translated content");
            this.add(translatedLabel);
        }

    }

}
