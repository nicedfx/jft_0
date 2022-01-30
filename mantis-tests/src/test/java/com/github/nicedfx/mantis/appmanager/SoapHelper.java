package com.github.nicedfx.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import com.github.nicedfx.mantis.model.Issue;
import com.github.nicedfx.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    private MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php"));
        //TODO: move url to config file.
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {

        ProjectData[] projects = getMantisConnect().mc_projects_get_user_accessible("administrator", "root");

        return Arrays.stream(projects).map(p -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        return getIssue(issueId);
    }

    public Issue getIssue(BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
        IssueData issueData = getMantisConnect().mc_issue_get("administrator", "root", issueId);
        return new Issue()
                .withId(issueData.getId().intValue())
                .withSummary(issueData.getSummary())
                .withDescription(issueData.getDescription())
                .withProject(new Project().withId(issueData.getId().intValue()).withName(issueData.getProject().getName()))
                .withStatus(issueData.getStatus().getName());
    }
}
