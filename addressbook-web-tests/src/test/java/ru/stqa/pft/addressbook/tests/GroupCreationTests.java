package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
//        for (int i = 0; i < 10; i++) {
            app.getNavigationHelper().gotoGroupPage();
            int before = app.getGroupHelper().getGroupCount();
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            int after = app.getGroupHelper().getGroupCount();
            Assert.assertEquals(after, before + 1);
        }

    }
//}
