package com.github.nicedfx.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordChangeHelper extends HelperBase {


    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void logIn(String username, String paswword) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.id("username"), username);
        click(By.cssSelector("input[value='Login']"));
        type(By.id("password"), paswword);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetUserPassword(int userId) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_edit_page.php?user_id=" + userId);
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
