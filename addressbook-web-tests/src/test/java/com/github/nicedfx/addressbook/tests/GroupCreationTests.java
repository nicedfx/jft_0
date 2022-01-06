package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupsPage();

        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test1", "test2", "test3");
        app.getGroupHelper().createGroup(group);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        app.getSessionHelper().logout();

        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId() +1);
        before.add(group);

        Comparator<GroupData> byId = Comparator.comparingInt(GroupData::getId);

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));

    }

}
