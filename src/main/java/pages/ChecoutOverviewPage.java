package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChecoutOverviewPage extends BasePage {

    private By cartItem = By.cssSelector("#checkout_summary_container > div > div.cart_list > div.cart_item");
    private By cancelButton = By.cssSelector("#cancel");
    private By finishButton = By.cssSelector("#finish");

    public ChecoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemName() {
        waitForVisible(cartItem);
        return driver.findElement(cartItem).getText();
    }

    public void clickCancelButton() {
        waitForClicable(cancelButton);
        driver.findElement(cancelButton).click();
    }

    public void clickFinishButton() {
        waitForClicable(finishButton);
        driver.findElement(finishButton).click();
    }



}
