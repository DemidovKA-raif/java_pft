package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        click(By.linkText("home page"));
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }


}
