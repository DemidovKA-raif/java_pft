package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class NewContactHelper extends HelperBase {

    public NewContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void setNameContact(NameNewContact nameNewContact) {
        type(By.name("firstname"), nameNewContact.getFirstName());
        type(By.name("middlename"), nameNewContact.getMiddleName());
        type(By.name("lastname"), nameNewContact.getLastName());
        type(By.name("nickname"), nameNewContact.getNickName());
        type(By.name("home"), nameNewContact.getMyHome());
    }
}
