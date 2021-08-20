package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // Проверка наличия хоть одной группы и её добавление  если таковой нет
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }



    @Test
    public void deleteContactInGroupTest() {
        Contacts beforeContact = app.db().contactsRequestDB();
        ContactData contact = beforeContact.iterator().next();
        Groups beforeGroups = app.db().contactAllGroups();
        String name = contact.getGroups().iterator().next().getName();
        app.contact().ContactDeleteGroup(contact.getId(), name);
//        GroupData groupForContact = contact.getGroups()
//                .stream().filter(g -> g.getName().equals(name)).findFirst().get();

//        Contacts afterContact = app.db().contactsRequestDB();
//        assertThat(afterContact.size(), equalTo(beforeContact.size()));
//
//        Groups afterInGroups = app.db().contactAllGroups();
//        assertThat((afterInGroups), equalTo(new Groups(beforeGroups.withOut(groupForContact))));
    }
    }

