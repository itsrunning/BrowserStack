package pages;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {

    static WebDriver primaryWebDriver;

    BasePage() {
        primaryWebDriver = Driver.getDriver();
    }

    public boolean waitForElementToBeVisibleUsing(By strategy) {
        try {
            WebDriverWait wait = new WebDriverWait(primaryWebDriver, 100);
            wait.until(ExpectedConditions.visibilityOfElementLocated(strategy));
            return true;
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return false;
        }
    }

}
