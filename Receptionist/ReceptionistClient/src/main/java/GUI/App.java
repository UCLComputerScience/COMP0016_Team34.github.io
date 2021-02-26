package GUI;


import GUI.Pages.Login;

import java.io.File;

public class App {
    public static String currentDirectory = System.getProperty("user.dir") + File.separator + "classes" + File.separator;
    public static void main(String[] args){
        new Login();
    }
}
