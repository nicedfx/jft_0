package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.goTo().goToHomePage();
        if (!app.getContactsHelper().isThereAContact()) {
            app.goTo().goToAddNewContactPage();
            app.getContactsHelper().createContact(new ContactData()
                    .withFirstName("ThisIsFirstName")
                    .withMiddleName("ThisIsMiddleName")
                    .withLastName("ThisIsLastName")
                    .withAddress("ThisIsAddress")
                    .withHomePhone("ThisIsHomePhone")
                    .withMobile("ThisIsMobilePhone")
                    .withEmail("thisIs@email.com")
                    .withGroup("test1"));
            app.goTo().goToHomePage();
        }

        List<ContactData> before = app.getContactsHelper().getContactsList();


        String editedName = "Edited Name " + (short) System.currentTimeMillis();
        String editedLastName = "Edited LastName " + (short) System.currentTimeMillis();

        ContactData modifiedContact = new ContactData()
                .withFirstName(editedName)
                .withMiddleName("Edited MiddleName")
                .withLastName(editedLastName)
                .withAddress("Edited address")
                .withHomePhone("Edited HomePhone")
                .withMobile("Edited mobilePhone")
                .withEmail("Edited email")
                .withGroup(null);

        app.goTo().initContactModification(before.size() - 1);
        app.getContactsHelper().fillContactCreationForm(modifiedContact, false);
        app.getContactsHelper().submitContactEditForm();
        app.goTo().goToHomePage();

        before.remove(before.size() -1);
        before.add(modifiedContact);

        List<ContactData> after = app.getContactsHelper().getContactsList();

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }
}
