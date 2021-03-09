package GUI.Widgets;

import GUI.Pages.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a link in the configuration page
 */
public class Link extends JPanel implements ActionListener {
    private final JButton remove = new JButton();
    private final JTextField nameField = new JTextField("");
    private final JTextField linkField = new JTextField("");
    public int yValue;

    /**
     * create a new link object
     * @param y vertical value y of the link
     */
    public Link(int y){
        this.yValue = y;
        
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name:");
        nameLabel.setBounds(10,3,100,30);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,30));
        
        JLabel linkLabel = new JLabel();
        linkLabel.setText("Link:");
        linkLabel.setBounds(10,38,100,30);
        linkLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,30));

        nameField.setBounds(120,3,550,30);
        nameField.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,22));

        linkField.setBounds(120,38,550,30);
        linkField.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,22));

        remove.setBounds(690,10,60,50);
        remove.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        remove.setBackground(Color.gray);
        remove.setText("remove");
        remove.setForeground(Color.white);
        remove.addActionListener(this);
        
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(10,yValue,760,73);
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(remove);
        this.add(nameLabel);
        this.add(linkLabel);
        this.add(nameField);
        this.add(linkField);
    }

    /**
     * listens to all the events
     * @param e events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == remove){
            Login.mainPage.configurationPage.remove(this);
            Login.mainPage.configurationPage.update(this.yValue);
        }
    }

    /**
     * change the vertical location of the links
     * @param value the vertical value
     */
    public void changeLocation(int value){
        yValue = value;
        this.setBounds(10,yValue,760,73);
    }

    /**
     * get the name of the link
     * @return the name of the link
     */
    public String getName(){
        return this.nameField.getText();
    }

    /**
     * get the address of the link
     * @return the address of the link
     */
    public String getLink(){
        return this.linkField.getText();
    }
}
