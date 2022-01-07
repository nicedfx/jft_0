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

        ContactData contactToCreate = new ContactData("ThisIsFirstName", "ThisIsMiddleName",
                "ThisIsLastName", "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone",
                "thisIs@email.com", "Test_edit1");

        before.add(contactToCreate);

        app.getContactsHelper().createContact(contactToCreate);
        app.goTo().goToHomePage();

        List<ContactData> after = app.getContactsHelper().getContactsList();

        Assert.assertEquals(after.size(), before.size());

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after, before);
    }

}
