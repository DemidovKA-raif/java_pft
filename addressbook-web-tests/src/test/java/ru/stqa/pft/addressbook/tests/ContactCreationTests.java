package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactCreationTests extends TestBase {

    @Test
    public void pageAddNewContact() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getGroupHelper().getGroupCount();
        app.getContactHelper().createContact(new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
        app.getNavigationHelper().gotoHomePage();
    }
}
