import base.AuthorizedBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

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
        Assert.assertFalse(checoutOverviewPage.getCartItemName().isEmpty(), "Cart list is empty");
        checoutOverviewPage.clickFinishButton();
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
    public void checkSumPriceOfTwoItems() {
        List<String> addedItemsFromInventoryPage = inventoryPage.addItemsToCart(2);
        inventoryPage.openShoppingCart();
        List<String> itemsInCart = cartPage.getCartItemNames();
        Assert.assertEquals(addedItemsFromInventoryPage, itemsInCart);



    }


}
