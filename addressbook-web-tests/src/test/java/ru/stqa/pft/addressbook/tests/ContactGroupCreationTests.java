package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupCreationTests extends TestBase {


    @Test
    public void addContactInGroupTest() {
        /**
         * Получаем список контактов "ДО", получаем список групп "ДО"
         * Выбрали рандомный контакт
         * Выполнили шаги по добавлению контакта в группу в UI
         * Сравнили список контактов "ДО" и "После"
         * Сравнили список групп "ДО" и "После"
         */

        Contacts before = app.db().contactsRequestDB();
        Groups beforeInGroups = app.db().contactAllGroups();
        ContactData addGroupContact = before.iterator().next();
        app.contact().addGroupInContactById(addGroupContact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contactsRequestDB();
        assertThat(after, equalTo(before));
        Groups afterInGrous = app.db().contactAllGroups();
        assertThat((afterInGrous), equalTo(beforeInGroups));
    }
}
