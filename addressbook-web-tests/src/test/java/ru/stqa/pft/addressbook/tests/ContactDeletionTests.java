package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionContacts() {
        if (app.getContactHelper().all().size() == 0 ) {
            app.getContactHelper().create(
                    new ContactData().withFirstName("Tramp").withLastName("Boris").withMiddleName("Gregor").withNickName("Donald").withMyHome("New Bitch").withGroup("test1"),true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactDeletion() throws InterruptedException {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
