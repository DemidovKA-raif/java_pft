package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase{


    @Test
    public void testContactMail (){
        app.contact().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMail(), equalTo(mergeMail(contactInfoFromEditForm)));
    }

    private String mergeMail(ContactData contact) {
        return Arrays.asList(contact.getMailFirst(), contact.getMailSecond(), contact.getMailThree())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactMailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return mail.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
