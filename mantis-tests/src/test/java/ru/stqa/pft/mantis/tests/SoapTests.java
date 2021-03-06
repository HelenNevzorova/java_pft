package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by User on 04.06.2016.
 */
public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void formalTestWithFixedIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1);
        System.out.println("Issue is fixed so test is ran");
        assertEquals(2 + 2, 4);
    }

    @Test
    public void formalTestWithOpenIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(2);
        System.out.println("Issue is NOT fixed so test is NOT ran");
    }
}
