package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletionTests() throws Exception {
        app.getNavigationHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }

        int before = app.getGroupHelper().getGroupsAmount();

        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupsPage();

        int after = app.getGroupHelper().getGroupsAmount();

        Assert.assertEquals(after, before - 1);
    }

}
