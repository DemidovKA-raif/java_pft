package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();
        if (app.db().groupsRequestDB().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testDeletion() {
        Groups before = app.db().groupsRequestDB();
        GroupData deleteGroup = before.iterator().next();
        app.group().delete(deleteGroup);
        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groupsRequestDB();
        assertThat(after, CoreMatchers.equalTo(before.withOut(deleteGroup)));
        verifyGroupListInUI();
    }


}

