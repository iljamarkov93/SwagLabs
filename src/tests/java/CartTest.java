import base.AuthorizedBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    @Test void completePurchaseSuccessfully() {
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

    @Test void  continueShoppingWithoutFirstName() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.fillZipField("110117");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test void  continueShoppingWithoutLastName() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Test");
        checoutInformationPage.fillZipField("Test");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test void  continueShoppingWithoutZip() {
        inventoryPage.addToCart();
        inventoryPage.openShoppingCart();
        cartPage.clickCheckoutButton();
        checoutInformationPage.fillFirstNameField("Test");
        checoutInformationPage.fillLastNameField("Test");
        checoutInformationPage.clickContinueButton();
        Assert.assertFalse(driver.getCurrentUrl().contains("checkout-step-two"), "Error, continueShoppingWithoutFirstName");
        Assert.assertEquals(checoutInformationPage.getErrorMessage(), "Error: Postal Code is required");

    }



}
