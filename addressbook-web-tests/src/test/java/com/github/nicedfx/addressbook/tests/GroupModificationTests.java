package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }

        int before = app.getGroupHelper().getGroupsAmount();

        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("Test_edit1", "test edit header", "test edit footer"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupsPage();

        int after = app.getGroupHelper().getGroupsAmount();

        Assert.assertEquals(after, before);
    }
}
