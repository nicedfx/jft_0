package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensureConditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1", "test2", "test3"));
        }
    }

    @Test
    public void testGroupDeletionTests() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        app.group().delete(index);
        app.goTo().groupPage();

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), index);

        before.remove(index);

        Assert.assertEquals(after, before);
    }



}
