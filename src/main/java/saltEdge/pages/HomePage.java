package saltEdge.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static saltEdge.pages.Locators.navigationMenuSection;
import static saltEdge.pages.Locators.relatedErrorsTableSection;
import static saltEdge.pages.Locators.requestParametersTableSection;
import static saltEdge.pages.Locators.responseTableSection;
import static saltEdge.pages.Locators.sectionHeaderElement;
import static saltEdge.pages.Locators.sideSectionElement;
import static saltEdge.pages.Locators.tableSection;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import saltEdge.utils.BasePage;

public class HomePage extends BasePage {

    SelenideElement tableSectionElement;


    //////ELEMENTS/////
    public void navigateToMainSection(String section) {
        waitForPageToLoad(60);
        smartClick($(navigationMenuSection(section)));
    }

    public void checkSectionHeader(String sectionHeader) {
        try {
            $(sectionHeaderElement(sectionHeader)).shouldBe(visible);
        } catch (ElementNotFound e) {
            String fallbackHeader = sectionHeader.replaceAll(" ", "").toLowerCase();
            $(sectionHeaderElement(fallbackHeader)).shouldBe(visible);
        }
        System.out.println(colorize("\nThe " + sectionHeader + " section is opened", GREEN_TEXT()));
    }

    public void navigateToSideSection(String sideMainSection, String sideChildSection, String sectionHeader) {
        smartClick($(sideSectionElement(sideChildSection)));
        checkWaypointHeaderSectionName(sideMainSection, sideChildSection, sectionHeader);
    }

    private void checkWaypointHeaderSectionName(String sideMainSection, String sideChildSection, String sectionHeader) {
        String parentWaypoint = "//h2[@id='%1$s-%2$s'] | //h3[@id='%1$s-%2$s']";
        SelenideElement parentWaypointElement = $x(
                String.format(parentWaypoint, sideMainSection.toLowerCase(), sideChildSection.toLowerCase()));
        try {
            parentWaypointElement.shouldBe(visible);
        } catch (ElementNotFound e) {
            try {
                String sideChildSectionXpath = String.join("_", sideChildSection.split(" ")).toLowerCase();
                parentWaypoint = "//h2[contains(@id,'%1$s')] | //h3[contains(@id,'%1$s')]";
                parentWaypointElement = $x(String.format(parentWaypoint, sideChildSectionXpath));
                parentWaypointElement.shouldBe(visible);
            } catch (ElementNotFound error) {
                String sideChildSectionXpath = String.join("-", sideChildSection.split(" ")).toLowerCase();
                parentWaypoint = "//h2[contains(@id,'%1$s')] | //h3[contains(@id,'%1$s')]";
                parentWaypointElement = $x(String.format(parentWaypoint, sideChildSectionXpath));
                parentWaypointElement.shouldBe(visible);
            }

        }
        assertElementEquals(parentWaypointElement, sideChildSection);
        System.out.println(
                "Navigated at " + colorize(sectionHeader + " -> " + sideMainSection + " -> " + sideChildSection,
                                           GREEN_TEXT()));
    }

    public void navigateToTableSection(String sideMainSection, String sideChildSection, String tableName) {
        sideMainSection = sideMainSection.toLowerCase();
        String sideMainSectionXpath = String.join("-", sideMainSection.split(" ")).toLowerCase();
        String sideChildSectionXpath = String.join("-", sideChildSection.split(" ")).toLowerCase();
        tableSectionElement = switch (tableName.toLowerCase()) {
            case "headers" -> $(tableSection(sideMainSectionXpath, sideChildSection, tableName));
            case "request parameters" -> $(requestParametersTableSection(sideMainSectionXpath, sideChildSectionXpath));
            case "related errors" -> $(relatedErrorsTableSection(sideMainSectionXpath, sideChildSectionXpath));
            case "response" -> $(responseTableSection(sideMainSectionXpath, sideChildSectionXpath));
            default -> null;
        };
        try {
            waitSleep(3);
            tableSectionElement.shouldBe(visible).scrollTo();
            System.out.println("Successfully scrolled to " + tableName + " section");
        } catch (ElementNotFound e) {
            System.out.println(colorize(
                    "The " + sideMainSection + " -> " + sideChildSection + " section does not have a " + tableName
                            + " table.",
                    RED_TEXT()
            ));
        }
    }
}
