package pages.questionnaire.steps;

import pages.components.ConfirmationWarning;
import pages.components.SingleChoiceStep;

import static com.codeborne.selenide.Selenide.$;

public class HomeownerStep extends SingleChoiceStep<HomeownerStep> {
    public static final String TITLE = "Are you the homeowner or authorized to make property changes?";
    public static final String AUTHORIZATION_WARNING_TEXT =
            "Our contractors require the homeowner or someone authorized to make property changes be present during the estimate. Would you like to continue?";

    private static final String YES = "data-autotest-radio-owner-yes";
    private static final String NO = "data-autotest-radio-owner-no";

    public HomeownerStep() {
        super($("[data-tracking='form-step-6']").$("#StepBodyId"), YES, NO);
    }

    public HomeownerStep shouldAskIfUserCanAuthorizeChanges() {
        return shouldHaveTitle(TITLE);
    }

    public HomeownerStep selectYes() {
        return selectOption(YES);
    }

    public HomeownerStep selectNo() {
        return selectOption(NO);
    }

    public KitchenSizeStep clickNext() {
        clickNextButton();
        return new KitchenSizeStep().shouldBeVisible();
    }

    public ConfirmationWarning<KitchenSizeStep, HomeownerStep> clickNextExpectingWarning() {
        clickNextButtonIfPresent();
        return new ConfirmationWarning<>(root, KitchenSizeStep::new, HomeownerStep::new).shouldBeVisible();
    }
}
