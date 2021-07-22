package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactCreation extends TestBase {

    @Test
    public void pageAddNewContact() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
        app.getNavigationHelper().gotoHomePage();
    }
}
