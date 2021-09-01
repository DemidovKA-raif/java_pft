package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoapHelper {
    private ApplicationManager app;
    private int idIss;
//    int idIss;


    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProject() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPortType();
        ProjectData[] projectData = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projectData).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnectPortType() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantisbtURL")));
        return mc;
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPortType();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData newIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(newIssueData.getId().intValue()).withSummary(newIssueData.getSummary()).withDescription(newIssueData.getDescription())
                .withProject(new Project().withId(newIssueData.getProject().getId().intValue()).withName(newIssueData.getProject().getName()));
    }

    public Set<Issue> getAllIssues() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPortType();
        IssueData[] issueData = mc.mc_project_get_issues("administrator", "root", BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(-1));
        return Arrays.asList(issueData).stream().map((p) -> new Issue().withIssue_id(p.getId().intValue()).withStatus(p.getStatus().getName())).collect(Collectors.toSet());
    }

    public Set<Issue> getIssueForID(int idIss) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPortType();
        IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(idIss));
        return Stream.of(issueData).map((p) -> new Issue().withStatus(p.getStatus().getName())).collect(Collectors.toSet());
//    }return Arrays.asList(projectData).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());

//        return Arrays.asList(issueData).stream().map((p) -> new Issue().withStatus(p.getStatus().getName())).collect(Collectors.toSet());

//    public Set<Issue> getResolution(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
//        MantisConnectPortType mc = getMantisConnectPortType();
//        IssueData issueGet = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issue.getIssue_id()));
//        return Arrays.asList(issueGet).stream().map((p) -> new Issue().withId(p.getId().intValue())).collect(Collectors.toSet());
    }
}























