import base.BaseTest;
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
}
