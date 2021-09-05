package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.UserData;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordUsers extends TestBase {


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testChangePasswordUsers() throws MessagingException, IOException {
        String password = app.getProperty("web.adminPassword");
        String user = app.getProperty("web.adminLogin");
        Users usersData = app.db().usersRequestDB();
        app.users().authorization(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.users().clickMenu();
        UsersData next = usersData.iterator().next();
        app.users().clickUserNameByID(next.getUsername());
        app.users().clickResetPassword();
        app.users().finish(password);
        assertTrue(app.newSession().login(user, password));
    }



//    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
//        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
//        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
//        return regex.getText(mailMessage.text);
//    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
