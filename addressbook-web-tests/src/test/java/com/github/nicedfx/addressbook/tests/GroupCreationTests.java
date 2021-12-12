package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupsPage();

        int before = app.getGroupHelper().getGroupsAmount();

        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        int after = app.getGroupHelper().getGroupsAmount();

        app.getSessionHelper().logout();

        Assert.assertEquals(after, before + 1);

    }

}
