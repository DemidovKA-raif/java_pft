package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
        app.contact().gotoHomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withMiddleName("MiddleName")
                .withNickName("NickName")
                .withMyHome("New Bitch")
                .withGroup("test1")
                .withPhoto(photo);
        app.contact().create(contact, true);
        app.contact().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }


    @Test
    public void pageAddNewBadContact() {
        app.contact().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("FirstName'").withLastName("LastName").withMiddleName("MiddleName").withNickName("Donald").withMyHome("New Bitch").withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
        app.contact().create(contact, true);
        app.contact().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}

