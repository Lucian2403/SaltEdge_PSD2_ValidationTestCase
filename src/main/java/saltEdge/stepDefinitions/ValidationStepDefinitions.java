package saltEdge.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.Map;
import saltEdge.pages.HomePage;
import saltEdge.utils.BasePage;

public class ValidationStepDefinitions extends BasePage {

    HomePage homePage = new HomePage();
    private String sideParentSectionName;
    private String sideChildSectionName;
    private String tableName;

    @Given("user is on Berlin Group (.*) - (.*) - (.*) Section$")
    public void verifySection(String sectionName, String sideParentSectionName, String sideChildSectionName) {
        setUpBrowser();
        this.sideParentSectionName = sideParentSectionName;
        this.sideChildSectionName = sideChildSectionName;
        homePage.navigateToMainSection(sectionName);
        homePage.checkSectionHeader(sectionName);
        homePage.navigateToSideSection(sideParentSectionName, sideChildSectionName, sectionName);
    }

    @And("user is on (.*) table section$")
    public void navigateToTableSection(String tableName) {
        this.tableName = tableName;
        homePage.navigateToTableSection(sideParentSectionName, sideChildSectionName, tableName);
    }

    @Then("the Header should have the following types:")
    public void validateHeadersWithTypes(Map<String, String> headersAndTypes) {
        headersAndTypes.forEach((header, type) ->
                                        homePage.getHeaderTypeValue(sideParentSectionName, sideChildSectionName,
                                                                    tableName, header, type)
        );
    }
}
