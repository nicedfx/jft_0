package com.github.nicedfx.addressbook.appmanager;

import com.github.nicedfx.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getName());
        type(By.name("group_footer"), groupData.getName());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void returnToGroupsPage() {
        click(By.linkText("groups"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public int getGroupsAmount() {
       return wd.findElements(By.name("selected[]")).size();
    }
}
