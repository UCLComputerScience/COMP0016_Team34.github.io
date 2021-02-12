package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entity extends JPanel implements ActionListener {

    private JTextArea name = new JTextArea();
    private JTextArea dob = new JTextArea();
    private JTextArea description = new JTextArea();
    private JButton call = new JButton();
    private JButton sendLink = new JButton();
    private JButton sendNurse = new JButton();
    private JButton neglect = new JButton();
    private static ImageIcon phone = new ImageIcon("D:\\Client\\src\\phone.jpg");
    private static ImageIcon chain = new ImageIcon("D:\\Client\\src\\chain.jpg");
    private static ImageIcon nurse = new ImageIcon("D:\\Client\\src\\nurse.jpg");
    private static ImageIcon cross = new ImageIcon("D:\\Client\\src\\cross.jpg");

    public String getId() {
        return id;
    }

    private String id;
    public int yValue;

    private Entity(){}
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
        this.dob.setBounds(10,65,300,45);
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
        //Remove this entity from the queue
        //if any option has been chosen
        if(e.getSource() == this.call){
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.sendLink){
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.sendNurse){
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }else if(e.getSource() == this.neglect){
            Login.mainPage.remove(this);
            Login.mainPage.update(this.id);
        }
    }

    public void changeLocation(int value){
        yValue = value;
        this.setBounds(10,yValue,960,120);
    }

    public void setDescription(String description){
        this.description.setText(description);
    }



}
