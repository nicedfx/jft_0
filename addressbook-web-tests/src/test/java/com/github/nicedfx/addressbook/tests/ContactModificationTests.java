package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.goTo().addNewContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("ThisIsFirstName")
                    .withMiddleName("ThisIsMiddleName")
                    .withLastName("ThisIsLastName")
                    .withAddress("ThisIsAddress")
                    .withHomePhone("ThisIsHomePhone")
                    .withMobilePhone("ThisIsMobilePhone")
                    .withEmail("thisIs@email.com")
                    .withGroup("test1"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();

        ContactData contactToModify = before.iterator().next();

        String editedName = "Edited Name " + (short) System.currentTimeMillis();
        String editedLastName = "Edited LastName " + (short) System.currentTimeMillis();

        ContactData modifiedContact = new ContactData()
                .withId(contactToModify.getId())
                .withFirstName(editedName)
                .withMiddleName("Edited MiddleName")
                .withLastName(editedLastName)
                .withAddress("Edited address")
                .withHomePhone("Edited HomePhone")
                .withMobilePhone("Edited mobilePhone")
                .withEmail("Edited email")
                .withGroup(null);

        app.contact().initContactModification(contactToModify.getId());
        app.contact().fillContactCreationForm(modifiedContact, false);
        app.contact().submitContactEditForm();
        app.goTo().homePage();

        assertEquals(app.group().count(), before.size());

        Contacts after = app.contact().all();

        assertThat(after,
                equalTo(before.without(contactToModify).withAdded(modifiedContact)));
    }
}
