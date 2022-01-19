package com.github.nicedfx.addressbook.tests;

import com.github.nicedfx.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTests.class);


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

}
