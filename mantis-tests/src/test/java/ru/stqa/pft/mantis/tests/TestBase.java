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

    private int ISSUEID;

    public int setISSUEID(int ISSUEID) {
        this.ISSUEID = ISSUEID;
        return ISSUEID;
    }

    public int getISSUEID(int i) {
        return ISSUEID;
    }



    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
        skipIfNotFixed(setISSUEID(3));
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

    @AfterSuite
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
