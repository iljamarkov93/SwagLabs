package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChecoutInformationPage extends BasePage {

    private By firstNameField = By.cssSelector("#first-name");
    private By lastNameField = By.cssSelector("#last-name");
    private By zipFiels = By.cssSelector("#postal-code");
    private By cancelButton = By.cssSelector("#cancel");
    private By continueButton = By.cssSelector("#continue");



    public ChecoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void fillFirstNameField(String query) {
        waitForVisible(firstNameField).sendKeys(query);

    }

    public void fillLastNameField(String query) {
        waitForVisible(lastNameField).sendKeys(query);

    }

    public void fillZipField(String query) {
        waitForVisible(zipFiels).sendKeys(query);

    }

    public void clickCancelButton() {
        waitForClicable(cancelButton).click();
    }

    public void clickContinueButton() {
        waitForVisible(continueButton).click();
    }

}
