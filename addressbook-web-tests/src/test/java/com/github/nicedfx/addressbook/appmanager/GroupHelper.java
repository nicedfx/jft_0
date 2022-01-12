package com.github.nicedfx.addressbook.appmanager;

import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {

    private Groups groupCash = null;

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

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupsPage();
        groupCash = null;
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCash = null;
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCash = null;
    }

    public int getGroupsAmount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Groups all() {
        if (groupCash != null) {
            return new Groups(groupCash);
        }

        groupCash = new Groups();

        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCash.add(new GroupData()
                    .withId(id)
                    .withName(name));
        }
        return new Groups(groupCash);
    }
}
