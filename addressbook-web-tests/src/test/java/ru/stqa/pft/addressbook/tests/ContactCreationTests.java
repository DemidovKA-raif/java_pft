package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
//        for (int i = 0; i < 5; i++) {

        app.getContactHelper().gotoHomePage();
        Set<ContactData> before = app.getContactHelper().all();
        ContactData contact = new ContactData()
                .withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald").withMyHome("New Bitch").withGroup("test1");
        app.getContactHelper().create(contact, true);
        app.getContactHelper().gotoHomePage();
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
//}
