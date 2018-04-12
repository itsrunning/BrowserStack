package steps;

import com.thoughtworks.gauge.Step;


public class GoogleSearchSteps extends BaseStep {

    @Step("I search for <searchString> in the google page")
    public void searchFor(String searchString) {
        googleSearchPage.launchGoogle();
        googleSearchPage.searchFor(searchString);
    }

    @Step("I verify it has <result> in results")
    public void verifyResultsHas(String result) {
        googleResultsPage.verifyResultsHas(result);
    }
}