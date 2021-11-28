package com.github.nicedfx.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/edit.php");
        login("admin", "secret");
    }

    @Test
    public void testContactCreationTests() throws Exception {
        goToAddNewContactPage();
        setFisrtName(new ContactData("ThisIsFirstName", "ThisIsMiddleName", "ThisIsLastName",
                "ThisIsAddress", "ThisIsHomePhone", "ThisIsMobilePhone", "thisIs@email.com"));
        createContact();
        goToHomePage();
        logout();
    }

    private void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    private void goToHomePage() {
        driver.findElement(By.linkText("home")).click();
    }

    private void createContact() {
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void setFisrtName(ContactData cintactData) {
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(cintactData.getFirstName());
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys(cintactData.getMiddleName());
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(cintactData.getLastName());
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(cintactData.getAddress());
        driver.findElement(By.name("home")).clear();
        driver.findElement(By.name("home")).sendKeys(cintactData.getHomePhone());
        driver.findElement(By.name("home")).clear();
        driver.findElement(By.name("home")).sendKeys(cintactData.getHomePhone());
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(cintactData.getMobile());
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(cintactData.getEmail());
    }

    private void goToAddNewContactPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    private void login(String userName, String password) {
        driver.findElement(By.name("user")).click();
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(userName);
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
