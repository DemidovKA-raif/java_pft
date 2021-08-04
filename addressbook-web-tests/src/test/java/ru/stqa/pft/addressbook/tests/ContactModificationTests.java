package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditionContscts() {
        if (app.getContactHelper().all().size() ==0 ) {
            app.getContactHelper().create(
                    new ContactData().withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald").withMyHome("New Bitch").withGroup("test1"), true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactModification() {
        Contacts before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Mackaley").withMiddleName("Calkin").withLastName("One").withNickName("Small").withMyHome("New Y").withGroup("test1");
        app.getContactHelper().modify(contact);
        Contacts after = app.getContactHelper().all();
        assertEquals(after.size(), before.size());
        assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}