package pages.questionnaire.steps;

import pages.components.ConfirmationWarning;
import pages.components.SingleChoiceStep;

import static com.codeborne.selenide.Selenide.$;

public class TypeOfPropertyStep extends SingleChoiceStep<TypeOfPropertyStep> {
    public static final String TITLE = "What type of property is this?";
    public static final String COMMERCIAL_WARNING_TEXT =
            "Our contractors only work with single or multi-family homes. Would you still like to continue?";

    private static final String SINGLE_FAMILY = "data-autotest-radio-propertytype-single";
    private static final String MULTI_FAMILY = "data-autotest-radio-propertytype-multi";
    private static final String COMMERCIAL = "data-autotest-radio-propertytype-commercial";

    public TypeOfPropertyStep() {
        super($("[data-tracking='form-step-4']").$("#StepBodyId"), SINGLE_FAMILY, MULTI_FAMILY, COMMERCIAL);
    }

    public TypeOfPropertyStep shouldAskForPropertyType() {
        return shouldHaveTitle(TITLE);
    }

    public TypeOfPropertyStep selectSingleFamily() {
        return selectOption(SINGLE_FAMILY);
    }

    public TypeOfPropertyStep selectMultiFamily() {
        return selectOption(MULTI_FAMILY);
    }

    public TypeOfPropertyStep selectCommercial() {
        return selectOption(COMMERCIAL);
    }

    public HomeTypeStep clickNext() {
        clickNextButton();
        return new HomeTypeStep().shouldBeVisible();
    }

    public ConfirmationWarning<HomeTypeStep, TypeOfPropertyStep> clickNextExpectingWarning() {
        clickNextButtonIfPresent();
        return new ConfirmationWarning<>(root, HomeTypeStep::new, TypeOfPropertyStep::new).shouldBeVisible();
    }
}
