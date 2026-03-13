package pages.questionnaire.steps;

import com.codeborne.selenide.SelenideElement;
import pages.BasePage;
import pages.LandingPage;
import pages.QuestionnairePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ExitStep extends BasePage {
    private static final String CONFIRMATION_TEXT = "We just need to confirm your information to get you a quote.";

    private final SelenideElement confirmationWindow = $(".step-active");
    private final SelenideElement confirmationText = confirmationWindow.$("h4");
    private final SelenideElement returnToProjectBtn = $("[data-autotest-button-submit-return-to-project]");
    private final SelenideElement cancelProjectBtn = $("[data-autotest-button-submit-cancel-project]");

    public ExitStep shouldBeVisible() {
        confirmationWindow.shouldBe(visible);
        confirmationText.shouldBe(visible);
        return this;
    }

    public ExitStep shouldShowConfirmationText() {
        confirmationText.shouldHave(text(CONFIRMATION_TEXT));
        return this;
    }

    public QuestionnairePage clickReturnToProject() {
        returnToProjectBtn.shouldBe(visible).click();
        return new QuestionnairePage().shouldBeVisible();
    }

    public LandingPage clickCancelProject() {
        cancelProjectBtn.shouldBe(visible).click();
        return new LandingPage().shouldBeVisible();
    }
}
