package com.github.nicedfx.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        app.mail().start();
    }

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user@localhost.localdomain");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        app.mail().stop();
    }
}
