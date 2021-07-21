package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NameNewContact;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void modificationContact(){
        click(By.xpath("//table[@id='maintable']/tbody/tr[4]/td[8]/a/img"));
    }

    public void setNameContact(NameNewContact nameNewContact, boolean creation) {
        type(By.name("firstname"), nameNewContact.getFirstName());
        type(By.name("middlename"), nameNewContact.getMiddleName());
        type(By.name("lastname"), nameNewContact.getLastName());
        type(By.name("nickname"), nameNewContact.getNickName());


//        click(By.linkText("home page"));

        /**
         * Выбор элемента из всплывающего списка
         */
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(nameNewContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void clickNewContact(){
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void clickUpdateContact(){
        click(By.name("update"));
    }
}

