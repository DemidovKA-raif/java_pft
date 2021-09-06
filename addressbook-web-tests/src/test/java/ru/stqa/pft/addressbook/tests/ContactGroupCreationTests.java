package ru.stqa.pft.addressbook.tests;

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
            app.group().create(new GroupData().withName("test M"));
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
        Contacts allContacts = app.db().contactsRequestDB();//получили список всех контактов
        Groups allGroup = app.db().groupsRequestDB();//получили список всех групп
        int contactId = allContacts.iterator().next().getId();//рандомный контакт и получаем ID
        Groups contactGroups = app.db().contactInGroupPack1(contactId);//получили список групп, в которые входит контакт
        Collection<GroupData> listFirst = new ArrayList<>(contactGroups);//преобразовали полученные данные в список 1
        Collection<GroupData> listSecond = new ArrayList<>(allGroup);//преобразовали все группы в список 2
        listSecond.removeAll( listFirst );//вывели лишь разницу - группу, в которой контакт не состоит
        System.out.println(listSecond);//выводим результат для понимания, есть ли группа свободная
        GroupData freeGroup = listSecond.iterator().next();//выбираем рандомную свободную группу
        Groups beforeGroup = app.db().contactInGroup(freeGroup.getId());
        app.contact().gotoHomePage();//идем домой
        app.contact().selectContactByIdByName(contactId, freeGroup.getName());//добавляем контакт в свободную группу
        Groups afterGroup = app.db().contactInGroup(freeGroup.getId());
        assertThat((afterGroup), equalTo(beforeGroup.withAdded(freeGroup)));



//        GroupData randomGroup = group.iterator().next();//выбрали рандомную группу
//        int randomGroupId = randomGroup.getId();//получили ID рандомной группы
//        Groups contactData = app.db().contactInGroupPack1(randomGroupId);//нужно получить список контактов, которых нет в этой группе
//        System.out.println(contactData);



//        Groups groupForContactByID = app.db().contactInGroupPack1(contactGroups);//получаем список групп, в которых состоит контакт
//        Iterator<GroupData> iterator = groupForContactByID.iterator();
//        int id = String.format(iterator.toString(), id)
//        GroupData groupsFreeAtContact = app.db().contactInGroupPack2(id).iterator().next();
//        System.out.println(groupsFreeAtContact);


//        GroupData groupForContact = groupForContactByID.iterator().next();//выбрали случайную группу без контакта из предыдущего шага
//        Groups beforeInGroups = app.db().contactInGroupPack1(contactGroups);
//        app.contact().gotoHomePage();
//        app.contact().selectContactByIdByName(contactGroups, groupForContact.getName());
//        Contacts after = app.db().contactsRequestDB();
//        assertThat(after.size(), equalTo(before.size()));
//        Groups afterInGroups = app.db().contactAllGroups();
//        assertThat((afterInGroups), equalTo(beforeInGroups.withAdded(groupForContact)));
    }
}
