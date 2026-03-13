package pages.questionnaire.steps;

import com.codeborne.selenide.SelenideElement;
import pages.components.BaseStep;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KitchenSizeStep extends BaseStep<KitchenSizeStep> {
    public static final String TITLE = "What is the approximate size of your kitchen in square feet?";

    private final SelenideElement kitchenSizeField = $("[data-autotest-input-squarefeet-tel]");
    private final SelenideElement notSureCheckbox = $("[data-autotest-checkbox-notsure-]");

    public KitchenSizeStep() {
        super($("[data-tracking='form-step-7']").$("#StepBodyId"));
    }

    public KitchenSizeStep shouldAskForKitchenSize() {
        return shouldHaveTitle(TITLE);
    }

    public KitchenSizeStep inputSize(String kitchenSize) {
        kitchenSizeField.shouldBe(visible).setValue(kitchenSize);
        return this;
    }

    public KitchenSizeStep chooseNotSure() {
        notSureCheckbox.shouldBe(visible).click();
        return this;
    }

    public BudgetStep clickNext() {
        clickNextButton();
        return new BudgetStep().shouldBeVisible();
    }
}
