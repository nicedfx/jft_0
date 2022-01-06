package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.getNavigationHelper().goToHomePage();

//        int before = app.getContactsHelper().getContactsAmount();

        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.getNavigationHelper().goToAddNewContactPage();

        app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                "thisIs@email.com", "Test_edit1"));
        app.getNavigationHelper().goToHomePage();

        List<ContactData> after = app.getContactsHelper().getContactsList();

        app.getSessionHelper().logout();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
