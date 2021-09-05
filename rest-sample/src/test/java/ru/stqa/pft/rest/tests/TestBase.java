package ru.stqa.pft.rest.tests;

import ru.stqa.pft.rest.appmanager.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager();

    public int ISSUEID = 10;

    @BeforeSuite
    public void setUp() throws Exception {
        skipIfNotFixed(ISSUEID);
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> issueStatus = app.issue().getIssueStatus(issueId);
        for (Issue issue : issueStatus) {
            String statusIssue = issue.getState_name();
            if (statusIssue.equals("Resolved")) {
                System.out.println("Status issue is correct, ID = "  + issueId);
                return false;
            }
        }
        System.out.println("Status issue is incorrect!!! ID = "  + issueId);
        return true;
    }
}
