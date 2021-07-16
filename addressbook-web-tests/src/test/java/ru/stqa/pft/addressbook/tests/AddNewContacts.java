package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class AddNewContacts extends StepTesting {

    @Test
    public void pageAddNewContact() {
        app.addNewContact();
        app.setNameContact(new NameNewContact("Qqwerty", "Voiue", "Fret"));
        app.setNickName("Krevedko");
        app.setHome("New Bitch");
        app.getNavigationHelper().gotoHomePage();
    }
}
