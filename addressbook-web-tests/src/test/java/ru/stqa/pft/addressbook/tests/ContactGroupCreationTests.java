package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactGroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        /**
         * Необходимо, что бы была хотя бы одна группа, для включения контакта в нее
         */
        app.goTo().groupPage();
        long now = System.currentTimeMillis();
        app.group().create(new GroupData().withName("Autotest" + now).withFooter("Add").withHeader("Contact in group"));
        app.group().gotoHomePage();


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
     * получили список всех контактов
     * получили список всех групп
     * Выбрали рандомный контакт и получаем ID
     * получили список групп, в которые входит контакт
     * преобразовали полученные данные в список 1
     * преобразовали все группы в список 2
     * вывели лишь разницу - группу, в которой контакт не состоит
     * выбираем рандомную свободную от нашего контакта группу
     * идем домой
     * добавляем контакт в свободную группу
     */
    @Test
    public void addContactInGroupTest() throws InterruptedException {
        Contacts allContacts = app.db().contactsRequestDB();//получили список всех контактов
        Groups allGroup = app.db().groupsRequestDB();//получили список всех групп
        int contactId = allContacts.iterator().next().getId();//рандомный контакт и получаем ID
        Groups contactGroups = app.db().contactInGroup(contactId);//получили список групп, в которые входит контакт
        Collection<GroupData> listFirst = new ArrayList<>(contactGroups);//преобразовали полученные данные в список 1
        Collection<GroupData> listSecond = new ArrayList<>(allGroup);//преобразовали все группы в список 2
        listSecond.removeAll(listFirst);//вывели разницу - группу, в которой контакт не состоит
        GroupData freeGroup = listSecond.iterator().next();//выбираем рандомную свободную от нашего контакта группу
        app.contact().gotoHomePage();//идем домой
        app.contact().selectContactByIdByName(contactId, freeGroup.getName());//добавляем контакт в свободную группу
        Groups contactGroupsAfter = app.db().contactInGroup(contactId);
        assertThat((contactGroupsAfter), CoreMatchers.equalTo(contactGroups.withAdded(freeGroup)));
    }
}


