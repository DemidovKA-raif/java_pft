package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void pageContactDeletion() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        List<NameNewContact> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().clickDeletionContact();
        Thread.sleep(500);
        app.getNavigationHelper().gotoHomePage();
        List<NameNewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
