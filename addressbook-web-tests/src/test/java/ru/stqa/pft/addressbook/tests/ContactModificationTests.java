package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {



    @BeforeMethod
    public void ensurePreconditionContscts() {
        if (app.db().contactsRequestDB().size() ==0 ) {
            app.contact().create(
                    new ContactData().withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald")
                    , true);
            app.contact().gotoHomePage();
        }
    }



    @Test
    public void testContactModification() {
        File photo = new File("src/test/resources/stru.png");
        Contacts before = app.db().contactsRequestDB();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Mackaley").withMiddleName("Calkin").withLastName("One").withNickName("Small").withPhoto(photo);
        app.contact().gotoHomePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contactsRequestDB();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}