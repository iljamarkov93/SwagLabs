package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {


    private By BurgerMenu = By.cssSelector("#react-burger-menu-btn");
    private By Logout = By.cssSelector("#logout_sidebar_link");
    private By About = By.cssSelector("#about_sidebar_link");
    private By AboutPageText = By.cssSelector("#__next > div.MuiBox-root.css-1kakr30 >" +
            " div.MuiBox-root.css-1km0eci > div > div > div.MuiStack-root.css-63esow >" +
            " div > div.MuiStack-root.css-ubwyx9 > div.MuiBox-root.css-0 >" +
            " div.MuiBox-root.css-zm2eng > div > p");

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

        public void aboutLink() {
        waitForClicable(About).click();

        }

        public boolean isTextPresent() {
        waitForVisible(AboutPageText);
        String actualText = driver.findElement(AboutPageText).getText();
        return actualText.contains("Built for enterprise engineering teams, Sauce AI agents auto-generate, " +
                "execute, debug and autonomously update tests — delivering 38% more productivity, " +
                "75% reduction in critical issues, and 46% higher ship frequency.");

        }

    }

