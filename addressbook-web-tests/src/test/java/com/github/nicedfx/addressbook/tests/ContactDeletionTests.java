package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.goTo().goToHomePage();
        if (!app.getContactsHelper().isThereAContact()) {
            app.goTo().goToAddNewContactPage();
            app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                    "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                    "thisIs@email.com", "test1"));
            app.goTo().goToHomePage();
        }

        List<ContactData> before = app.getContactsHelper().getContactsList();

        app.getContactsHelper().selectContact(before.size() - 1);
        app.getContactsHelper().clickDeleteContactButton();
        app.goTo().acceptAlertPopup();
        app.goTo().goToHomePage();

        List<ContactData> after = app.getContactsHelper().getContactsList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        Assert.assertEquals(after, before);

    }
}
