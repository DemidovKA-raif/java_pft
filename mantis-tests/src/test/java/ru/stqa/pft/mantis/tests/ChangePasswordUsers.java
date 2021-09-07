package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;


import static org.testng.Assert.assertFalse;
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
        app.users().authorization(user, password);
        app.users().clickMenu();
        UsersData next = usersData.iterator().next();
        app.users().resetPassword(password, next);
        HttpSession session = app.newSession();
        assertTrue(session.login(user, password));
        assertTrue(session.isLoggedInAs(user));
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
