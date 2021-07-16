package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class StepTesting {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      login("admin", "secret");
    }

    private void login(String username, String password) {
      wd.get("http://localhost/addressbook/group.php");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void reternToGroupePage() {
      wd.findElement(By.xpath("//div[@id='content']/form/span")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
      wd.findElement(By.linkText("group page")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    protected void addNewContact() {
      wd.findElement(By.linkText("add new")).click();
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
    }

    protected void setNameContact(NameNewContact nameNewContact) {
      wd.findElement(By.name("firstname")).sendKeys(nameNewContact.getFirstName());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(nameNewContact.getMiddleName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(nameNewContact.getLastName());
    }

    protected void setNickName(String nickName) {
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(nickName);
    }

    protected void setHome(String s) {
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(s);
    }

    protected void gotoHomePage() {
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
      wd.findElement(By.linkText("home page")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      logoutPage();
      wd.quit();
    }

    private void logoutPage() {
      wd.findElement(By.linkText("Logout")).click();
    }

    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }
}
