package com.github.nicedfx.mantis.tests;

import com.github.nicedfx.mantis.model.MailMessage;
import com.github.nicedfx.mantis.model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

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
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finishPasswordModification(confirmationLink, password);

        assertTrue(app.newSession().login(userName, password));
    }

    @Test
    public void testChangeUserPassword() throws IOException {
        long now = System.currentTimeMillis();
        String password = String.valueOf(now);
        UserData user = app.db().users().stream()
                .filter(u -> !u.getUsername().equals("administrator"))
                .findAny().get();

        app.userPasswordChange().logIn("administrator", "root");
        app.userPasswordChange().resetUserPassword(user.getId());

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());

        app.userPasswordChange().finishPasswordModification(confirmationLink, password);

        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        app.mail().stop();
    }
}
