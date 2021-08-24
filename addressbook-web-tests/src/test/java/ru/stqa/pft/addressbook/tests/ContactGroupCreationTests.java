package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }


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
        Groups afterInGroups = app.db().contactAllGroups();
        assertThat((afterInGroups), equalTo(beforeInGroups));
    }
}
