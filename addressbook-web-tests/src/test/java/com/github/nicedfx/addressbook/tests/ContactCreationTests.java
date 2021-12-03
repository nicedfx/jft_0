package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.getNavigationHelper().goToAddNewContactPage();
        app.getContactsHelper().fillContactCreationForm(new ContactData("ThisIsFirstName", "ThisIsMiddleName", "ThisIsLastName",
                "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone", "thisIs@email.com"));
        app.getContactsHelper().createContact();
        app.getNavigationHelper().goToHomePage();
        app.getSessionHelper().logout();
    }

}