package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactCreation extends TestBase {

    @Test
    public void pageAddNewContact() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactClass().addNewContact();
        app.getContactClass().setNameContact(
                new NameNewContact("Qqwerty", "Voiue", "Fret", "Krevedko", "New Bitch", "test1"), true);
        app.getContactClass().clickNewContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
