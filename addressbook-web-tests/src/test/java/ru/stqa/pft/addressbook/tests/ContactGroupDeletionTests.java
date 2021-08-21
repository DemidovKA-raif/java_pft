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
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
        if (app.db().contactAllGroups().size()==0 ){
            Contacts before = app.db().contactsRequestDB();
            ContactData addGroupContact = before.iterator().next();
            app.contact().addGroupInContactById(addGroupContact);
        }
    }


    @Test
    public void deleteContactInGroupTest() {

        Contacts before = app.db().contactsRequestDB();
        Groups beforeInGroups = app.db().contactAllGroups();

        ContactData contact = before.iterator().next();
        String getNameGroup = contact.getGroups().iterator().next().getName();
        app.contact().contactDeleteGroup(contact, getNameGroup);

//        assertThat(app.contact().count(), equalTo(before.size()));
//        Contacts after = app.db().contactsRequestDB();
//        assertThat(after, equalTo(before));
//        Groups afterInGrous = app.db().contactAllGroups();
//        assertThat((afterInGrous), equalTo(beforeInGroups));
    }

}


