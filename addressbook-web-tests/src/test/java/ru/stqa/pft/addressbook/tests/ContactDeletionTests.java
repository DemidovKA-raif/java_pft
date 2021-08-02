package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionContscts() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactDeletion() throws InterruptedException {

        List<NameNewContact> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(index);
        List<NameNewContact> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
