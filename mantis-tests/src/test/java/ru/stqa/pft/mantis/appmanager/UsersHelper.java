package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class UsersHelper extends HelperBase{

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(String password, UsersData next) throws MessagingException, IOException {
       clickUserNameByID(next.getUsername());
       clickResetPassword();
       finish(password);
    }


    public void finish(String newPassword) throws MessagingException, IOException {
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages);
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//form[@id='account-update-form']/fieldset/span/button/span"));
    }

       private String findConfirmationLink(List<MailMessage> mailMessages) {
        MailMessage mailMessage = mailMessages.get(0);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
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
