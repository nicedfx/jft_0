package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensureConditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("Test_edit1")
                .withHeader("test edit header")
                .withFooter("test edit footer");

        app.group().modify(group);
        app.goTo().groupPage();

        Set<GroupData> after = app.group().all();

        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(after, before);
    }


}
