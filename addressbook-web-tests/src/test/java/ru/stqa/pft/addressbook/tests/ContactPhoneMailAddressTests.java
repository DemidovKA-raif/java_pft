package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneMailAddressTests extends TestBase{


    @Test
    public void testContactPhones(){
        app.contact().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo((contactInfoFromEditForm).getAddress()));
        assertThat(contact.getAllMail(),  equalTo(mergeMail(contactInfoFromEditForm)));

    }


    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneMailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeMail(ContactData contact) {
        return Arrays.asList(contact.getMailFirst(), contact.getMailSecond(), contact.getMailThree())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneMailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String clean) {
        return clean.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
