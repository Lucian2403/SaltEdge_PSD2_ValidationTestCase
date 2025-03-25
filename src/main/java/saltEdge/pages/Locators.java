package saltEdge.pages;

public class Locators {

    public static String navigationMenuSection(String section) {
        return String.format("//ul[@class='menu']//a[@class='menu-link'][.='%s']", section);
    }

    public static String sectionHeaderElement(String sectionHeader) {
        return String.format("//nav[@id='sidebar']//li[contains(@class,'%s')]", sectionHeader.toLowerCase());
    }

    public static String sideSectionElement(String sideChildSection) {
        return String.format("//li[@class='tocify-item %s']", sideChildSection.toLowerCase());
    }

    public static String tableSection(String sideMainSection, String sideChildSection, String tableType) {
        String tableSectionXpath = String.format("//table[@class='%1$s-%2$s-%3$s']",
                                                 sideMainSection.toLowerCase(),
                                                 sideChildSection.toLowerCase(),
                                                 tableType.toLowerCase());
        return tableSectionXpath;
    }

    public static String requestParametersTableSection(String sideMainSection, String sideChildSection) {
        String requestParametersTableSectionXpath = String.format("//div[contains(@class,'%1$s-%2$s-parameters')]",
                                                 sideMainSection.toLowerCase(),
                                                 sideChildSection.toLowerCase());
        return requestParametersTableSectionXpath;
    }

    public static String relatedErrorsTableSection(String sideMainSection, String sideChildSection) {
        String relatedErrorsTableSectionXpath = String.format("//table[contains(@class,'%1$s-%2$s-errors')]",
                                                                  sideMainSection.toLowerCase(),
                                                                  sideChildSection.toLowerCase());
        return relatedErrorsTableSectionXpath;
    }

    public static String responseTableSection(String sideMainSection, String sideChildSection) {
        String responseTableSectionXpath = String.format("//div[contains(@class,'%1$s-%2$s-response')]",
                                                              sideMainSection.toLowerCase(),
                                                              sideChildSection.toLowerCase());
        return responseTableSectionXpath;
    }

    public static String getHeaderValueXpath(String headerValue, String tableName) {
        if (tableName.equalsIgnoreCase("Headers")) {
            return String.format("//tbody//td[@class='cell first-title']/code[.='%s']", headerValue);
        } else {
            return String.format("//div[@class='param']//span[.='%s']", headerValue);
        }

    }

    public static String getHeaderTypeXpath(String headerTypeValue, String tableName) {
        if (tableName.equalsIgnoreCase("Headers")) {
            return String.format("//parent::td//following-sibling::td/strong[contains(.,'%s')]", headerTypeValue);
        } else {
            return String.format("//ancestor::div[@class='param']//div[@class='param-row param-type']/span[contains(.,'%s')]", headerTypeValue);
        }
    }
}
