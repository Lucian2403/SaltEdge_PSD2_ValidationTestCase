package saltEdge.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

import com.codeborne.selenide.Condition;
import saltEdge.utils.BasePage;

public class HomePage extends BasePage {
    //////ELEMENTS/////
    private static final String navigationMenuSection = "//ul[@class='menu']//a[@class='menu-link'][.='AIS']";
    private static final String sectionHeaderElement = "//nav[@id='sidebar']//li[contains(@class,'%s')]";

    //////ELEMENTS/////
    public void navigateToSection(String section) {
        waitForPageToLoad(60);
        smartClick($x(String.format(navigationMenuSection, section.toUpperCase())));
    }
    public void checkSectionHeader(String sectionHeader) {
        $x(String.format(sectionHeaderElement, sectionHeader.toLowerCase())).shouldBe(Condition.visible);
        System.out.println(colorize("\nThe " + sectionHeader + " section is opened", GREEN_TEXT()));
    }


}
