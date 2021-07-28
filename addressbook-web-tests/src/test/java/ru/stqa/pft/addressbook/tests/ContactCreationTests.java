package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
        app.getNavigationHelper().gotoHomePage();
        List<NameNewContact> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
        app.getNavigationHelper().gotoHomePage();
        List<NameNewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

    }
}
