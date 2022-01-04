package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();

        int before = app.getContactsHelper().getContactsAmount();

        if (!app.getContactsHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                    "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                    "thisIs@email.com", "test1"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactsHelper().selectContact();
        app.getContactsHelper().clickDeleteContactButton();
        app.getNavigationHelper().acceptAlertPopup();
        app.getNavigationHelper().goToHomePage();

        int after = app.getContactsHelper().getContactsAmount();

        Assert.assertEquals(after, before - 1);

    }
}
