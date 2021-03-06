package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        modificationContact(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String mailFirst = wd.findElement(By.name("email")).getAttribute("value");
        String mailSecond = wd.findElement(By.name("email2")).getAttribute("value");
        String mailThree = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname)
                .withLastName(lastname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withMailFirst(mailFirst)
                .withMailSecond(mailSecond)
                .withMailThree(mailThree)
                .withAddress(address);
    }

    public void setNameContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        attach(By.name("photo"), contactData.getPhoto());


        if (creation) {
            if (contactData.getGroups().size()>0) {
                Assert.assertTrue(contactData.getGroups().size()==1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
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
        contactCache = null;
    }

    public void modify(ContactData contact) {
        modificationContact(contact.getId());
        setNameContact(contact, false);
        clickUpdateContact();
        contactCache = null;
        gotoHomePage();
    }



    public void contactDeleteGroupTest(int id, String text) {
        selectTypeGroupsInContacts(text);
        selectContactById(id);
        deleteGroupIsContact();
        gotoHomePage();
    }


    public void selectTypeGroupsInContacts(String text) {
        click(By.name("group"));
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(text);
    }

    public void clickAddGroup() {
        click(By.xpath("//input[@value='Add to']"));
    }

    public void deleteGroupIsContact() {
        click(By.name("remove"));
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        clickDeletionContact();
        Thread.sleep(500);
        contactCache = null;
        gotoHomePage();
    }

    public void modificationContact(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void selectContactByIdByName(int id, String name) throws InterruptedException {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
        clickAddGroup();
        Thread.sleep(2000);
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name(("entry")));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allMail = cells.get(4).getText();
            String address = cells.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstname)
                    .withLastName(lastName)
                    .withAllPhones(allPhones)
                    .withAllMail(allMail)
                    .withAddress(address));
        }
        return new Contacts(contactCache);
    }


}

