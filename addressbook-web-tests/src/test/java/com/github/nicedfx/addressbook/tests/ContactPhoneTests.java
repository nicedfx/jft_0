package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests  extends TestBase{

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
                    .withHomePhone("+7 (921) 2205077")
                    .withMobilePhone("557-89-21")
                    .withWorkPhone("8 800 111 22  33")
                    .withEmail("thisIs@email.com")
                    .withGroup("test1"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter(v -> ! v.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
