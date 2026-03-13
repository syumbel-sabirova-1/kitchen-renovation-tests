package pages.questionnaire.steps;

import pages.components.ConfirmationWarning;
import pages.components.SingleChoiceStep;

import static com.codeborne.selenide.Selenide.$;

public class HomeTypeStep extends SingleChoiceStep<HomeTypeStep> {
    public static final String TITLE = "Is it a mobile, modular or manufactured home?";
    public static final String MANUFACTURED_HOME_WARNING_TEXT =
            "Unfortunately, our contractors do not work with mobile, modular or manufactured homes. Would you still like to continue?";

    private static final String YES = "data-autotest-radio-internalmobilehome-yes";
    private static final String NO = "data-autotest-radio-internalmobilehome-no";

    public HomeTypeStep() {
        super($("[data-tracking='form-step-5']").$("#StepBodyId"), YES, NO);
    }

    public HomeTypeStep shouldAskAboutHomeType() {
        return shouldHaveTitle(TITLE);
    }

    public HomeTypeStep selectYes() {
        return selectOption(YES);
    }

    public HomeTypeStep selectNo() {
        return selectOption(NO);
    }

    public HomeownerStep clickNext() {
        clickNextButton();
        return new HomeownerStep().shouldBeVisible();
    }

    public ConfirmationWarning<HomeownerStep, HomeTypeStep> clickNextExpectingWarning() {
        clickNextButtonIfPresent();
        return new ConfirmationWarning<>(root, HomeownerStep::new, HomeTypeStep::new).shouldBeVisible();
    }
}
