package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{


    @Test
    public void testContactPhones(){
        app.getContactHelper().gotoHomePage();
        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
        assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
        assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
    }
}
