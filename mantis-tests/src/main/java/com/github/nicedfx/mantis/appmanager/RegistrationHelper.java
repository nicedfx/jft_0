package com.github.nicedfx.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
       type(By.name("username"), "administrator");
       type(By.name("email"), email);
       click(By.cssSelector("input[value='Signup']"));
    }
}
