package appmanager;

public class ApplicationManager {

    private IssuesHelper issuesHelper;


    public IssuesHelper issue() {
        if (issuesHelper == null) {
            issuesHelper = new IssuesHelper(this);
        }
        return issuesHelper;
    }
}
