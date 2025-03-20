package saltEdge.stepDefinitions;

import io.cucumber.java.en.Given;
import saltEdge.pages.AisPage;
import saltEdge.pages.HomePage;
import saltEdge.utils.BasePage;

public class AISValidationStepDefinitions extends BasePage {
    HomePage homePage = new HomePage();
    AisPage AisPage = new AisPage();
    private final String sectionName = "AIS";

    @Given("user is on Berlin Group AIS Card Transaction Section")
    public void verifyAISCardTransactionSection() {
        setUpBrowser();
        homePage.navigateToSection(sectionName);
        homePage.checkSectionHeader(sectionName);
    }
}
