package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensureConditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        GroupData group = new GroupData()
                .withId(before.get(index).getId())
                .withName("Test_edit1")
                .withHeader("test edit header")
                .withFooter("test edit footer");

        app.group().modify(index, group);
        app.goTo().groupPage();

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }


}
