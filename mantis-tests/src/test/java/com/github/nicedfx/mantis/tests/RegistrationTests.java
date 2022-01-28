package com.github.nicedfx.mantis.tests;

import com.github.nicedfx.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String email = "user" + now + "@localhost.localdomain";
        String userName = "user" + now;
        String password = "password";

        app.registration().start(userName, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = finConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        assertTrue(app.newSession().login(userName, password));
    }

    @Test
    public void testChangeUserPassword() {

    }

    private String finConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        app.mail().stop();
    }
}
