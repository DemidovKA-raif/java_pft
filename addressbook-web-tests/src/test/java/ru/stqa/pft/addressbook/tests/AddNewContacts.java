package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class AddNewContacts extends StepTesting {

    @Test
    public void pageAddNewContact() {
        app.getNewContactClass().addNewContact();
        app.getNewContactClass().setNameContact(new NameNewContact("Qqwerty", "Voiue", "Fret"));
        app.getNewContactClass().setNickName("Krevedko");
        app.getNewContactClass().setHome("New Bitch");
        app.getNavigationHelper().gotoHomePage();
    }
}
