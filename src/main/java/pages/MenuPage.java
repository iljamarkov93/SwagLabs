package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {


    private By Logout = By.cssSelector("#logout_sidebar_link");
    private By BurgerMenu = By.cssSelector("#react-burger-menu-btn");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

        public void burgerMenuBtn() {
        waitForVisible(BurgerMenu);
        driver.findElement(BurgerMenu).click();

        }


        public void logoutBtn() {
            waitForVisible(Logout);
            driver.findElement(Logout).click();
        }

    }

