package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.tests.NameNewContact;

public class NewContactClass extends HelperBase {

    public NewContactClass(FirefoxDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void setNameContact(NameNewContact nameNewContact) {
        type(By.name("firstname"), nameNewContact.getFirstName());
        type(By.name("middlename"), nameNewContact.getMiddleName());
        type(By.name("lastname"), nameNewContact.getLastName());

    }

    public void setNickName(String nickName) {
        type(By.name("nickname"), nickName);
    }

    public void setHome(String s) {
        type(By.name("home"), s);
    }
}
