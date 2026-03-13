package pages;

import com.codeborne.selenide.SelenideElement;
import pages.questionnaire.steps.KitchenElementsStep;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LandingPage extends BasePage {
    private final SelenideElement getEstimateButton = $("[data-autotest-button-submit-0]");
    private final SelenideElement zipCodeInput = $("[data-autotest-input-0]");

    public LandingPage shouldBeVisible() {
        waitForJsReady();
        getEstimateButton.shouldBe(visible);
        return this;
    }

    public KitchenElementsStep clickGetEstimate() {
        getEstimateButton.shouldBe(visible).click();
        return new KitchenElementsStep().shouldBeVisible();
    }

    public NoMatchingContractorsPage clickGetEstimateExpectingNoMatchingContractors() {
        getEstimateButton.shouldBe(visible).click();
        return new NoMatchingContractorsPage().shouldBeVisible();
    }

    public LandingPage inputZipCode(String zipCode) {
        zipCodeInput.shouldBe(visible).setValue(zipCode);
        return this;
    }
}
