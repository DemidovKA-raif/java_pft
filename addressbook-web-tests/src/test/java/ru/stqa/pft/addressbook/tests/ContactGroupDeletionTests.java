package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class ContactGroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {

        /**
         * Проверяем, что есть хотя бы одну группа
         */
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
            app.contact().gotoHomePage();
        }

        /**
         * Проверяем, что есть хотя бы один контакт
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
         * Добавляем контакт в группу
         */
        Contacts before = app.db().contactsRequestDB();
        ContactData contactForGroup = before.iterator().next();
        if (contactForGroup.getGroups().size() == 0) {
            Groups group = app.db().groupsRequestDB()  ;
            GroupData groupForContact = group.iterator().next();
            app.contact().selectContactByIdByName(contactForGroup.getId(), groupForContact.getName());
        }
    }


    /**
     * Получаем список контактов в группах
     * Выбираем случайную группу из списка, где есть контакты
     * Получаем список контактов "ДО"
     * Получаем список групп "ДО"
     * Выбираем случайный контак из рандоной группы
     * Выбираем случайную группу
     * Переходим на главную страницу в UI
     * Выбираем подготовленную заранее группу и контакт, удаляем контакт из группы
     * Сравниваем все полученные заранее списки
     */

    @Test
    public void deleteContactInGroupTest() {
        Groups beforeInGroups = app.db().contactAllGroups();
        GroupData contactInGroups = beforeInGroups.iterator().next();
        Contacts before = app.db().contactsRequestDB();
        Groups group = app.db().groupsRequestDB();
        ContactData addGroupContact = before.iterator().next();
        GroupData groupForContact = group.iterator().next();
        app.contact().gotoHomePage();
        app.contact().contactDeleteGroupTest(addGroupContact.getId(), contactInGroups.getName());
        Contacts after = app.db().contactsRequestDB();
        assertThat(after.size(), CoreMatchers.equalTo(before.size()));
        Groups afterInGroups = app.db().contactAllGroups();
        assertThat((afterInGroups), CoreMatchers.equalTo(beforeInGroups.withOut(groupForContact)));
    }
}



