package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GruopeCreationTests extends StepTesting{


  @Test
  public void testGruopeCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    reternToGroupePage();

  }


}
