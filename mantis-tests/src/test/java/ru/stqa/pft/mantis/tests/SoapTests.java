package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;

import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {


    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getId());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();
        Issue issue = new Issue().withSummary("TEst Issue").withDescription("Test Description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }


    @Test
    public void testBeforeCheckStatus() {
        System.out.println("test pass! task id = " + ISSUEID + " status not closed!");
    }
}

