package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private By completeContainer = By.cssSelector("#checkout_complete_container");
    private By backHomeButton = By.cssSelector("#back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteContainer() {
        waitForVisible(completeContainer);
        return driver.findElement(completeContainer).getText();
    }

    public void clickBackHomeButton() {
        waitForClicable(backHomeButton);
        driver.findElement(backHomeButton).click();
    }


}
