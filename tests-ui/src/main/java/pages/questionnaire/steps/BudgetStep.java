package pages.questionnaire.steps;

import pages.components.SingleChoiceStep;

import static com.codeborne.selenide.Selenide.$;

public class BudgetStep extends SingleChoiceStep<BudgetStep> {
    public static final String TITLE = "Do you know what your approximate budget is?";

    private static final String UP_TO_5 = "data-autotest-radio-budget-1";
    private static final String FROM_5_TO_10 = "data-autotest-radio-budget-2";
    private static final String FROM_10_TO_30 = "data-autotest-radio-budget-3";
    private static final String MORE_THAN_30 = "data-autotest-radio-budget-4";
    private static final String NOT_SURE = "data-autotest-radio-budget-not-sure";

    public BudgetStep() {
        super(
                $("[data-tracking='form-step-8']").$("#StepBodyId"),
                UP_TO_5,
                FROM_5_TO_10,
                FROM_10_TO_30,
                MORE_THAN_30,
                NOT_SURE);
    }

    public BudgetStep shouldAskForBudget() {
        return shouldHaveTitle(TITLE);
    }

    public BudgetStep chooseUpTo5() {
        selectOption(UP_TO_5);
        return this;
    }

    public BudgetStep choose5to10() {
        selectOption(FROM_5_TO_10);
        return this;
    }

    public BudgetStep choose10to30() {
        selectOption(FROM_10_TO_30);
        return this;
    }

    public BudgetStep chooseMoreThan30() {
        selectOption(MORE_THAN_30);
        return this;
    }

    public BudgetStep chooseNotSure() {
        selectOption(NOT_SURE);
        return this;
    }

    public ContactInfoStep clickNext() {
        clickNextButton();
        return new ContactInfoStep().shouldBeVisible();
    }
}
