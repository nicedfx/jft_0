package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.appmanager.ApplicationManager;
import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.Contacts;
import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.EDGE.browserName()));
    Logger logger = LoggerFactory.getLogger(ContactCreationTests.class);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUi")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();

            assertThat(uiGroups,
                    equalTo(dbGroups.stream()
                            .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                            .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListUi() {
        if (Boolean.getBoolean("verifyUi")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();


            //Merge phones and emails to single fields
            dbContacts.stream().map(c -> c.withAllPhones(Stream.of(c.getHomePhone(), c.getMobilePhone(), c.getWorkPhone(), c.getSecondPhone())
                            .filter(p -> !p.equals(""))
                            .collect(Collectors.joining("\n"))))
                    .map(g -> g.withEmails(Stream.of(g.getEmail(), g.getEmail2(), g.getEmail3())
                            .filter(m -> !m.equals(""))
                            .collect(Collectors.joining("\n"))))
                    .collect(Collectors.toSet());

            assertThat(uiContacts,
                    equalTo(dbContacts.stream()
                            .map((g) -> new ContactData()
                                    .withId(g.getId())
                                    .withFirstName((g.getFirstName()))
                                    .withLastName(g.getLastName())
                                    .withAllPhones(g.getAllPhones())
                                    .withAddress(g.getAddress())
                                    .withEmails(g.getEmails()))
                            .collect(Collectors.toSet())));
        }
    }

}
