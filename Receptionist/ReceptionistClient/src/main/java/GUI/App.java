package GUI;


import GUI.Pages.Login;
import GUI.Pages.MainPage;

import java.io.File;
import java.net.CookieHandler;

/**
 * main class of the program
 */
public class App {
    public static String currentDirectory = System.getProperty("user.dir") + File.separator + "classes" + File.separator;
    public static void main(String[] args){
        CookieHandler.setDefault(MainPage.cookieManager);
        new Login();
    }
}
