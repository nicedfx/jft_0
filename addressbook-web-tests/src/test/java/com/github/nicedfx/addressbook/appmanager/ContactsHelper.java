package com.github.nicedfx.addressbook.appmanager;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void finishContactCreation() {
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
            if (isGroupPresentInTheList(contactData.getGroup())) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactEditForm() {
        click(By.name("update"));
    }

    public void selectContact(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void clickDeleteContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contactData) {
        fillContactCreationForm(contactData, true);
        finishContactCreation();
    }

    public boolean isGroupPresentInTheList(String groupName) {
        boolean result = false;
        List<WebElement> elements = wd.findElement(By.name("new_group")).findElements(By.tagName("option"));
        for (WebElement elt : elements) {
            if (elt.getText().equals(groupName)) {
                result = true;
            }
        }
        return result;
    }

    public int getContactsAmount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void initContactModification(int id) {
        wd.findElement(By.xpath("//*[@id=\"" + id + "\"]/../.."))
                .findElement(By.cssSelector("img[alt=Edit]"))
                .click();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElements(By.tagName("td")).get(0)
                    .findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName));
        }
        return contacts;
    }
}
