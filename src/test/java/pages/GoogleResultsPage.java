package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleResultsPage extends BasePage {

    public GoogleResultsPage() {
        PageFactory.initElements(primaryWebDriver, this);
    }

    public void verifyResultsHas(String resultString) {
        assertThat(waitForElementToBeVisibleUsing(By.partialLinkText(resultString))).isTrue();
    }
}


