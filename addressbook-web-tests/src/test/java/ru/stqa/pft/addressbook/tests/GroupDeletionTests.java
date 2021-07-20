package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends StepTesting{

    @Test
    public void testDeletion(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroupe();
        app.getGroupHelper().deleteSelectedGroupe();
        app.getGroupHelper().reternToGroupePage();
    }
}
