package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactModification extends  StepTesting{

    @Test
    public void pageContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactClass().modificationContact();
        app.getContactClass().setNameContact(
                new NameNewContact("Tramp", "Boris", "Gregor", "Donald", "New Bitch", "test1"), false);
        app.getContactClass().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
