package pages.questionnaire.steps;

import com.codeborne.selenide.SelenideElement;
import pages.components.ConfirmationWarning;
import pages.components.MultiChoiceStep;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class KitchenElementsStep extends MultiChoiceStep<KitchenElementsStep> {
    public static final String TITLE = "Which elements of the kitchen would you like to update?";
    public static final String CABINET_WARNING_TEXT =
            "Our contractors only do projects including kitchen cabinets. Would you still like to continue?";

    private static final String CABINETS = "data-autotest-checkbox-updates-cabinets";
    private static final String COUNTERTOPS = "data-autotest-checkbox-updates-countertop";
    private static final String LIGHTING = "data-autotest-checkbox-updates-lighting";
    private static final String FLOORING = "data-autotest-checkbox-updates-flooring";
    private static final String PLUMBING = "data-autotest-checkbox-updates-plumbing";
    private static final String CHANGE_LAYOUT = "data-autotest-checkbox-updates-changelayout";
    private static final String COUNTERTOPS_TEXT = "Countertop(s)";
    private static final String SINKS_AND_FAUCETS_TEXT = "Sink(s) and";
    private static final String BACKSPLASH_TEXT = "Backsplash";

    public KitchenElementsStep() {
        super(
                $("[data-tracking='form-step-2']").$("#StepBodyId"),
                CABINETS,
                COUNTERTOPS,
                LIGHTING,
                FLOORING,
                PLUMBING,
                CHANGE_LAYOUT);
    }

    public KitchenElementsStep shouldAskWhichKitchenElementsNeedUpdating() {
        return shouldHaveTitle(TITLE);
    }

    public KitchenElementsStep selectCabinets() {
        return selectOptions(CABINETS);
    }

    public KitchenElementsStep selectCountertops() {
        return selectOptionByLabelText(COUNTERTOPS_TEXT);
    }

    public KitchenElementsStep selectSinksAndFaucets() {
        return selectOptionByLabelText(SINKS_AND_FAUCETS_TEXT);
    }

    public KitchenElementsStep selectBacksplash() {
        return selectOptionByLabelText(BACKSPLASH_TEXT);
    }

    public KitchenElementsStep selectLighting() {
        return selectOptions(LIGHTING);
    }

    public KitchenElementsStep selectFlooring() {
        return selectOptions(FLOORING);
    }

    public KitchenElementsStep selectPlumbing() {
        return selectOptions(PLUMBING);
    }

    public KitchenElementsStep selectChangeLayout() {
        return selectOptions(CHANGE_LAYOUT);
    }

    public KitchenUpdateStep clickNext() {
        clickNextButton();
        return new KitchenUpdateStep().shouldBeVisible();
    }

    public ConfirmationWarning<KitchenUpdateStep, KitchenElementsStep> clickNextExpectingWarning() {
        clickNextButton();
        return new ConfirmationWarning<>(root, KitchenUpdateStep::new, KitchenElementsStep::new).shouldBeVisible();
    }

    @Override
    public KitchenElementsStep shouldHaveOptionsCount(int expectedCount) {
        root.$$("[name='updates']").shouldHave(size(expectedCount));
        return this;
    }

    private KitchenElementsStep selectOptionByLabelText(String optionText) {
        findOptionLabel(optionText).click();
        return this;
    }

    private SelenideElement findOptionLabel(String optionText) {
        return root.$$("label").findBy(text(optionText));
    }
}
