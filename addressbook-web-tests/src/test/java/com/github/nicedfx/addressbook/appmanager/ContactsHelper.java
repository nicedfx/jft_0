package com.github.nicedfx.addressbook.appmanager;

import com.github.nicedfx.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactCreationForm(ContactData contactData, boolean isContactCreation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (isContactCreation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactEditForm() {
        click(By.name("update"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void clickDeleteContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

}
