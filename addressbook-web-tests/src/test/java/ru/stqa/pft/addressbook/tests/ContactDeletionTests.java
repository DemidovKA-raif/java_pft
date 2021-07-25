package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactDeletionTests extends TestBase{

    @Test
    public void pageContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getGroupHelper().selectGroupe();
        app.getContactHelper().clickDeletionContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
