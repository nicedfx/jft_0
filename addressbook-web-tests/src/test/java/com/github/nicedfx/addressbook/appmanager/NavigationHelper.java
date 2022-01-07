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

    public void groupPage() {
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

    public void initContactModification(int index) {
       wd.findElements(By.name("entry")).get(index)
               .findElement(By.cssSelector("[alt = Edit]")).click();
    }

    public void acceptAlertPopup() {
        wd.switchTo().alert().accept();
    }
}
