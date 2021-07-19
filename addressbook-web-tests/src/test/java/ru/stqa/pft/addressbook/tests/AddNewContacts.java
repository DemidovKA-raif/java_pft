package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class AddNewContacts extends StepTesting {

    @Test
    public void pageAddNewContact() {
        app.getNewContactClass().addNewContact();
        app.getNewContactClass().setNameContact(new NameNewContact("Qqwerty", "Voiue", "Fret","Krevedko","New Bitch"));
        app.getNavigationHelper().gotoHomePage();
    }
}
