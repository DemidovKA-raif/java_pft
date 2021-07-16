package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GruopeCreationTests extends StepTesting{


  @Test
  public void testGruopeCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.submitGroupCreation();
    app.reternToGroupePage();

  }
}
