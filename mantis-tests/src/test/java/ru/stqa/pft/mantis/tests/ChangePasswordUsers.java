package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import java.util.List;

public class ChangePasswordUsers extends TestBase{

    @Test
    public void testChangePasswordUsers(){
        long now = System.currentTimeMillis();
        Users usersData = app.db().usersRequestDB();
        app.users().authorization(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.users().clickMenu();
        UsersData next = usersData.iterator().next();
        app.users().clickUserNameByID(next.getUsername());
        app.users().clickResetPassword();
//        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 100000);
//        String confirmationLink = findConfirmationLink(mailMessages, email);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
