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
import java.util.List;

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
            Groups group = app.db().groupsRequestDB();
            GroupData groupForContact = group.iterator().next();
            app.contact().selectContactByIdByName(contactForGroup.getId(), groupForContact.getName());
        }
    }


    @Test
    public void deleteContactInGroupTest() {

        ContactData groupData = app.db().contactAllGroups();//получили контактам c группой
        int contactId = groupData.getId();//получили ID контакта
        Groups groupBefore = app.db().contactInGroup(contactId);//получили группу контакта
        GroupData randomGroup = groupBefore.iterator().next();
        String groupName = randomGroup.getName();//имя группы с контактом
        app.contact().gotoHomePage();
        app.contact().contactDeleteGroupTest(contactId, groupName);
        Groups contactInGroupAfter = app.db().contactInGroup(contactId);
        assertThat((contactInGroupAfter), CoreMatchers.equalTo(groupBefore.withOut(randomGroup)));
    }
}

//                                  for memory
//        Contacts allContacts = app.db().contactsRequestDB();//получили список всех контактов
//        Groups allGroup = app.db().groupsRequestDB();//получили список всех групп
//        int contactId = allContacts.iterator().next().getId();//рандомный контакт и получаем ID
//        Groups contactGroups = app.db().contactInGroup(contactId);//получили список групп, в которые входит контакт
//        if(contactGroups.size()==0){
//            Collection<GroupData> listGroup = new ArrayList<>(allGroup);//преобразовали все группы в список 2
//            GroupData freeGroup = listGroup.iterator().next();//выбираем рандомную свободную от нашего контакта группу
//            app.contact().gotoHomePage();//идем домой
//            app.contact().selectContactByIdByName(contactId, freeGroup.getName());
//            app.contact().contactDeleteGroupTest(contactId, freeGroup.getName());
//            Groups contactGroupsAfter = app.db().contactInGroup(contactId);
//            assertThat((contactGroupsAfter), CoreMatchers.equalTo(contactGroups.withOut(freeGroup)));
//            return;
//        }
//        Collection<GroupData> listGroups = new ArrayList<>(contactGroups);
//        GroupData freeGroup = listGroups.iterator().next();
//        app.contact().gotoHomePage();
//        app.contact().contactDeleteGroupTest(contactId, freeGroup.getName());
//        Groups contactGroupsAfter = app.db().contactInGroup(contactId);
//        assertThat((contactGroupsAfter), CoreMatchers.equalTo(contactGroups.withOut(freeGroup)));



