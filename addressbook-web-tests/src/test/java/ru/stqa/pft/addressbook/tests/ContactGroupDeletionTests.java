package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactGroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionGroups() {
        if (app.)

    }


    @Test
    public void deleteContactInGroupTest() {
        Contacts before = app.db().contactsRequestDB();
        ContactData deleteGroupContact = before.iterator().next();
        app.contact().deleteGroupInContactById(deleteGroupContact);
    }
}
