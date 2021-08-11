package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        File photo = new File("src/test/resources/stru.png");
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv.")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{ new ContactData()
                    .withFirstName(split[0])
                    .withLastName(split[1])
                    .withMiddleName(split[2])
                    .withNickName(split[3])
                    .withGroup(split[4])
                    .withPhoto(photo)
            });
            line = reader.readLine();
        }
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

