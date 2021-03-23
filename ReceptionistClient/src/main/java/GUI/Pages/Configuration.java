package GUI.Pages;

import GUI.Dialogues.ExceedLength;
import GUI.Dialogues.InvalidFormat;
import GUI.Dialogues.NoLink;
import GUI.Widgets.Link;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The page where the user sets the links and their names
 */
public class Configuration extends JFrame implements ActionListener {
    //values to be used in combo box
    public String[] names= null;
    public String[] linkValues = null;
    //ui properties
    private final int STEP = 80;
    private final JButton save;
    private final JButton add;
    private final JButton importCSV;
    //a list of links
    private final LinkedList<Link> links = new LinkedList<>();
    //a CSV file
    private File csv;


    public Configuration() {
        JLabel hint = new JLabel();
        hint.setText("Room Link Configurations");
        hint.setFont(new Font(Font.SANS_SERIF, Font.PLAIN ,30));
        hint.setForeground(new Color(0xFFFFFF));
        hint.setBounds(20,8,400,60);

        JSeparator separator = new JSeparator();
        separator.setBounds(10,70,755,5);
        separator.setForeground(Color.WHITE);

        save = new JButton();
        save.setText("save and close");
        save.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        save.setFocusable(false);
        save.setForeground(new Color(0xFFFFFF));
        save.setHorizontalAlignment(SwingConstants.CENTER);
        save.setVerticalAlignment(SwingConstants.CENTER);
        save.setBounds(398,880,370,55);
        save.setBackground(Color.lightGray);
        save.addActionListener(this);

        add = new JButton();
        add.setText("create new link");
        add.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        add.setFocusable(false);
        add.setForeground(new Color(0xFFFFFF));
        add.setHorizontalAlignment(SwingConstants.CENTER);
        add.setVerticalAlignment(SwingConstants.CENTER);
        add.setBounds(10,880,372,55);
        add.setBackground(Color.lightGray);
        add.addActionListener(this);

        importCSV = new JButton();
        importCSV.setText("Import CSV");
        importCSV.setFont(new Font(Font.SANS_SERIF, Font.BOLD ,35));
        importCSV.setFocusable(false);
        importCSV.setForeground(new Color(0xFFFFFF));
        importCSV.setHorizontalAlignment(SwingConstants.CENTER);
        importCSV.setVerticalAlignment(SwingConstants.CENTER);
        importCSV.setBounds(430,10,300,50);
        importCSV.setBackground(Color.BLACK);
        importCSV.addActionListener(this);


        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(100, 20, 780, 950);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.add(save);
        this.add(add);
        this.add(importCSV);
        this.add(hint);
        this.add(separator);
    }

    /**
     * listens to all the events happening in this page
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save) {
            if(links.size() == 0){
                NoLink.invokeNoLink();
                return;
            }
            MainPage.timer.start();
            setComboBoxValues(links);
            Login.mainPage.updateComboBoxes();
            this.setVisible(false);
        }else if(e.getSource() == add){
            if(links.size() == 10){
                ExceedLength.invokeExceedLength();
                return;
            }
            addLink("","");
        }else if(e.getSource() == importCSV){
            JFileChooser fileChooser = new JFileChooser();
            int choice = fileChooser.showOpenDialog(null);
            if(choice != JFileChooser.APPROVE_OPTION){
                return;
            }
            this.csv = fileChooser.getSelectedFile();
            try{
                if(!csv.getName().endsWith(".csv")){
                    InvalidFormat.invokeInvalidFormat();
                    return;
                }
                Scanner scanner = new Scanner(csv);
                scanner.useDelimiter(System.lineSeparator()+"|,");
                while(scanner.hasNext()){
                    if(links.size() == 10){
                        ExceedLength.invokeExceedLength();
                        return;
                    }
                    addLink(scanner.next(),scanner.next());
                }
                scanner.close();
            }catch (Exception exception){
                InvalidFormat.invokeInvalidFormat();
                exception.printStackTrace();
            }
        }
    }

    /**
     * add a link
     */
    private void addLink(String name, String address){
        Link link = new Link((links.size() + 1)*STEP,name,address);
        links.add(link);
        this.add(link);
        this.repaint();
    }

    /**
     * set a jcombobox to be used by the callers
     * @param links a linkedlist of link values
     */
    private void setComboBoxValues(LinkedList<Link> links){
        String[] names = new String[links.size()];
        String[] linkValues = new String[links.size()];
        for(int i = 0; i < links.size(); i++){
            names[i] = links.get(i).getName();
            linkValues[i] = links.get(i).getLink();
        }
        this.names = names;
        this.linkValues = linkValues;
    }

    /**
     * update the location of all other links if user removes one
     * @param y the vertical y value of the link to be removed
     */
    public void update(int y){
        links.removeIf(link -> link.yValue == y);
        for(int i = 0; i < links.size(); i++){
            int expectedValue = (i + 1)*STEP;
            if(links.get(i).yValue != expectedValue){
                links.get(i).changeLocation(expectedValue);
            }
        }
        this.repaint();
    }

}
