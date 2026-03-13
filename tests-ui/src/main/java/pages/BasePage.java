package pages;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    protected void waitForJsReady() {
        $("body").shouldHave(attribute("data-autotest-js-ready"));
    }
}
