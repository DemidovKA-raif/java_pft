package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditionContscts() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactModification() {

        List<NameNewContact> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        NameNewContact contact = new NameNewContact(before.get(index).getId(), "Mackaley", "Calkin", "One", "Small", "New Y", "test1");
        app.getContactHelper().modifyContact(index, contact);
        List<NameNewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
