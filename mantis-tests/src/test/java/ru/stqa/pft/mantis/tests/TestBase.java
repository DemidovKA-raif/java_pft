package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    /**
     * Я не смог придумать, как передавать параметр непосредственно из теста, пришлось его вынести как ручной.
     */
    public int ISSUEID = 10;


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
        isIssueList(); //опция, для красоты, случайно получилось
        skipIfNotFixed(ISSUEID);
    }


    public static boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        Set<Issue> issueForID = app.soap().getIssueForID(issueId);
        for (Issue issue : issueForID) {
            String statusIssue = issue.getStatus();
            if (statusIssue.equals("closed"))
                return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue " + issueId);
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public void isIssueList() throws MalformedURLException, ServiceException, RemoteException {
        Set<Issue> issues = app.soap().getAllIssues();
        for (Issue issue : issues) {
            String allIssuesStatus = "Status = " + issue.getStatus() + "; " + "ID = " + issue.getIssue_id();
            System.out.println("List of all open tasks: " + allIssuesStatus + System.lineSeparator() + "_______________________");
        }

    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
