import base.AuthorizedBaseTest;
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

}
