package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionContacts() {
        if (app.getContactHelper().all().size() == 0 ) {
            app.getContactHelper().create(
                    new ContactData().withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald").withMyHome("New Bitch").withGroup("test1"),true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactDeletion() throws InterruptedException {
        Contacts before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        assertThat(app.getContactHelper().count(), equalTo(before.size()-1));
        Contacts after = app.getContactHelper().all();
        assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
    }


}
