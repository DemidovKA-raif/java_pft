package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;

    private final String browser;
    private RegistrationHelper registrationHelper;
    private FTPHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;
    private UsersHelper users;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        dbHelper = new DbHelper();
        if (browser.equals(BrowserType.CHROME)) {
           wd = new ChromeDriver();
        }
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public UsersHelper users() {
        if (users == null) {
            users = new UsersHelper(this);
        }
        return users;
    }

    public DbHelper db(){
        return dbHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            }
        }
        return wd;
    }

    public FTPHelper ftp() {
        if (ftp == null) {
            ftp = new FTPHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james (){
        if(jamesHelper == null){
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
}
