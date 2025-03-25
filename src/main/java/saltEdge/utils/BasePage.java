package saltEdge.utils;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.ITALIC;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import static org.testng.Assert.assertEquals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

public class BasePage {

    public final String BASE_URL = "https://priora.saltedge.com/docs/berlingroup";

    @BeforeAll
    protected void setUpBrowser() {
        DriverManager.configureDriver();
        navigateToMainUrl(BASE_URL);
        maximizeBrowserWindow();
    }

    private static void navigateToMainUrl(String url) {
        open(url);
        System.out.println("Starting Page URL: " + url);
    }

    private static void maximizeBrowserWindow() {
        Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
    }

    protected static void waitForPageToLoad(long timeOutInSeconds) {
        try {
            Selenide.Wait().until(webDriver ->
                                          ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                                                  .equals("complete"));
        } catch (Throwable e) {
            System.out.println("Error occurred while waiting for Page to Load: " + e);
        }
    }

    public static void waitSleep(int timeoutInSeconds) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        CompletableFuture<Void> future = new CompletableFuture<>();
        executor.schedule(() -> future.complete(null), timeoutInSeconds, TimeUnit.SECONDS);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException ignored) {
        } finally {
            executor.shutdown();
        }
    }

    public static void smartClick(SelenideElement target) {
        try {
            target.shouldBe(Condition.exist)
                    .shouldBe(Condition.enabled)
                    .shouldBe(Condition.visible)
                    .hover();
            target.click();
        } catch (Exception | Error e) {
            System.out.println(e.getMessage());
            System.out.println(colorize("Click on " + target.toString() + " with JS executor", YELLOW_TEXT()));
            clickWithJS(target);
        }
    }

    public static void clickWithJS(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

    public static void assertElementEquals(SelenideElement element, String expectedText,
                                           String... getType) {
        String actual;
        if (getType.length == 0 || "text".equalsIgnoreCase(getType[0])) {
            actual = element.shouldBe(Condition.visible).getText();
        } else if ("value".equalsIgnoreCase(getType[0])) {
            actual = element.shouldBe(Condition.visible).getValue();
        } else {
            throw new IllegalArgumentException("Invalid type: " + getType[0]);
        }
        System.out.println(colorize("Assertion text: Expected = '" + expectedText + "', Actual = '" + actual + "'",
                                    ITALIC()));
        assertEquals(actual, expectedText);
    }

    public class Hooks {
        @After
        public void afterScenario(Scenario scenario) {
            // Capture screenshot for ALL scenarios (passed/failed)
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            if (scenario.isFailed()) {
                byte[] failureScreenshot = Objects.requireNonNull(Selenide.screenshot("FAILED_" + scenario.getName())).getBytes();
                scenario.attach(failureScreenshot, "image/png", "FAILED_" + scenario.getName());
            }
        }
    }

}
