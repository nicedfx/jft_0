package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactsHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactsHelper().createContact(new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                    "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                    "thisIs@email.com", "test1"));
            app.getNavigationHelper().goToHomePage();
        }

        List<ContactData> before = app.getContactsHelper().getContactsList();

        app.getNavigationHelper().initContactModification(before.size() - 1);

        String editedName = "Edited Name " + (short) System.currentTimeMillis();
        String editedLastName = "Edited LastName " + (short) System.currentTimeMillis();

        ContactData modifiedContact = new ContactData(editedName, "Edited MiddleName",
                editedLastName, "Edited address", "Edited HomePhone", "Edited mobilePhone",
                "Edited email", null);

        app.getContactsHelper().fillContactCreationForm(modifiedContact, false);
        app.getContactsHelper().submitContactEditForm();
        app.getNavigationHelper().goToHomePage();

        before.remove(before.size() -1);
        before.add(modifiedContact);

        List<ContactData> after = app.getContactsHelper().getContactsList();

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
