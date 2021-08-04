package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
//        for (int i = 0; i < 5; i++) {

        app.getContactHelper().gotoHomePage();
        Contacts before = app.getContactHelper().all();
        ContactData contact = new ContactData()
                .withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald").withMyHome("New Bitch").withGroup("test1");
        app.getContactHelper().create(contact, true);
        app.getContactHelper().gotoHomePage();
        Contacts after = app.getContactHelper().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded( contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }
}
//}
