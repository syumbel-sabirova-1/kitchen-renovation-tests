package dev.sirenltd;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public abstract class UITestBase {
    protected final Faker faker = new Faker();

    @BeforeClass
    public void initSelenideConfig() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = TestConfig.getBaseUrl();
    }

    public void openLanding() {
        open(Configuration.baseUrl);
        if (hasWebDriverStarted()) {
            getWebDriver().manage().window().maximize();
        }
    }
}

