package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletionTests() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();

        assertEquals(app.group().count(), before.size() - 1);

        Groups after = app.db().groups();


        MatcherAssert.assertThat(after, equalTo(before.without(deletedGroup)));

        verifyGroupListInUi();
    }


}
