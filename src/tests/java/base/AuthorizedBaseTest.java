package base;

import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class AuthorizedBaseTest extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }
}
