package saltEdge.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
public class DriverManager {
    @BeforeMethod
    protected static void configureDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--disable-plugins", "--disable-gpu");
        Configuration.browser = "Chrome";
        Configuration.browserCapabilities = options;
        Configuration.timeout = Duration.ofSeconds(5).toMillis();
        Configuration.pageLoadTimeout = Duration.ofSeconds(15).toMillis();
        Configuration.holdBrowserOpen = true;
    }

    protected static void closeDriver() {
        Selenide.closeWebDriver();
    }
}
