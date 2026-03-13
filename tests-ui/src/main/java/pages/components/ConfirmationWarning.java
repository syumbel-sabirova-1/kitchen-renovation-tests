package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.function.Supplier;

import static com.codeborne.selenide.Condition.visible;

public class ConfirmationWarning<N extends BaseStep<N>, C extends BaseStep<C>> {
    private final SelenideElement message;
    private final SelenideElement continueButton;
    private final SelenideElement stayButton;
    private final Supplier<N> nextStepSupplier;
    private final Supplier<C> currentStepSupplier;

    public ConfirmationWarning(
            SelenideElement root,
            Supplier<N> nextStepSupplier,
            Supplier<C> currentStepSupplier) {
        this.message = root.$x(".//div[contains(@class,'text-orangeDeep100')]");
        this.continueButton = root.$("[data-autotest-button-button-yes], [data-autotest-button-submit-yes]");
        this.stayButton = root.$("[data-autotest-button-button-no], [data-autotest-button-submit-no]");
        this.nextStepSupplier = nextStepSupplier;
        this.currentStepSupplier = currentStepSupplier;
    }

    public ConfirmationWarning<N, C> shouldBeVisible() {
        message.shouldBe(visible);
        continueButton.shouldBe(visible);
        stayButton.shouldBe(visible);
        return this;
    }

    public ConfirmationWarning<N, C> shouldHaveText(String expectedText) {
        message.shouldHave(Condition.exactText(expectedText));
        return this;
    }

    public N clickYes() {
        continueButton.shouldBe(visible).click();
        return nextStepSupplier.get().shouldBeVisible();
    }

    public C clickNo() {
        stayButton.shouldBe(visible).click();
        return currentStepSupplier.get().shouldBeVisible();
    }
}
