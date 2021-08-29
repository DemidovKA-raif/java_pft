package ru.stqa.pft.addressbook.tests;

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
    public void ensurePreconditions() {
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
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
            app.contact().gotoHomePage();
        }

//        if (app.db().contactAllGroups().size() <= 1) {
//            for (int i = 0; true; i++) {
//                if (app.db().contactAllGroups().size() <= 1) {
//                    Groups groups = app.db().groupsRequestDB();
//                    File photo = new File("src/test/resources/stru.png");
//                    app.contact().gotoHomePage();
//                    app.contact().create(new ContactData()
//                            .withFirstName("FirstName").withLastName("LastName").withMiddleName("MiddleName").withNickName("Donald")
//                            .inGroup(groups.iterator().next())
//                            .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhoto(photo), true);
//                    app.contact().gotoHomePage();
//                    Contacts before = app.db().contactsRequestDB();
//                    ContactData addGroupContact = before.iterator().next();
//                    app.contact().addGroupInContactById(addGroupContact);
//                    app.contact().gotoHomePage();
//                }
//                break;
//            }
//        }

    }


    @Test
    public void deleteContactInGroupTest() {
        if (!app.contact().verifyFreeContact()) {
            app.contact().verifyGroupContact();
        }

        Contacts before = app.db().contactsRequestDB();
        Groups beforeInGroups = app.db().contactAllGroups();
        ContactData contact = before.iterator().next();
        app.contact().gotoHomePage();
        String getNameGroup = contact.getGroups().iterator().next().getName();
        app.contact().contactDeleteGroup(contact, getNameGroup);

        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contactsRequestDB();
        assertThat(after, equalTo(before));

        Groups afterInGroups = app.db().contactAllGroups();
        if (afterInGroups.size() != 0) {
            assertThat((afterInGroups), equalTo(beforeInGroups));
        }
    }
}



