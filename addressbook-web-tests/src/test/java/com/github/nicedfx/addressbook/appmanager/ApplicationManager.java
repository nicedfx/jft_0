package com.github.nicedfx.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

public class ApplicationManager {
    private final Browser browser;
    WebDriver wd;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactsHelper contactsHelper;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(Browser.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.EDGE)) {
            wd = new EdgeDriver();
        }
//        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactsHelper = new ContactsHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactsHelper contact() {
        return contactsHelper;
    }
}
