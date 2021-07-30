package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
//        for (int i = 0; i < 5; i++) {

            app.getNavigationHelper().gotoHomePage();
            List<NameNewContact> before = app.getContactHelper().getContactList();
            NameNewContact contact = new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1");
            app.getContactHelper().createContact(contact, true);
            app.getNavigationHelper().gotoHomePage();
            List<NameNewContact> after = app.getContactHelper().getContactList();
            Assert.assertEquals(after.size(), before.size() + 1);

            contact.setId(after.stream().max(((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))).get().getId());
            before.add(contact);
            Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        }
    }
//}
