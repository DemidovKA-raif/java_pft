package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void reternToGroupePage() {
        click(By.xpath("//div[@id='content']/form/span"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
        click(By.linkText("group page"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_header"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }
}
