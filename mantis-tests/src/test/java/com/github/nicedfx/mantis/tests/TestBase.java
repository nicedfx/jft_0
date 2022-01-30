package com.github.nicedfx.mantis.tests;

import com.github.nicedfx.mantis.appmanager.ApplicationManager;
import com.github.nicedfx.mantis.model.Issue;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.EDGE.browserName()));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    protected boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        Issue issue = app.soap().getIssue(BigInteger.valueOf(issueId));

        return !issue.getStatus().equals("closed");

    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
