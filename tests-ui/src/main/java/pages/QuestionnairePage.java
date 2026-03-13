package pages;

import com.codeborne.selenide.SelenideElement;
import pages.questionnaire.steps.ExitStep;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class QuestionnairePage extends BasePage {
    private final SelenideElement closeBtn = $("[data-autotest-button-close]");

    public QuestionnairePage shouldBeVisible() {
        waitForJsReady();
        $("#root").shouldBe(visible);
        closeBtn.shouldBe(visible);
        return this;
    }

    public ExitStep clickCloseQuestionnaire() {
        closeBtn.click();
        return new ExitStep();
    }

    public LandingPage closeQuestionnaire() {
        return clickCloseQuestionnaire().clickCancelProject();
    }
}
