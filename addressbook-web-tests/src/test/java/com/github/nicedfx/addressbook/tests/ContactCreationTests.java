package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.goTo().homePage();

        Contacts before = app.contact().all();
        app.goTo().addNewContactPage();

        ContactData contactToCreate = new ContactData()
                .withFirstName("ThisIsFirstName")
                .withMiddleName("ThisIsMiddleName")
                .withLastName("ThisIsLastName")
                .withAddress("ThisIsAddress")
                .withHomePhone("ThisIsHomePhone")
                .withMobilePhone("ThisIsMobilePhone")
                .withEmail("thisIs@email.com")
                .withGroup("Test_edit1");

        app.contact().create(contactToCreate);
        app.goTo().homePage();

        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(
                before.withAdded(contactToCreate.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()
                ))));
    }

    @Test
    public void testBadContactCreationTests() throws Exception {
        app.goTo().homePage();

        Contacts before = app.contact().all();
        app.goTo().addNewContactPage();

        ContactData contactToCreate = new ContactData()
                .withFirstName("ThisIsFirstName'")
                .withMiddleName("ThisIsMiddleName")
                .withLastName("ThisIsLastName")
                .withAddress("ThisIsAddress")
                .withHomePhone("ThisIsHomePhone")
                .withMobilePhone("ThisIsMobilePhone")
                .withEmail("thisIs@email.com")
                .withGroup("Test_edit1");

        app.contact().create(contactToCreate);
        app.goTo().homePage();

        assertEquals(app.group().count(), before.size());

        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }

}
