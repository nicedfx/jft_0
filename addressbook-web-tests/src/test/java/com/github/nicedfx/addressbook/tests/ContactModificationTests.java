package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactsHelper().selectContact();
        app.getNavigationHelper().initContactModification();
        app.getContactsHelper().fillContactCreationForm(new ContactData("Edited Name", "Edited MiddleName",
                "Edited LastName", "Edited address", "Edited HomePhone", "Edited mobilePhone", "Edited email"));
        app.getContactsHelper().submitContactEditForm();
        app.getNavigationHelper().goToHomePage();
    }
}
