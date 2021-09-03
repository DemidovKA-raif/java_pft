package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import static java.lang.Thread.sleep;

public class UsersHelper extends HelperBase{

    public UsersHelper(ApplicationManager app) {
        super(app);
    }
    public void authorization(String username, String password) {
        login(username);
        password(password);
    }
   public void login(String username){
       wd.get(app.getProperty("web.BaseURL"));
       type(By.name("username"), username);
       click(By.cssSelector("input[type='submit']"));
   }

    public void password(String password){
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void clickMenu()  {
        click(By.xpath("//li[7]/a/i"));
        click(By.xpath("//a[contains(@href, '/mantisbt-2.25.2/manage_user_page.php')]"));
    }

    public void clickUserNameByID(String username){
        click(By.xpath("//a[contains(text(),'"+ username + "')]"));
    }

    public void clickResetPassword(){
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }




}
