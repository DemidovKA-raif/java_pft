package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class AddNewContacts extends StepTesting {

    @Test
    public void pageAddNewContact() {
        addNewContact();
        setNameContact(new NameNewContact("Qqwerty", "Voiue", "Fret"));
        setNickName("Krevedko");
        setHome("New Bitch");
        gotoHomePage();
    }
}
