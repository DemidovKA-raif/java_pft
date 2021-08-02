package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditionContscts() {
        if (app.group().list().size() ==0 ) {
            app.getContactHelper().create(
                    new ContactData("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getContactHelper().gotoHomePage();
        }
    }

    @Test
    public void pageContactModification() {

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Mackaley", "Calkin", "One", "Small", "New Y", "test1");
        app.getContactHelper().modify(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
