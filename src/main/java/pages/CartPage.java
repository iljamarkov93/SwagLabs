package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage {

    private By cartList = By.cssSelector(".cart_item");
    private By cartItemNames = By.cssSelector(".cart_item .inventory_item_name");
    private By removeFromCartButton = By.cssSelector("#remove-sauce-labs-backpack");
    private By continueShoppingButton = By.cssSelector("#continue-shopping");
    private By checkoutButton = By.cssSelector("#checkout");




    public CartPage(WebDriver driver) {

        super(driver);
    }

    public int getCartItemsCount() {
        waitForVisible(cartList);
        List<WebElement> list = driver.findElements(cartList);
        return list.size();
    }

    public List<String> getCartItemNames() {
                return driver.findElements(cartItemNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getCartItemsPrice() {
        List<WebElement> priceElements = driver.findElements(cartList);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            prices.add(parsePrice(priceElement.getText()));
        }
        return prices;
    }

    public double sumPrices(List<Double> prices) {
        double sum = 0;
        for (Double price : prices) {
            sum += price;
        }
        return sum;
    }

    public boolean isItemInCart(String itemName) {
        return getCartItemNames().contains(itemName);
    }

    public void clickRemoveFromCartButton() {
        waitForVisible(removeFromCartButton);
        driver.findElement(removeFromCartButton).click();
    }

    public void clickContinueShoppingButton() {
        waitForClicable(continueShoppingButton);
        driver.findElement(continueShoppingButton).click();

    }

    public void clickCheckoutButton() {
        waitForClicable(checkoutButton);
        driver.findElement(checkoutButton).click();
    }




}
