package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{


    @Test
    public void testContactPhones(){
        app.getContactHelper().gotoHomePage();
        ContactData contacts = app.getContactHelper().all().iterator().next();
//        ContactData contactInfoFromEditForm = app.getContactHelper().
    }
}
