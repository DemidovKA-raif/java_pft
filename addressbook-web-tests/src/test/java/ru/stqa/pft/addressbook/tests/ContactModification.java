package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactModification extends TestBase {

    @Test
    public void pageContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().modificationContact();
        app.getContactHelper().setNameContact(
                new NameNewContact("Mackaley", "Calkin", "Gregor", "Donald", "New Bitch", "test1"), false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
