package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CheckoutOverviewPage extends BasePage {

    private By cartList = By.cssSelector(".cart_list .inventory_item_name");
    private By cancelButton = By.cssSelector("#cancel");
    private By finishButton = By.cssSelector("#finish");
    private By itemTotal = By.className(".summary_subtotal_label");
    private By summaryTax = By.className(".summary_tax_label");
    private By summmaryTotal = By.className(".summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getOverviewPageItemNames() {
        List<WebElement> elements = driver.findElements(cartList);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
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
