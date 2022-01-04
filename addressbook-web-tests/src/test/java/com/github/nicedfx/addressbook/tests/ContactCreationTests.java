package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.getNavigationHelper().goToHomePage();

        int before = app.getContactsHelper().getContactsAmount();

        app.getNavigationHelper().goToAddNewContactPage();

        app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                "thisIs@email.com", "Test_edit1"));
        app.getNavigationHelper().goToHomePage();

        int after = app.getContactsHelper().getContactsAmount();

        app.getSessionHelper().logout();
        Assert.assertEquals(after, before + 1);
    }

}
