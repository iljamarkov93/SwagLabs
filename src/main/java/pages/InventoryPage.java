package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {

    //Локаторы элементов страницы inventory
    private By inventoryList = By.cssSelector("#inventory_container > div");
    private By addToCartButton = By.cssSelector(".inventory_item:first-child button");
    private By removetButton = By.cssSelector("#remove-sauce-labs-backpack");
    private By shopingCartLink = By.cssSelector("#shopping_cart_container > a");

    //Локаторы первого товара страницы inventory
    private By firstItemtTitle = By.cssSelector(".inventory_item:first-child");
    private By firstItemPrices = By.cssSelector(".inventory_item:first-child .inventory_item_price");
    private By firstItemImage = By.cssSelector("#item_4_img_link > img");
    private By firstItemDescription = By.cssSelector(".inventory_item:first-child .inventory_item_desc");
    private By firstItemTitleLink = By.cssSelector(".inventory_item:first-child .inventory_item_name");

    //Локаторы сортировки товаров
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By itemPrices = By.cssSelector(".inventory_item_price");
    private By itemNames = By.cssSelector(".inventory_item_name");




    public InventoryPage(WebDriver driver) {

        super(driver);
    }

    public int getInventoryCount() {
        waitForVisible(inventoryList);
        List<WebElement> inventory = driver.findElements(inventoryList);
        return inventory.size();
    }

    public String getFirstItemTitle() {
        waitForVisible(firstItemtTitle);
        return driver.findElement(firstItemtTitle).getText();
    }

    public double getFirstItemPrice() {
        waitForVisible(firstItemPrices);
        String priceText = driver.findElement(firstItemPrices)
                .getText()
                .replace("$", "");
        return Double.parseDouble(priceText);
    }


    public String getFirstItemDescription() {
        waitForVisible(firstItemDescription);
        return driver.findElement(firstItemDescription).getText();
    }

    public void openFirstItem() {
        waitForVisible(firstItemTitleLink);
        driver.findElement(firstItemTitleLink).click();
    }

    public void addToCart() {
        waitForVisible(addToCartButton);
        driver.findElement(addToCartButton).click();
    }

    public void removeFromCart() {
        waitForVisible(removetButton);
        driver.findElement(removetButton).click();
    }

    public void openShoppingCart() {
        waitForClicable(shopingCartLink).click();
    }

    public boolean firstItemImageExists() {
        waitForVisible(firstItemImage);
        return driver.findElement(firstItemImage).isDisplayed();
    }

    public void selectSortByValue(String sortBy) {
        waitForVisible(sortDropdown);
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(sortBy);
    }

    public List<Double> getItemPrices() {
        return driver.findElements(itemPrices)
                .stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
















}
