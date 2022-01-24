package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().addNewContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("ThisIsFirstName")
                    .withMiddleName("ThisIsMiddleName")
                    .withLastName("ThisIsLastName")
                    .withAddress("ThisIsAddress")
                    .withHomePhone("ThisIsHomePhone")
                    .withMobilePhone("ThisIsMobilePhone")
                    .withEmail("thisIs@email.com"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();

        ContactData removedContact = before.iterator().next();

        app.contact().selectContact(removedContact.getId());
        app.contact().clickDeleteContactButton();
        app.goTo().acceptAlertPopup();
        app.goTo().homePage();

        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(removedContact)));

        verifyContactListUi();
    }
}
