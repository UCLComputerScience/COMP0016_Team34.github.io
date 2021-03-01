package GUI.Widgets;

import GUI.App;
import GUI.Pages.Login;
import GUI.Pages.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Link extends JPanel implements ActionListener {
    private static final ImageIcon cross = new ImageIcon(App.currentDirectory + "cross.jpg");
    private final JButton remove = new JButton();
    private final JTextField nameField = new JTextField("");
    private final JTextField linkField = new JTextField("");
    public int yValue;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == remove){
            Login.mainPage.configuration.remove(this);
            Login.mainPage.configuration.update(this.yValue);
        }
    }

    public void changeLocation(int value){
        yValue = value;
        this.setBounds(10,yValue,760,73);
    }

    public String getName(){
        return this.nameField.getText();
    }

    public String getLink(){
        return this.linkField.getText();
    }
}
