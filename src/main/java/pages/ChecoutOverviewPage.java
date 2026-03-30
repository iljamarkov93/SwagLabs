package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ChecoutOverviewPage extends BasePage {

    private By cartItem = By.cssSelector("#checkout_summary_container > div > div.cart_list > div.cart_item");
    private By cancelButton = By.cssSelector("#cancel");
    private By finishButton = By.cssSelector("#finish");
    private By itemTotal = By.className(".summary_subtotal_label");
    private By summaryTax = By.className(".summary_tax_label");
    private By summmaryTotal = By.className(".summary_total_label");

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

    public double getItemTotal() {
        String text = driver.findElement(itemTotal).getText();
        return parsePrice(text.split("\\$")[1]);
    }

    public double getSummaryTax() {
        String text = driver.findElement(summaryTax).getText();
        return parsePrice(text.split("\\$")[1]);
    }

    public double getSummaryTotal() {
        String text = driver.findElement(summmaryTotal).getText();
        return parsePrice(text.split("\\$")[1]);
    }

}
