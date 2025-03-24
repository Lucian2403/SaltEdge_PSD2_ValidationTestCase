package saltEdge.stepDefinitions;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BRIGHT_CYAN_TEXT;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import saltEdge.pages.HomePage;
import saltEdge.utils.BasePage;

public class ValidationStepDefinitions extends BasePage {
    HomePage homePage = new HomePage();
    private String sideParentSectionName;
    private String sideChildSectionName;

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
        homePage.navigateToTableSection(sideParentSectionName, sideChildSectionName, tableName);
    }


}
