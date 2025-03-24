package saltEdge.pages;

import org.openqa.selenium.By;

public class Locators {

    public static By navigationMenuSection(String section) {
        return By.xpath(String.format("//ul[@class='menu']//a[@class='menu-link'][.='%s']", section));
    }

    public static By sectionHeaderElement(String sectionHeader) {
        return By.xpath(String.format("//nav[@id='sidebar']//li[contains(@class,'%s')]", sectionHeader.toLowerCase()));
    }

    public static By sideSectionElement(String sideChildSection) {
        return By.xpath(String.format("//li[contains(@class,'tocify-item %s')]", sideChildSection.toLowerCase()));
    }

    public static By tableSection(String sideMainSection, String sideChildSection, String tableType) {
        String tableSectionXpath = String.format("//table[@class='%1$s-%2$s-%3$s']",
                                                 sideMainSection.toLowerCase(),
                                                 sideChildSection.toLowerCase(),
                                                 tableType.toLowerCase());
        return By.xpath(tableSectionXpath);
    }

    public static By requestParametersTableSection(String sideMainSection, String sideChildSection) {
        String requestParametersTableSectionXpath = String.format("//div[contains(@class,'%1$s-%2$s-parameters')]",
                                                 sideMainSection.toLowerCase(),
                                                 sideChildSection.toLowerCase());
        return By.xpath(requestParametersTableSectionXpath);
    }

    public static By relatedErrorsTableSection(String sideMainSection, String sideChildSection) {
        String relatedErrorsTableSectionXpath = String.format("//table[contains(@class,'%1$s-%2$s-errors')]",
                                                                  sideMainSection.toLowerCase(),
                                                                  sideChildSection.toLowerCase());
        return By.xpath(relatedErrorsTableSectionXpath);
    }

    public static By responseTableSection(String sideMainSection, String sideChildSection) {
        String responseTableSectionXpath = String.format("//div[contains(@class,'%1$s-%2$s-response')]",
                                                              sideMainSection.toLowerCase(),
                                                              sideChildSection.toLowerCase());
        return By.xpath(responseTableSectionXpath);
    }

}
