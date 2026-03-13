package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public abstract class BaseStep<T extends BaseStep<T>> {
    private final List<String> optionAttributes;
    protected final SelenideElement root;
    protected final SelenideElement nextButton;
    private final SelenideElement title;

    protected BaseStep(SelenideElement root, String... optionAttributes) {
        this.root = root;
        this.optionAttributes = List.copyOf(Arrays.asList(optionAttributes));
        this.nextButton = root.$("[data-autotest-button-submit-next], [data-autotest-button-button-next]");
        this.title = root.$("h4");
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    protected void clickNextButton() {
        nextButton.shouldBe(enabled).as("Next button is not enabled").click();
    }

    protected void clickNextButtonIfPresent() {
        if (nextButton.exists()) {
            clickNextButton();
        }
    }

    protected SelenideElement option(String optionAttribute) {
        return root.$("[%s]".formatted(optionAttribute)).should(exist);
    }

    protected SelenideElement optionLabel(String optionAttribute) {
        return option(optionAttribute).parent().$("label").shouldBe(visible);
    }

    public T shouldHaveTitle(String expectedTitle) {
        title.shouldBe(visible).shouldHave(text(expectedTitle));
        return self();
    }

    public T shouldBeVisible() {
        root.shouldBe(visible);
        title.shouldBe(visible);
        return self();
    }

    public T shouldHaveOptionsCount(int expectedCount) {
        if (optionAttributes.isEmpty()) {
            throw new IllegalStateException("Option count is not supported for %s".formatted(getClass().getSimpleName()));
        }
        if (optionAttributes.size() != expectedCount) {
            throw new AssertionError(
                    "Expected %s options to be defined but found %s in %s"
                            .formatted(expectedCount, optionAttributes.size(), getClass().getSimpleName()));
        }
        optionAttributes.forEach(this::optionLabel);
        return self();
    }
}
