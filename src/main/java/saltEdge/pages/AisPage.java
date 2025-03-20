package saltEdge.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import saltEdge.utils.BasePage;

public class AisPage extends BasePage {

    //////ELEMENTS/////
    private static final SelenideElement homePageDeliverAddressBtn = $x("//div[@id='nav-global-location-slot']");
    private static final String homeDeliverCountryNameAddress = "//div/span[@id='glow-ingress-line2'][contains(normalize-space(.), '%s')]";


    //////METHODS/////
    public void verifyDeliverAddress(String addressName) {
        $x(String.format(homeDeliverCountryNameAddress, addressName)).shouldBe(Condition.visible);
        System.out.println("The " + addressName + " is set to be the Deliver Address.");
    }

}
