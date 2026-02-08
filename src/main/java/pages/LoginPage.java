package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By userName = By.cssSelector("#user-name");
    private By password = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");


    public LoginPage(WebDriver driver) {

        super(driver);
    }
    public void typeUserName(String query) {

        waitForVisible(userName).sendKeys(query);
    }

    public void typePassword(String query) {

        waitForVisible(password).sendKeys(query);
    }

    public void clickLoginButton() {

        waitForClicable(loginButton).click();
    }

    public void login(String username, String password) {
        typeUserName(username);
        typePassword(password);
        clickLoginButton();
    }
}
