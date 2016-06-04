package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by User on 01.06.2016.
 */
public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailSever(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException, InterruptedException {
        Users users = app.db().users();
        UserData userWithResetPassword = users.iterator().next();
        String newPassword = "new_password";

        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.resetPassword().resetPassword(userWithResetPassword);
        app.session().logout();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userWithResetPassword.getEmail());
        app.resetPassword().newPassword(confirmationLink, newPassword);

        HttpSession session = app.newSession();
        assertTrue(session.login(userWithResetPassword.getUsername(), newPassword));
        assertTrue(session.isLoggedInAs(userWithResetPassword.getUsername()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.contains(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
