package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactCreationDataProvider() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() {
            }.getType());

            return contacts.stream().map(c -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "contactCreationDataProvider")
    public void testContactCreationTests(ContactData contactToCreate) {
        logger.info("Start test testContactCreationTests");
        app.goTo().homePage();

        Contacts before = app.db().contacts();
        app.goTo().addNewContactPage();
        File photo = new File("src/test/resources/pic1.png");

        app.contact().create(contactToCreate);
        app.goTo().homePage();

        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(
                before.withAdded(contactToCreate.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()
                ))));
        logger.info("Start test testContactCreationTests");
    }

    @Test
    public void testBadContactCreationTests() {
        app.goTo().homePage();

        Contacts before = app.db().contacts();
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

        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before));

        verifyContactListUi();
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/pic1.png");
        System.out.println(photo.getAbsolutePath());

        System.out.println(photo.exists());

    }

}
