import base.AuthorizedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends AuthorizedBaseTest {

    @Test

    public void cartListIsNotEmpty() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"), "Cart list is empty");
    }

    @Test
    public void removeItemFromCart() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickRemoveFromCartButton();
        Assert.assertFalse(cartPage.isItemInCart("Sauce Labs Backpack"), "Item was not removed from cart");

    }

    @Test
    public void completePurchaseSuccessfully() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Ilia");
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.fillZipField("110117");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(checkoutOverviewPage.getOverviewPageItemNames().isEmpty(), "Cart list is empty");
        checkoutOverviewPage.clickFinishButton();
        Assert.assertTrue(checkoutCompletePage.getCompleteContainer().contains("Thank you for your order!"), "Complete message absent!");
    }

    @Test
    public void  continueShoppingWithoutFirstName() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.fillZipField("110117");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void  continueShoppingWithoutLastName() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Test");
        checoutInformationPage.fillZipField("Test");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test
    public void  continueShoppingWithoutZip() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Test");
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: Postal Code is required");

    }


    @Test
    public void checkSumPriceOfTwoItems() throws InterruptedException {
        List<String> addedItemsFromInventoryPage = inventoryPage.addItemsToCart(2);
        inventoryPage.openShoppingCart();
        List<String> itemsInCart = cartPage.getCartItemNames();
        Assert.assertEquals(addedItemsFromInventoryPage, itemsInCart, "Items doesn't match");
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Ilia");
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.fillZipField("0123456789");
        checoutInformationPage.clickContinueButton();
        List<String> itemsInOverviewPage = checkoutOverviewPage.getOverviewPageItemNames();
        System.out.println("itemsInOverviewPage" + itemsInOverviewPage);
        System.out.println("itemsInOverviewPage" +  itemsInOverviewPage.size());
        Assert.assertEquals(addedItemsFromInventoryPage, itemsInOverviewPage, "Items doesn't match");



    }

}
