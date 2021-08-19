package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionGroups() {
//        if

    }


    @Test
    public void deleteContactInGroupTest() {
        Contacts beforeContact = app.db().contactsRequestDB();
        ContactData contact = beforeContact.iterator().next();
        Groups beforeGroups = app.db().contactAllGroups();
        String name = contact.getGroups().iterator().next().getName();
        app.contact().ContactDeleteGroup(contact.getId(), name);
        GroupData groupForContact = contact.getGroups()
                .stream().filter(g -> g.getName().equals(name)).findFirst().get();

        Contacts afterContact = app.db().contactsRequestDB();
        assertThat(afterContact.size(), equalTo(beforeContact.size()));

        Groups afterInGrous = app.db().contactAllGroups();
        assertThat((afterInGrous), equalTo(new Groups(beforeGroups.withOut(groupForContact))));
    }
    }

