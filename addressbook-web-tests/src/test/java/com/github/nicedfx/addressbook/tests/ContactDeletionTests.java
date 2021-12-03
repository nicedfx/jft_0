package com.github.nicedfx.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().goToHomePage();
        app.getContactsHelper().selectContact();
        app.getContactsHelper().clickDeleteContactButton();
        app.getNavigationHelper().acceptAlertPopup();
        app.getNavigationHelper().goToHomePage();
    }
}
