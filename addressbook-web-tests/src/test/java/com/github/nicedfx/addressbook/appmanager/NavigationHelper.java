package com.github.nicedfx.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoGroupsPage() {
        if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

    public void goToAddNewContactPage() {
        click(By.linkText("add new"));
    }


    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void acceptAlertPopup() {
        wd.switchTo().alert().accept();
    }
}
