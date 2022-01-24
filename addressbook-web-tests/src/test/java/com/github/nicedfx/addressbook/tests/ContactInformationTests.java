package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

    public static String cleanedPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String normalizeSpaces(String address) {
        String[] addressLines = address.split("\n");

        for (int i = 0; i < addressLines.length; i++) {
            addressLines[i] = addressLines[i].replaceAll(" {2,}", " ").trim();
        }

        return Arrays.stream(addressLines).collect(Collectors.joining("\n"));
    }

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
                    .withSecondPhone("8888     8888")
                    .withEmail("thisIs@email.co   m")
                    .withEmail2("thisIs@email.com    ")
                    .withEmail3("   thisIs@email.com"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhonesEmailsAddresses() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(normalizeSpaces(contactInfoFromEditForm.getAddress())));
        assertThat(contact.getEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondPhone())
                .filter(v -> !v.equals(""))
                .map(ContactInformationTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter(v -> !v.equals(""))
                .map(ContactInformationTests::normalizeSpaces)
                .collect(Collectors.joining("\n"));
    }
}
