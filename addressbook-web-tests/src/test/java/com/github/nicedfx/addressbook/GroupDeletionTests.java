package com.github.nicedfx.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletionTests() throws Exception {
    gotoGroupsPage();
    selectGroup();
    deleteSelectedGroups();
    gotoGroupsPage();
  }

}
