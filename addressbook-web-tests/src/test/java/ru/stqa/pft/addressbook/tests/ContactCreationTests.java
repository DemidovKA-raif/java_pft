package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");
        list.add(new Object[] {new ContactData()
                .withFirstName("FirstName1")
                .withLastName("LastName1")
                .withMiddleName("MiddleName1")
                .withNickName("NickName1")
                .withMyHome("New Bitch1")
                .withGroup("test1")
                .withPhoto(photo)});
        list.add(new Object[] {new ContactData()
                .withFirstName("FirstName2")
                .withLastName("LastName2")
                .withMiddleName("MiddleName2")
                .withNickName("NickName2")
                .withMyHome("New Bitch2")
                .withGroup("test1")
                .withPhoto(photo)});
        return list.iterator();
    }



    @Test(dataProvider = "validContacts")
    public void pageAddNewContact(ContactData contact) {
        app.contact().gotoHomePage();
        Contacts before = app.contact().all();
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

