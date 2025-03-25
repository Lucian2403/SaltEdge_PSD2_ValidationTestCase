package saltEdge.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BRIGHT_RED_TEXT;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import static saltEdge.pages.Locators.getHeaderTypeXpath;
import static saltEdge.pages.Locators.getHeaderValueXpath;
import static saltEdge.pages.Locators.navigationMenuSection;
import static saltEdge.pages.Locators.relatedErrorsTableSection;
import static saltEdge.pages.Locators.requestParametersTableSection;
import static saltEdge.pages.Locators.responseTableSection;
import static saltEdge.pages.Locators.sectionHeaderElement;
import static saltEdge.pages.Locators.sideSectionElement;
import static saltEdge.pages.Locators.tableSection;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.testng.Assert;
import saltEdge.utils.BasePage;

/**
 * Handles core UI interactions for the Salt Edge Berlin Group application. Provides methods for navigation, section
 * validation, and header/type assertions.
 */
public class HomePage extends BasePage {

    String tableSectionElement;

    /**
     * Navigates to a main section (e.g., "PIIS", "AIS").
     *
     * @param section The display name of the section (case-sensitive)
     */
    public void navigateToMainSection(String section) {
        waitForPageToLoad(60);
        smartClick($x(navigationMenuSection(section)));
    }

    /**
     * Verifies a section header is visible. Automatically handles format variations.
     * @param sectionHeader Expected a header section as from previous method (e.g., "PIIS", "AIS")
     */
    public void checkSectionHeader(String sectionHeader) {
        try {
            $x(sectionHeaderElement(sectionHeader)).shouldBe(visible);
        } catch (ElementNotFound e) {
            String fallbackHeader = sectionHeader.replaceAll(" ", "").toLowerCase();
            $x(sectionHeaderElement(fallbackHeader)).shouldBe(visible);
        }
        System.out.println(colorize("\nThe " + sectionHeader + " section is opened", GREEN_TEXT()));
    }

    /**
     * Navigates to a subsection in the sidebar and validates its header.
     * @param sideMainSection Parent section (e.g., "Funds")
     * @param sideChildSection Child section (e.g., "Confirmations")
     * @param sectionHeader Expected header text for verification
     */
    public void navigateToSideSection(String sideMainSection, String sideChildSection, String sectionHeader) {
        smartClick($x(sideSectionElement(sideChildSection)));
        checkWaypointHeaderSectionName(sideMainSection, sideChildSection, sectionHeader);
    }

    private String buildWaypointByLineXpath(String section) {
        return String.join("-", section.split(" ")).toLowerCase();
    }

    private String buildWaypointByUnderscoreXpath(String section) {
        return String.join("-", section.split(" ")).toLowerCase();
    }

    private void checkWaypointHeaderSectionName(String sideMainSection, String sideChildSection, String sectionHeader) {
        String parentWaypoint = "//h2[@id='%1$s-%2$s'] | //h3[@id='%1$s-%2$s']";
        SelenideElement parentWaypointElement = $x(
                String.format(parentWaypoint, sideMainSection.toLowerCase(), sideChildSection.toLowerCase()));
        try {
            parentWaypointElement.shouldBe(visible);
        } catch (ElementNotFound e) {
            try {
                parentWaypoint = "//h2[contains(@id,'-%1$s')] | //h3[contains(@id,'-%1$s')]";
                parentWaypointElement = $x(String.format(parentWaypoint, sideChildSection.toLowerCase()));
                parentWaypointElement.shouldBe(visible);
            } catch (ElementNotFound error) {
                try {
                    String sideChildSectionXpath = buildWaypointByUnderscoreXpath(sideChildSection);
                    parentWaypoint = "//h2[contains(@id,'%1$s')] | //h3[contains(@id,'%1$s')]";
                    parentWaypointElement = $x(String.format(parentWaypoint, sideChildSectionXpath));
                    parentWaypointElement.shouldBe(visible);
                } catch (ElementNotFound err) {
                    String sideChildSectionXpath = buildWaypointByLineXpath(sideChildSection);
                    parentWaypoint = "//h2[contains(@id,'%1$s')] | //h3[contains(@id,'%1$s')]";
                    parentWaypointElement = $x(String.format(parentWaypoint, sideChildSectionXpath));
                    parentWaypointElement.shouldBe(visible);
                }
            }
        }
        assertElementEquals(parentWaypointElement, sideChildSection);
        System.out.println(
                "Navigated at " + colorize(sectionHeader + " -> " + sideMainSection + " -> " + sideChildSection,
                                           GREEN_TEXT()));
    }

    /**
     * Navigates to a specific table section (e.g., "Response", "Headers").
     * @param sideMainSection Parent section name (e.g., "Funds")
     * @param sideChildSection Child section name (e.g., "Confirmations")
     * @param tableName Table type (e.g., "Headers", "Request Parameters")
     */
    public void navigateToTableSection(String sideMainSection, String sideChildSection, String tableName) {
        tableSectionElement = selectTheRequestedTableSection(sideMainSection, sideChildSection, tableName);
        try {
            waitSleep(3);
            $x(tableSectionElement).shouldBe(visible).scrollTo();
            System.out.println("Successfully scrolled to " + colorize(tableName + " section", YELLOW_TEXT()));
        } catch (ElementNotFound e) {
            System.out.println(colorize(
                    "The " + sideMainSection + " -> " + sideChildSection + " section does not have a " + tableName
                            + " table. \nOR\nThe xpath for the table required is wrong. If the header and header type are bellow, then please check the xpath table",
                    RED_TEXT()
            ));
        }
    }

    private String selectTheRequestedTableSection(String sideMainSection, String sideChildSection, String tableName) {
        sideMainSection = sideMainSection.toLowerCase();
        String sideMainSectionXpath = buildWaypointByLineXpath(sideMainSection);
        String sideChildSectionXpath = buildWaypointByLineXpath(sideChildSection);
        tableSectionElement = switch (tableName.toLowerCase()) {
            case "headers" -> tableSection(sideMainSectionXpath, sideChildSection, tableName);
            case "request parameters" -> requestParametersTableSection(sideMainSectionXpath, sideChildSectionXpath);
            case "related errors" -> relatedErrorsTableSection(sideMainSectionXpath, sideChildSectionXpath);
            case "response" -> responseTableSection(sideMainSectionXpath, sideChildSectionXpath);
            default -> null;
        };
        return tableSectionElement;
    }

    private String getHeaderValue(String sideMainSection, String sideChildSection, String tableName,
                                  String headerValue) {
        tableSectionElement = selectTheRequestedTableSection(sideMainSection, sideChildSection, tableName);
        String headerValueFullPath = tableSectionElement + getHeaderValueXpath(headerValue, tableName);
        SelenideElement headerElement = $x(headerValueFullPath);
        try {
            headerElement.shouldBe(visible);
        } catch (ElementNotFound e) {
            Assert.fail(colorize(
                    "The inserted header '" + headerValue + "' is not present in '" + tableName + "' table section.",
                    BRIGHT_RED_TEXT()));
        }
        String headerValueText = $x(headerValueFullPath).innerText();
        System.out.println("\nThe requested header is " + colorize(headerValueText, YELLOW_TEXT()));
        return headerValueFullPath;
    }

    /**
     * Validates that a header exists and has the expected type in a table section.
     * @param sideMainSection Parent section (e.g., "Fund")
     * @param sideChildSection Child section (e.g., "Confirmations")
     * @param tableName Table type (e.g., "Response")
     * @param headerValue Header name to validate (e.g., "fundsAvailable")
     * @param headerTypeValue Expected type (e.g., "Boolean")
     * @throws AssertionError if header/type is not found or mismatched
     */
    public void getHeaderTypeValue(String sideMainSection, String sideChildSection, String tableName,
                                   String headerValue, String headerTypeValue) {
        String headerValueXpath = getHeaderValue(sideMainSection, sideChildSection, tableName, headerValue);
        headerTypeValue = headerTypeValue.toLowerCase();
        String headerTypeValueFullPath = headerValueXpath + getHeaderTypeXpath(headerTypeValue, tableName);
        SelenideElement headerTypeElement = $x(headerTypeValueFullPath);
        try {
            headerTypeElement.shouldBe(visible);
        } catch (ElementNotFound e) {
            Assert.fail(
                    colorize("The inserted header '" + headerValue + "' does not have such type - " + headerTypeValue,
                             BRIGHT_RED_TEXT()));
        }
        String headerTypeValueText = headerTypeElement.innerText();
        System.out.println("The requested header type is " + colorize(headerTypeValueText, YELLOW_TEXT()));
    }

}
