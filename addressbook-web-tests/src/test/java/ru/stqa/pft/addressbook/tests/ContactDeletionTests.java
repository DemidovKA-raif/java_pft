package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionContacts() {
        if (app.db().contacts().size() ==0 ) {
            app.contact().create(
                    new ContactData().withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald")
//                            .withGroup("test1")
                    , true);
            app.contact().gotoHomePage();
        }
    }

    @Test
    public void pageContactDeletion() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
        verifyContactListInUI();
    }


}
