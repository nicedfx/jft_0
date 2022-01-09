package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        Set<GroupData> after = app.group().all();

        assertThat(after.size(), equalTo(before.size() + 1));

        group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
        before.add(group);

//        assertThat(after, equalTo(before.withAdded(group)));
    }

}
