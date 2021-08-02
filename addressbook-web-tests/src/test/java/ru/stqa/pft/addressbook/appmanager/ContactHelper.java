package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NameNewContact;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void modificationContact(int i) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(i).click();
    }

    public void setNameContact(NameNewContact nameNewContact, boolean creation) {
        type(By.name("firstname"), nameNewContact.getFirstName());
        type(By.name("middlename"), nameNewContact.getMiddleName());
        type(By.name("lastname"), nameNewContact.getLastName());
        type(By.name("nickname"), nameNewContact.getNickName());


        /**
         * Выбор элемента из всплывающего списка
         */
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(nameNewContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void clickNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void clickUpdateContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public void createContact(NameNewContact contact, boolean creation) {
        addNewContact();
        setNameContact(contact, creation);
        clickNewContact();
    }

    public void modifyContact(int index, NameNewContact contact) {
        modificationContact(index);
        setNameContact(contact, false);
        clickUpdateContact();
        gotoHomePage();
    }

    public void deleteContact(int index) throws InterruptedException {
        selectContact(index);
        clickDeletionContact();
        Thread.sleep(500);
        gotoHomePage();
    }

    public void clickDeletionContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    /**
     * Выбор всех элементов по индексу и клик по установленному (-1 == последний элемент)
     */
    public void selectContact(int indexContact) {
        wd.findElements(By.name("selected[]")).get(indexContact).click();
    }

    public List<NameNewContact> getContactList() {
        List<NameNewContact> contacts = new ArrayList<NameNewContact>();
        List<WebElement> elements = wd.findElements(By.name(("entry")));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            NameNewContact contact = new NameNewContact(id, firstname, null, lastName, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

