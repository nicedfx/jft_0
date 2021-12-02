package com.github.nicedfx.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void gotoGroupsPage() {
        click(By.linkText("groups"));
    }

    public void goToAddNewContactPage() {
        click(By.linkText("add new"));
    }
}
