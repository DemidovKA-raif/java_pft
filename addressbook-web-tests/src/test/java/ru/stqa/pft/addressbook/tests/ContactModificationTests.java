package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void pageContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<NameNewContact> before = app.getContactHelper().getContactList();
        app.getContactHelper().modificationContact(before.size()-1);
        NameNewContact contact = new NameNewContact(before.get(before.size() -1).getId(),"Mackaley", "Calkin", "One", "Small", "New Y", "test1");
        app.getContactHelper().setNameContact(contact,false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
        List<NameNewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
                Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
