package pages.questionnaire.steps;

import pages.components.ConfirmationWarning;
import pages.components.SingleChoiceStep;

import static com.codeborne.selenide.Selenide.$;

public class KitchenUpdateStep extends SingleChoiceStep<KitchenUpdateStep> {
    public static final String TITLE = "What would you like to do with your kitchen cabinets?";
    public static final String FEW_CABINETS_WARNING_TEXT =
            "Our contractors only replace all or most cabinets. Would still you like to continue?";

    private static final String REPLACE_ALL = "data-autotest-radio-worktype-replaceall";
    private static final String REPLACE_FEW = "data-autotest-radio-worktype-fewcabinets";
    private static final String NOT_SURE = "data-autotest-radio-worktype-notsure";

    public KitchenUpdateStep() {
        super($("[data-tracking='form-step-3']").$("#StepBodyId"), REPLACE_ALL, REPLACE_FEW, NOT_SURE);
    }

    public KitchenUpdateStep shouldAskAboutCabinetUpdates() {
        return shouldHaveTitle(TITLE);
    }

    public KitchenUpdateStep selectReplaceAll() {
        return selectOption(REPLACE_ALL);
    }

    public KitchenUpdateStep selectReplaceFew() {
        return selectOption(REPLACE_FEW);
    }

    public KitchenUpdateStep selectNotSure() {
        return selectOption(NOT_SURE);
    }

    public TypeOfPropertyStep clickNext() {
        clickNextButton();
        return new TypeOfPropertyStep().shouldBeVisible();
    }

    public ConfirmationWarning<TypeOfPropertyStep, KitchenUpdateStep> clickNextExpectingWarning() {
        clickNextButtonIfPresent();
        return new ConfirmationWarning<>(root, TypeOfPropertyStep::new, KitchenUpdateStep::new).shouldBeVisible();
    }
}
