package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.model.ContactData;
import com.github.nicedfx.addressbook.model.GroupData;
import com.github.nicedfx.addressbook.model.Groups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class ContactGroupsTests extends TestBase {

    private ContactData contactToBeAdded;
    private ContactData contactToBeRemoved;
    private GroupData group;
    private GroupData groupToRemove;

    @BeforeClass
    public void beforeClass() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            createContact();
        }
        contactToBeAdded = app.db().contacts().iterator().next();

        group = getAvailableGroup(contactToBeAdded);

        if (app.db().contacts().size() <= 1) {
            createContact();
        }

        contactToBeRemoved = app.db().contacts().without(contactToBeAdded).iterator().next();

        if (contactToBeRemoved.getGroups().size() == 0) {
            app.goTo().homePage();
            app.contact().selectContact(contactToBeRemoved.getId());
            app.contact().addToGroup(group);
            app.goTo().homePage();
            contactToBeRemoved = app.db().contacts().stream()
                    .filter(c -> c.getId() == contactToBeRemoved.getId())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Failed to read contactToRemove from the DB"));
        }
        groupToRemove = contactToBeRemoved.getGroups().iterator().next();

    }


    @Test
    public void addContactToGroup() {

        app.contact().selectContact(contactToBeAdded.getId());
        app.contact().addToGroup(group);

        app.goTo().homePage();
        app.contact().selectGroup(group); //not necessary

        ContactData contactAfterAssigning = app.db().contacts().stream()
                .filter(c -> contactToBeAdded.getId() == c.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact under test was not found in the DB"));

        assertThat(contactToBeAdded.getGroups(), not(hasItem(group)));

        assertThat(contactAfterAssigning.getGroups(), hasItem(group));
    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().homePage();
        app.contact().selectGroup(groupToRemove);

        app.contact().selectContact(contactToBeRemoved.getId());
        app.contact().clickRemoveFromGroupButton();

        ContactData removedContact = app.db().contacts().stream()
                .filter(c -> c.getId() == contactToBeRemoved.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contact under test was not found in the DB"));

        assertThat(contactToBeRemoved.getGroups(), hasItem(groupToRemove));

        assertThat(removedContact.getGroups(), not(hasItem(groupToRemove)));
    }

    private GroupData getAvailableGroup(ContactData contact) {
        Groups groups = app.db().groups();

        if (groups.size() == 0) {
            return createGroup();
        }

        Groups occupiedGroups = contact.getGroups();
        groups.removeAll(occupiedGroups);

        if (groups.size() > 0) {
            return groups.iterator().next();
        } else {
            return createGroup();
        }
    }

    private GroupData createGroup() {
        app.goTo().groupPage();
        String name = "test " + new Random().nextInt(99);
        app.group().create(new GroupData()
                .withName(name)
                .withHeader("test2")
                .withFooter("test3"));
        app.goTo().homePage();
        List<GroupData> groupsLIst = app.db().groups().stream()
                .sorted(Comparator.comparingInt(GroupData::getId))
                .collect(Collectors.toList());
        return groupsLIst.get(groupsLIst.size()-1);
    }

    private void createContact() {
        app.goTo().addNewContactPage();
        String name = "ThisIsFirstName " + new Random().nextInt(99);
        app.contact().create(new ContactData()
                .withFirstName(name)
                .withMiddleName("ThisIsMiddleName")
                .withLastName("ThisIsLastName")
                .withAddress("ThisIsAddress")
                .withHomePhone("ThisIsHomePhone")
                .withMobilePhone("ThisIsMobilePhone")
                .withEmail("thisIs@email.com"));
    }
}
