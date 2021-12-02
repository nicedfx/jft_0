package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.goToAddNewContactPage();
        app.fillContactCreationForm(new ContactData("ThisIsFirstName", "ThisIsMiddleName", "ThisIsLastName",
                "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone", "thisIs@email.com"));
        app.createContact();
        app.goToHomePage();
        app.logout();
    }

}
