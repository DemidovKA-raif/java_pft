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

public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProject() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType ms = getMantisConnectPortType();
        ProjectData[] projectData = ms.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projectData).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnectPortType() throws ServiceException, MalformedURLException {
        MantisConnectPortType ms = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantisbtURL")));
        return ms;
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

    public Set<Project> getResolution() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnectPortType();
        ObjectRef[] resolutions = mc.mc_enum_resolutions("administrator", "root");
        return Arrays.asList(resolutions).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }
    }


