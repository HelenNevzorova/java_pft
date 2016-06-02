package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by User on 01.06.2016.
 */
public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(UserData user) {
        click(By.linkText("Manage Users"));
        click(By.xpath("//*[@href='manage_user_edit_page.php?user_id=" + user.getId() + "']"));
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public void newPassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//input[@value='Update User']"));
    }
}
