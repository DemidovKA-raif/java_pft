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
         * Необходимо, что бы была хртя бы одна группа, для включения контакта в нее
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

        /**
         * Необходимо, что бы была хотя бы одна запись в таблице address_in_groups для сравнения
         */
        if (app.db().contactAllGroups().size()==0){
            Contacts before = app.db().contactsRequestDB();
            ContactData addGroupContact = before.iterator().next();
            app.contact().addGroupInContactById(addGroupContact);
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
