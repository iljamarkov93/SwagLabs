import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;


public class LoginTest extends BaseTest  {

    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://www.saucedemo.com/");

    }


    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUserName("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login Failed");
    }

    @Test
    public void testLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUserName("standard_user");
        loginPage.clickLoginButton();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login without password is successful");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void testLoginWithoutUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login without username is successful");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");

    }

    @Test
    public void testLockOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUserName("locked_out_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Locked out user is successful");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
