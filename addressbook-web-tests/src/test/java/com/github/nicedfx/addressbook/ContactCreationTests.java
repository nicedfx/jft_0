package com.github.nicedfx.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        goToAddNewContactPage();
        fillContactCreationForm(new ContactData("ThisIsFirstName", "ThisIsMiddleName", "ThisIsLastName",
                "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone", "thisIs@email.com"));
        createContact();
        goToHomePage();
        logout();
    }

}
