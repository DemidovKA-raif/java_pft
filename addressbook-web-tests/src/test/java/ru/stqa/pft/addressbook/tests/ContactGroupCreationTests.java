package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        /**
         * Необходимо, что бы была хотя бы одна группа, для включения контакта в нее
         */
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test M"));
            app.group().gotoHomePage();
        }

        /**
         * Необходимо, что бы быть хоть бы один контакт , для добавления его в группы
         */
        if (app.db().contactsRequestDB().size() == 0) {
            Groups groups = app.db().groupsRequestDB();
            File photo = new File("src/test/resources/stru.png");
            app.contact().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstName("FirstName").withLastName("LastName").withMiddleName("MiddleName").withNickName("Donald")
                    .inGroup(groups.iterator().next())
                    .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhoto(photo), true);
            app.contact().gotoHomePage();
        }

    }


    /**
     * Получаем список контактов "ДО"
     * Получаем список групп "ДО"
     * Выбрали рандомный контакт
     * Выбрали рандомную группу
     * Берем список контактов в группе
     * Возвращаемся на главную
     * Выполнили шаги по добавлению контакта в группу в UI, получаем ID- контакта, name-группы
     * Возвращаемся на главную
     * Получаем список контактов "После"
     * Сравнили список контактов "ДО" и "После"
     * Получаем список контактов в группах
     * Сравнили список групп "ДО" и "После"
     */
    @Test
    public void addContactInGroupTest() {
        Contacts before = app.db().contactsRequestDB();
        Groups group = app.db().groupsRequestDB();
        ContactData addGroupContact = before.iterator().next();
        GroupData groupForContact = group.iterator().next();
        Groups beforeInGroups = app.db().contactAllGroups();
        app.contact().gotoHomePage();
        app.contact().selectContactByIdByName(addGroupContact.getId(), groupForContact.getName());
        Contacts after = app.db().contactsRequestDB();
        assertThat(after.size(), equalTo(before.size()));
        Groups afterInGroups = app.db().contactAllGroups();
        assertThat((afterInGroups), equalTo(new Groups(beforeInGroups.withAdded(groupForContact))));
    }
}
