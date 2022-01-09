package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() throws Exception {
        app.goTo().goToHomePage();

        List<ContactData> before = app.getContactsHelper().getContactsList();
        app.goTo().goToAddNewContactPage();

        ContactData contactToCreate = new ContactData()
                .withFirstName("ThisIsFirstName")
                .withMiddleName("ThisIsMiddleName")
                .withLastName("ThisIsLastName")
                .withAddress("ThisIsAddress")
                .withHomePhone("ThisIsHomePhone")
                .withMobile("ThisIsMobilePhone")
                .withEmail("thisIs@email.com")
                .withGroup("Test_edit1");

        before.add(contactToCreate);

        app.getContactsHelper().createContact(contactToCreate);
        app.goTo().goToHomePage();

        List<ContactData> after = app.getContactsHelper().getContactsList();

        Assert.assertEquals(after.size(), before.size());

        Comparator<? super ContactData> byName = Comparator.comparing(ContactData::getLastName);
        before.sort(byName);
        after.sort(byName);

        Assert.assertEquals(after, before);
    }

}
