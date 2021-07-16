package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{


    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        click(By.linkText("home page"));
    }
}
