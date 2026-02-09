import base.AuthorizedBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MenuTest extends AuthorizedBaseTest {


    @Test
    public void LogoutTest() {
        menuPage.burgerMenuBtn();
        menuPage.logoutBtn();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(),
                "Logout failed: login button not visible");


    }
}
git