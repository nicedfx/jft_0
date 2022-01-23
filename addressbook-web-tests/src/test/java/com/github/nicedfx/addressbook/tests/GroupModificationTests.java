package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensureConditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("Test_edit1")
                .withHeader("test edit header")
                .withFooter("test edit footer");

        app.goTo().groupPage();

        app.group().modify(group);
        app.goTo().groupPage();

        assertEquals(app.group().count(), before.size());

        Groups after = app.db().groups();

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

        verifyGroupListInUi();
    }


}
