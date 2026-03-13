package pages.questionnaire.steps;

import com.codeborne.selenide.SelenideElement;
import pages.components.BaseStep;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ContactInfoStep extends BaseStep<ContactInfoStep> {
    public static final String TITLE = "Who should I prepare this estimate for?";

    private final SelenideElement fullNameField = $("#fullName");
    private final SelenideElement emailField = $("#email");

    public ContactInfoStep() {
        super($("[data-tracking='form-step-pii1']").$("#StepBodyId"));
    }

    public ContactInfoStep shouldAskForContactDetails() {
        return shouldHaveTitle(TITLE);
    }

    public ContactInfoStep fillFullName(String fullName) {
        fullNameField.shouldBe(visible).setValue(fullName);
        return this;
    }

    public ContactInfoStep fillEmail(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    public PhoneNumberStep clickNext() {
        clickNextButton();
        return new PhoneNumberStep().shouldBeVisible();
    }
}
