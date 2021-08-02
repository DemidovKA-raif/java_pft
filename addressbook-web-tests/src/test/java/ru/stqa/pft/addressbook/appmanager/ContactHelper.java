package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public void setNameContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());


        /**
         * Выбор элемента из всплывающего списка
         */
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    public void create(ContactData contact, boolean creation) {
        addNewContact();
        setNameContact(contact, creation);
        clickNewContact();
    }

    public void modify(int index, ContactData contact) {
        modificationContact(index);
        setNameContact(contact, false);
        clickUpdateContact();
        gotoHomePage();
    }

    public void delete(int index) throws InterruptedException {
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

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name(("entry")));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            ContactData contact = new ContactData(id, firstname, null, lastName, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

