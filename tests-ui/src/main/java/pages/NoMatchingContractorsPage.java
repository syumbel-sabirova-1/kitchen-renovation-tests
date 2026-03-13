package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NoMatchingContractorsPage extends BasePage {
    public static final String TITLE_TEXT = "Unfortunately, I have no matching contractors in your area yet.";

    private final SelenideElement root = $("#StepBodyId");
    private final SelenideElement title = $("#StepBodyId h4");
    private final SelenideElement emailInput = $("#StepBodyId #email");
    private final SelenideElement submitBtn = $("#StepBodyId [data-autotest-button-submit-submit]");
    private final SelenideElement tryAnotherZipBtn =
            $("#StepBodyId [data-autotest-button-try-another-zip], #StepBodyId [data-autotest-button-submit-try-another-zip-code]");

    public NoMatchingContractorsPage shouldBeVisible() {
        waitForJsReady();
        root.shouldBe(visible);
        title.shouldBe(visible).shouldHave(text(TITLE_TEXT));
        return this;
    }

    public NoMatchingContractorsPage fillEmail(String email) {
        emailInput.shouldBe(visible).setValue(email);
        return this;
    }

    public NoMatchingContractorsPage clickSubmit() {
        submitBtn.shouldBe(visible).click();
        return this;
    }

    public void shouldHaveSuccessMessage(String message) {
        root.shouldHave(text(message));
    }

    public LandingPage clickTryAnotherZip() {
        tryAnotherZipBtn.shouldBe(visible).click();
        return new LandingPage().shouldBeVisible();
    }
}
