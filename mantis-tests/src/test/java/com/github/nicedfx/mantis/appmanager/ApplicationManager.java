package com.github.nicedfx.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private final String browser;
    private WebDriver wd;
    private RegistrationHelper registrationHelper;
    private PasswordChangeHelper passwordChangeHelper;
    private MailHelper mailHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
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

    public PasswordChangeHelper userPasswordChange() {
        if (passwordChangeHelper == null) {
            passwordChangeHelper = new PasswordChangeHelper(this);
        }
        return passwordChangeHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equalsIgnoreCase(Browser.FIREFOX.browserName())) {
                wd = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase(Browser.CHROME.browserName())) {
                wd = new ChromeDriver();
            } else if (browser.equalsIgnoreCase(Browser.EDGE.browserName())) {
                wd = new EdgeDriver();
            }
//        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }
 }
