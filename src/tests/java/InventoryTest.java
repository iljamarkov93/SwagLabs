import base.AuthorizedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class InventoryTest extends AuthorizedBaseTest {


    @Test
    public void InventoryPageIsNotEmpty() {
        int productCount = inventoryPage.getInventoryCount();
        Assert.assertTrue(productCount > 0, "Inventory page is empty");
    }

    @Test
    public void firstInventoryPageItemDisplayed() {
        Assert.assertFalse(inventoryPage.getFirstItemTitle().isEmpty(), "First item title not displayed");
        Assert.assertFalse(inventoryPage.getFirstItemDescription().isEmpty(), "First item desc not displayed");
        Assert.assertTrue(inventoryPage.getFirstItemPrice() > 0, "First item price not displayed");
        Assert.assertTrue(inventoryPage.firstItemImageExists(), "First item image not displayed");
    }

    @Test
    public void openFirstItem() {
        inventoryPage.openFirstItem();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"), "First item page not displayed");

    }

    @Test
    public void removeItemFromCartWithInventoryPage() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"), "Item is not in the cart");
        cartPage.clickContinueShoppingButton();
        inventoryPage.removeFromCart();

    }


    @Test
    public void sortByPriceLowToHigh() {
        List<Double> pricesBeforeSort = inventoryPage.getItemPrices();
        inventoryPage.selectSortByValue("lohi");
        List<Double> pricesAfterSort = inventoryPage.getItemPrices();
        List<Double> expectedSortedPrices = new ArrayList<>(pricesBeforeSort);
        expectedSortedPrices.sort(Double::compareTo);

        Assert.assertEquals(pricesAfterSort, expectedSortedPrices, "Prices are not sorted from low to high");
    }

    @Test
    public void sortByPriceHighToLow() {
        List<Double> pricesBeforeSort = inventoryPage.getItemPrices();
        inventoryPage.selectSortByValue("hilo");
        List<Double> pricesAfterSort = inventoryPage.getItemPrices();
        List<Double> expectedSortedPrices = new ArrayList<>(pricesBeforeSort);
        expectedSortedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(pricesAfterSort, expectedSortedPrices, "Prices are not sorted from high to low");
    }

    @Test void sortByNameAToZ() {
        List<String> namesBeforeSort = inventoryPage.getItemNames();
        inventoryPage.selectSortByValue("az");
        List<String> namesAfterSort = inventoryPage.getItemNames();
        List<String> expectedSortedNames = new ArrayList<>(namesBeforeSort);
        expectedSortedNames.sort(Comparator.naturalOrder());
        Assert.assertEquals(namesAfterSort, expectedSortedNames, "Items are not sorted from A to Z");

    }

    @Test void sortByNameZToA() {
        List<String> namesBeforeSort = inventoryPage.getItemNames();
        inventoryPage.selectSortByValue("za");
        List<String> namesAfterSort = inventoryPage.getItemNames();
        List<String> expectedSortedNames = new ArrayList<>(namesBeforeSort);
        expectedSortedNames.sort(Comparator.reverseOrder());
        Assert.assertEquals(namesAfterSort, expectedSortedNames, "Items are not sorted from Z to A");
    }

}