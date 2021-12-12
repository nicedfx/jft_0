package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.getNavigationHelper().goToAddNewContactPage();
        app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                "thisIs@email.com", "Test_edit1"), true);
        app.getNavigationHelper().goToHomePage();
        app.getSessionHelper().logout();
    }

}
