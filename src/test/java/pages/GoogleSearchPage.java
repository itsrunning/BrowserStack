package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchTextBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleSearchPage() {
        PageFactory.initElements(primaryWebDriver, this);
    }

    public void launchGoogle() {
            visit();
    }

    public void searchFor(String searchString) {
        searchTextBox.sendKeys(searchString + "\n");
    }

    private void visit() {
            String baseUrl = System.getenv("URL");
            System.out.println("URL::" + baseUrl + "\n");
            primaryWebDriver.get(baseUrl);
    }
}



