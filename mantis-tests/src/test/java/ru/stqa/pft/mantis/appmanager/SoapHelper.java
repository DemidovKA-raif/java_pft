package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
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
        MantisConnectPortType ms = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php"));
        ProjectData[] projectData = ms.mc_projects_get_user_accessible("administrator", "root");
       return Arrays.asList(projectData).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }
}

