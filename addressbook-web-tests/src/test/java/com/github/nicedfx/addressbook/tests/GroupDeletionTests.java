package com.github.nicedfx.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletionTests() throws Exception {
    app.gotoGroupsPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.gotoGroupsPage();
  }

}
