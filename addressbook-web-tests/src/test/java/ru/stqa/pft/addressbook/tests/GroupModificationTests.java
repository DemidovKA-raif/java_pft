package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groupsRequestDB().size() == 0) {
            long now = System.currentTimeMillis();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("autotest"+now));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groupsRequestDB();
        GroupData modifiedGroup = before.iterator().next();
        long now = System.currentTimeMillis();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1"+now).withHeader("test2").withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groupsRequestDB();
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }


}
